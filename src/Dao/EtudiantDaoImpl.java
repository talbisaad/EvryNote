package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Beans.Etudiant;
import Beans.Filiere;
import Beans.Matiere;
import Treatment.TreatementEtudiant;

public class EtudiantDaoImpl implements EtudiantDao {

	private DAOFactory daofactory;
	private TreatementEtudiant treatementetudiant;
	private static final String GET_LISTETUDIANT = "select * from etudiant where idClasse= ?";
	private static final String CREAT_STUDENT = "INSERT INTO etudiant (`INE`, `NomEtudiant`, `PrenomEtudiant`, `DateDeNaissance`, `TelEtud`, `EmailEtudiant`, `IdClasse`, `Active`) VALUES ( ?, ?, ?, ?, ?, ?, ?,false)";
	private static final String UPDATE_STUDENT = "UPDATE etudiant SET NomEtudiant= ?, PrenomEtudiant= ?, DateDeNaissance= ?, TelEtud= ?, EmailEtudiant= ? WHERE  INE= ?";
	private static final String DELETE_STUDENT = "DELETE FROM `evrynote`.`etudiant` WHERE `etudiant`.`INE` = ?";
	private static final String GET_MATIERES_FOR_SIMULATE = "SELECT  filmatens.coefficient, matiere.nom from matiere,filmatens "
			+ "where matiere.idMatiere=filmatens.idMatiere AND idFiliere=(SELECT distinct filiere.idFiliere from filiere,filmatens where filmatens.idFiliere=filiere.idFiliere "
			+ "AND filiere.nomFiliere=? and filiere.niveau=?)";

	public EtudiantDaoImpl(DAOFactory daofactory) {
		// TODO Auto-generated constructor stub

		this.daofactory = daofactory;
		treatementetudiant = new TreatementEtudiant();

	}

	@Override
	public void AddStudent(Etudiant e) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		java.sql.Date mySqlDate = new java.sql.Date(e.getDateDeNaissance().getTime());

		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					CREAT_STUDENT, false, e.getIne(), e.getNomEtudiant(), e.getPrenomEtudiant(), mySqlDate,
					e.getTelEtud(), e.getEmailEtudiant(), e.getClasse().getIdClasse());
			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException("échec de la création de l'étudiant, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException ex) {

			throw new DAOException(ex);

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
					e.getEmailEtudiant(), e.getIne());
			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException("échec de la création de l'étudiant, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException ex) {

			throw new DAOException(ex);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

	}

	@Override
	public void DeleteStudent(Etudiant e) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					DELETE_STUDENT, false, e.getIne());
			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException("échec de la suppression de l'étudiant, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException ex) {

			throw new DAOException(ex);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

	}

	@Override
	public ArrayList<Etudiant> GetListOfStudent(int idClasse) throws DAOException {

		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					GET_LISTETUDIANT, false, idClasse);
			ResultSet result = preparedStatement.executeQuery();

			return treatementetudiant.MapResultSetToClasse(result);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

	}

	@Override
	public ArrayList<Matiere> GetMatiereForSimulate(Filiere filiere) throws DAOException {
		// TODO Auto-generated method stub
				Connection connexion = null;
				PreparedStatement preparedStatement = null;

				try {
					connexion = daofactory.getConnection();
					preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
							GET_MATIERES_FOR_SIMULATE, false, filiere.getNom(),filiere.getNiveau());
					ResultSet result = preparedStatement.executeQuery();

					return MapForSimulation(result);

				} catch (SQLException e) {

					throw new DAOException(e);

				} finally {

					DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

				}
	}
	
	public ArrayList<Matiere> MapForSimulation(ResultSet resultset){
		ArrayList<Matiere> listmatiere = new ArrayList<Matiere>();
		if(resultset!=null){
			
			try {
				while(resultset.next()){
					Matiere matiere = new Matiere();
					matiere.setNom(resultset.getString("nom"));
					matiere.setCoefficient(resultset.getInt("coefficient"));
					listmatiere.add(matiere);
				}
				return listmatiere;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
		
	}

	@Override
	public void Upload(ArrayList<Etudiant> listetudiant) throws DAOException {
		// TODO Auto-generated method stub
		
		for(Etudiant e:listetudiant){
			AddStudent(e);
		}
		
		
		
		
		
		
	}

}
