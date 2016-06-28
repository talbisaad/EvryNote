package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import Beans.Enseignant;
import Beans.Etudiant;

public class InscriptionDaoImpl implements InscriptionDao {

	private DAOFactory daofactory;
	
	private static final String GET_STUDENT_BY_ID="SELECT * from etudiant WHERE INE= ?";
	private static final String UPDATE_STUDENT = "UPDATE etudiant SET NomEtudiant= ?, PrenomEtudiant= ?, DateDeNaissance= ?, TelEtud= ?, EmailEtudiant= ?, Password= ?,Active= 1  WHERE  INE= ?";
	private static final String ADD_RESPONSABLE="INSERT INTO `evrynote`.`responsable` (`nom`, `prenom`, `login`, `password`) VALUES (?, ?, ?, ?);";
	private static final String GET_ID_RESPONSABLE="SELECT distinct IdResponsable from responsable WHERE nom=? AND  prenom=?  AND login=? AND  password= ?";
	private static final String INSERT_ROLE="INSERT INTO `evrynote`.`droit` (`IdResponsable`, `CD`, `RF`, `AD`) VALUES (?, ?, ?, ?);";
	public InscriptionDaoImpl(DAOFactory daoFactory) {
		
		this.daofactory = daoFactory;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Etudiant SearchStudent(int ine) {

		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					GET_STUDENT_BY_ID, false, ine);
			ResultSet result = preparedStatement.executeQuery();

			return MapResultsetToEtudiant(result);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
		
	}
	
	@Override
	public void UpdateStudent(Etudiant e) throws DAOException {
		// TODO Auto-generated method stub
		

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		java.sql.Date mySqlDate = new java.sql.Date(e.getDateDeNaissance().getTime());

		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					UPDATE_STUDENT, false, e.getNomEtudiant(), e.getPrenomEtudiant(), mySqlDate, e.getTelEtud(),
					e.getEmailEtudiant(),e.getPasseword(), e.getIne());
			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException("échec de la modification de l'étudiant, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException ex) {

			throw new DAOException(ex);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
		
	}

	
	public Etudiant MapResultsetToEtudiant(ResultSet resultSet){
		Etudiant etudiant = new Etudiant();
		
		try {
			while(resultSet.next()){
				etudiant.setIne(resultSet.getInt("INE"));
				etudiant.setNomEtudiant(resultSet.getString("NomEtudiant"));
				etudiant.setPrenomEtudiant(resultSet.getString("PrenomEtudiant"));
				etudiant.setDateDeNaissance(resultSet.getDate("DateDeNaissance"));
				etudiant.setTelEtud(resultSet.getInt("TelEtud"));
				etudiant.setEmailEtudiant(resultSet.getString("EmailEtudiant"));
				etudiant.getClasse().setIdClasse(resultSet.getInt("IdClasse"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return etudiant;
	}

	@Override
	public void InsertResponsable(Enseignant enseignant) throws DAOException {
		// TODO Auto-generated method stub
		

		Connection connexion = null;
		PreparedStatement preparedStatement = null;


		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					ADD_RESPONSABLE, false,enseignant.getNom(),enseignant.getPrenom(),enseignant.getLogin(),enseignant.getMotdepasse());
			int statut = preparedStatement.executeUpdate();
			
			int id=getIdResponsable(enseignant);
			enseignant.setId(id);
			InsertRole(enseignant);
			if (statut == 0) {
				throw new DAOException("échec de la création du résponsable, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException ex) {

			throw new DAOException(ex);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
		
	}
	
	public int getIdResponsable(Enseignant enseignant)throws DAOException{

		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					GET_ID_RESPONSABLE, false,enseignant.getNom(),enseignant.getPrenom(),enseignant.getLogin(),enseignant.getMotdepasse());
			ResultSet result = preparedStatement.executeQuery();
			
			if(result.next())
			return result.getInt("IdResponsable");

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
		
		return 0;
	}
	
	public void InsertRole(Enseignant enseignant){

		Connection connexion = null;
		PreparedStatement preparedStatement = null;


		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					INSERT_ROLE, false, enseignant.getId(),enseignant.isChefDepart(),enseignant.isReponsableFil(),enseignant.isAdministratif());
			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException("échec de la modification de l'étudiant, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException ex) {

			throw new DAOException(ex);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
	}

	
}
