package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import Dao.DAOUtilitaire;

import Beans.Enseignant;

public class EnseignantDaoImpl implements EnseignantDao {

	private DAOFactory daoFactory;

	ArrayList<Enseignant> enseignants;

	// lister les enseignants
	private static final String SQL_SELECT_LIST_ENS = "SELECT responsable.idResponsable, responsable.nom, responsable.prenom, droit.CD, droit.RF FROM responsable, droit WHERE responsable.idResponsable = droit.idResponsable AND droit.RF=false";
	// selectionner l'enseignant avec son id
	private static final String SQL_SELECT_ENS_ID = "SELECT responsable.idResponsable, responsable.nom, responsable.prenom, droit.CD, droit.RF FROM responsable, droit WHERE responsable.idResponsable = droit.idResponsable AND responsable.idResponsable = ?";
	// selectionner l'enseignant avec son nom
	private static final String SQL_SELECT_ENS_NOM = "SELECT responsable.idResponsable, responsable.nom, responsable.prenom, droit.CD, droit.RF FROM responsable, droit WHERE responsable.idResponsable = droit.idResponsable AND responsable.nom = ?";
	// selectionner l'enseignant avec son prenom
	private static final String SQL_SELECT_ENS_PRENOM = "SELECT responsable.idResponsable, responsable.nom, responsable.prenom, droit.CD, droit.RF FROM responsable, droit WHERE responsable.idResponsable = droit.idResponsable AND responsable.prenom = ?";
	// selectionner l'enseignant avec son prenom, son nom ou son id
	private static final String SQL_SELECT_ENS_ID_NOM_PRENOM = "SELECT responsable.idResponsable, responsable.nom, responsable.prenom, droit.CD, droit.RF FROM responsable, droit WHERE responsable.idResponsable = droit.idResponsable AND responsable.idResponsable = ? OR responsable.nom = ? OR responsable.prenom=?";
	// selectionner l'enseignant avec son login
	private static final String SQL_SELECT_ENS_LOGIN = "SELECT responsable.idResponsable, responsable.nom, responsable.prenom, responsable.login, responsable.password, droit.CD, droit.RF FROM responsable, droit WHERE responsable.idResponsable = droit.idResponsable AND responsable.login = ?";
	// lister tout les enseignants avec leurs droits
	private static final String SQL_SELECT_LIST = "SELECT responsable.idResponsable, responsable.nom, responsable.prenom, droit.CD, droit.RF FROM responsable, droit WHERE responsable.idResponsable = droit.idResponsable";
	// selectionner l'enseignant en mettant en parametre la matiere qui enseigne
	private static final String SQL_SELECT_ENS_MAT = "SELECT responsable.idResponsable, responsable.nom, responsable.prenom FROM responsable, filMatEns WHERE responsable.idResponsable = filMatEns.idResponsable AND filMatEns.nomMatiere= ?";
	// modifier le droit d'un enseignant
	private static final String SQL_UPDATE_ENS_DROIT = "UPDATE droit SET RF = ?, CD = ? WHERE idResponsable = ? ";
	// modification du responsable au moment de la suppression du responsable
	// par 0
	private static final String SQL_UPDATE_ENS_FIL = "UPDATE filiere SET idResponsable = 0 WHERE idResponsable = ?";
	// supprimer ensiegnant/Responsable/Chef de departement
	private static final String SQL_DELETE_ENS = "DELETE FROM responsable WHERE idResponsable = ?";
	// supprimer droit ensiegnant/Responsable/Chef de departement
	private static final String SQL_DELETE_ENS_DROIT = "DELETE FROM droit WHERE idResponsable = ?";
	// supprimer les matieres d'un enseignant
	private static final String SQL_DELETE_ENS_MAT = "DELETE FROM filMatEns WHERE idResponsable = ? ";

	private static Enseignant map(ResultSet resultSet) throws SQLException {

		Enseignant enseignant = new Enseignant();

		enseignant.setId(resultSet.getInt("idResponsable"));

		enseignant.setNom(resultSet.getString("nom"));

		enseignant.setPrenom(resultSet.getString("prenom"));

		enseignant.setChefDepart(resultSet.getBoolean("CD"));

		enseignant.setReponsableFil(resultSet.getBoolean("RF"));

		return enseignant;

	}

	private static Enseignant mapLogin(ResultSet resultSet) throws SQLException {

		Enseignant enseignant = new Enseignant();

		enseignant.setId(resultSet.getInt("idResponsable"));

		enseignant.setNom(resultSet.getString("nom"));

		enseignant.setPrenom(resultSet.getString("prenom"));

		enseignant.setLogin(resultSet.getString("login"));

		enseignant.setPassword(resultSet.getString("password"));

		enseignant.setChefDepart(resultSet.getBoolean("CD"));

		enseignant.setReponsableFil(resultSet.getBoolean("RF"));

		return enseignant;

	}

	private static Enseignant mapBis(ResultSet resultSet) throws SQLException {

		Enseignant enseignant = new Enseignant();

		enseignant.setId(resultSet.getInt("idResponsable"));

		enseignant.setNom(resultSet.getString("nom"));

		enseignant.setPrenom(resultSet.getString("prenom"));

		return enseignant;

	}

	EnseignantDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Enseignant enseignant) throws DAOException {

	}

	@Override
	public ArrayList<Enseignant> listerEns() throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		enseignants = new ArrayList<Enseignant>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_LIST_ENS, false);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				enseignants.add(map(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return enseignants;
	}

	@Override
	public Enseignant modifier(int id) throws DAOException {

		return null;
	}

	@Override
	public void modifierRespFil(int idResponsable) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_UPDATE_ENS_FIL, false,
					idResponsable);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				System.out.println("Échec de la modification de l'enseignant, aucune ligne modifiée dans la table.");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
	}

	public void modifierDroit(int id, boolean RF, boolean CD) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_UPDATE_ENS_DROIT, false, RF,
					CD, id);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				System.out.println(
						"Échec de la modification de l'enseignant pour la filiere, aucune ligne modifiée dans la table.");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
	}

	public void supprimerDroit(int idResponsable) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_DELETE_ENS_DROIT, false,
					idResponsable);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				System.out.println(
						"Échec de la suppression de droit de l'enseignant, aucune ligne modifiée dans la table.");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
	}

	public void supprimerEnsMat(int idResponsable) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_DELETE_ENS_MAT, false,
					idResponsable);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				System.out.println("l'enseignant n'a aucune matiere");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
	}

	public void supprimerEns(int idResponsable) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_DELETE_ENS, false,
					idResponsable);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				System.out.println("Échec de la suppression de l'enseignant, aucune ligne modifiée dans la table.");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
	}

	@Override
	public Enseignant trouverById(int id) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		Enseignant enseignant = null;

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_ENS_ID, false, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				enseignant = map(resultSet);
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}
		return enseignant;
	}

	@Override
	public ArrayList<Enseignant> lister() throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		enseignants = new ArrayList<Enseignant>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_LIST, false);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				enseignants.add(map(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return enseignants;
	}

	@Override
	public Enseignant trouverEnsMat(String matiere) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		Enseignant enseignant = null;

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_ENS_MAT, false,
					matiere);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				enseignant = mapBis(resultSet);
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}
		return enseignant;
	}

	@Override
	public ArrayList<Enseignant> trouverByNom(String nom) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		enseignants = new ArrayList<Enseignant>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_ENS_NOM, false, nom);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				enseignants.add(map(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}
		return enseignants;
	}

	@Override
	public ArrayList<Enseignant> trouverByPrenom(String prenom) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		enseignants = new ArrayList<Enseignant>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_ENS_PRENOM, false,
					prenom);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				enseignants.add(map(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}
		return enseignants;
	}

	@Override
	public ArrayList<Enseignant> trouverByNomPrenomId(String responsable) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		enseignants = new ArrayList<Enseignant>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_ENS_ID_NOM_PRENOM,
					false, responsable, responsable, responsable);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				enseignants.add(map(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}
		return enseignants;
	}

	@Override
	public Enseignant trouverByLogin(String login) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		Enseignant enseignant = null;

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_ENS_LOGIN, false,
					login);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				enseignant = mapLogin(resultSet);
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}
		return enseignant;
	}

}
