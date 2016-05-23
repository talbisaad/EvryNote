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
	// modifier le droit d'un enseignant
	private static final String SQL_UPDATE_ENS_DROIT = "UPDATE droit SET RF = true WHERE idResponsable = ? ";
	// lister tout les enseignants avec leurs droits
	private static final String SQL_SELECT_LIST = "SELECT responsable.idResponsable, responsable.nom, responsable.prenom, droit.CD, droit.RF FROM responsable, droit WHERE responsable.idResponsable = droit.idResponsable";
	// selectionner l'enseignant en mettant en parametre la matiere qui enseigne
	private static final String SQL_SELECT_ENS_MAT = "SELECT responsable.idResponsable, responsable.nom, responsable.prenom FROM responsable, filMatEns WHERE responsable.idResponsable = filMatEns.idResponsable AND filMatEns.idMatiere= ?";

	private static Enseignant map(ResultSet resultSet) throws SQLException {

		Enseignant enseignant = new Enseignant();

		enseignant.setId(resultSet.getInt("idResponsable"));

		enseignant.setNom(resultSet.getString("nom"));

		enseignant.setPrenom(resultSet.getString("prenom"));

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
	public void modifierDroit(int id) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_UPDATE_ENS_DROIT, false, id);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException(
						"Échec de la modification de l'enseignant, aucune ligne modifiée dans la table.");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
	}

	@Override
	public Enseignant trouver(int id) throws DAOException {
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

}
