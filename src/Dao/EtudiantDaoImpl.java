package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Beans.Etudiant;
import Treatment.TreatementEtudiant;

public class EtudiantDaoImpl implements EtudiantDao {

	private DAOFactory daofactory;
	private static ClasseDao classe;

	private TreatementEtudiant treatementetudiant;
	private static final String GET_LISTETUDIANT = "select * from etudiant where idClasse= ?";
	private static final String CREAT_STUDENT = "INSERT INTO etudiant (`INE`, `NomEtudiant`, `PrenomEtudiant`, `DateDeNaissance`, `TelEtud`, `EmailEtudiant`, `IdClasse`) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STUDENT = "UPDATE etudiant SET NomEtudiant= ?, PrenomEtudiant= ?, DateDeNaissance= ?, TelEtud= ?, EmailEtudiant= ? WHERE  INE= ?";
	private static final String DELETE_STUDENT="DELETE FROM `evrynote`.`etudiant` WHERE `etudiant`.`INE` = ?";
	//mes modifs (zahir badr)
	private static final String SQL_SELECT_CLASSE_ETUDIANT ="SELECT INE , NomEtudiant , PrenomEtudiant, idClasse FROM etudiant WHERE idClasse = ?";
	
	private static final String SQL_SELECT_TROUVER_ETUDIANT ="SELECT INE , NomEtudiant , PrenomEtudiant, idClasse FROM etudiant WHERE INE = ?";
	
	//private static final String SQL_SELECT_ETUDAINT_CLASSE_ENSEIGNANT ="SELECT INE, NomEtudiant, PrenomEtudiant FROM etudiant, matiere, filMatEns WHERE matiere.idMatiere = 4 AND filMatEns.idResponsable = 1 AND filMatEns.idMatiere=matiere.idMatiere AND etudiant.IdClasse=filMatEns.idClasse";

	EtudiantDaoImpl(DAOFactory daoFactory) {
		this.daofactory = daoFactory;
		classe = (ClasseDaoImpl) daoFactory.getClasseDao();
	}
	
	private static Etudiant map(ResultSet resultSet) throws SQLException {
		Etudiant etudiant = new Etudiant();
		etudiant.setIne(resultSet.getInt("INE"));
		etudiant.setNomEtudiant(resultSet.getString("NomEtudiant"));
		etudiant.setPrenomEtudiant(resultSet.getString("PrenomEtudiant"));
		etudiant.setClasse(classe.trouverClasse(resultSet.getInt("idClasse")));
		return etudiant;
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
				throw new DAOException("�chec de la cr�ation de l'�tudiant, aucune ligne ajout�e dans la table.");
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
				throw new DAOException("�chec de la cr�ation de l'�tudiant, aucune ligne ajout�e dans la table.");
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
					DELETE_STUDENT, false,e.getIne());
			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException("�chec de la suppression de l'�tudiant, aucune ligne ajout�e dans la table.");
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

	// zahir badr
	@Override
	public Etudiant trouverEtudiant(int idEtudaint) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		Etudiant etudaint = new Etudiant();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daofactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_TROUVER_ETUDIANT, false,
					idEtudaint);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				etudaint = map(resultSet);
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return etudaint;

	}
	
	@Override
	public ArrayList<Etudiant> classeEtudiant(int idClasse) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daofactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_CLASSE_ETUDIANT, false,
					idClasse);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				etudiants.add(map(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return etudiants;
	}
	
	
}
