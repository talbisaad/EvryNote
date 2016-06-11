package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Beans.Filiere;
import Beans.Matiere;

public class FiliereDaoImpl implements FiliereDao {

	private DAOFactory daoFactory;

	private ArrayList<Filiere> filieres;

	private static final String SQL_INSERT_FIL = "INSERT INTO filiere (nomFiliere, idResponsable, niveau) VALUES (?, ?, ?)";

	private static final String SQL_UPDATE_FIL = "UPDATE filiere SET nomFiliere = ?, idResponsable = ?, niveau = ? WHERE idFiliere = ?";

	private static final String SQL_SELECT_LIST_FIL = "SELECT filiere.idFiliere, filiere.nomFiliere, filiere.idResponsable, filiere.niveau FROM filiere , responsable WHERE filiere.idResponsable=responsable.idResponsable";

	private static final String SQL_SELECT_LIST_FIL_SANS_MAT = "SELECT DISTINCT filiere.idFiliere, filiere.nomFiliere, filiere.idResponsable, filiere.niveau FROM filiere, filMatEns WHERE filiere.idResponsable = ? AND filiere.idFiliere NOT IN (SELECT filMatEns.idFiliere FROM filMatEns)";

	private static final String SQL_SELECT_LIST_FIL_AVEC_MAT = "SELECT DISTINCT filiere.idFiliere, filiere.nomFiliere, filiere.idResponsable, filiere.niveau FROM filiere, filMatEns WHERE filiere.idFiliere IN (SELECT filMatEns.idFiliere FROM filMatEns)";

	private static final String SQL_SELECT_LIST_FIL_AVEC_MAT_RESP = "SELECT DISTINCT filiere.idFiliere, filiere.nomFiliere, filiere.idResponsable, filiere.niveau FROM filiere, filMatEns WHERE filiere.idResponsable = ? AND filiere.idFiliere IN (SELECT filMatEns.idFiliere FROM filMatEns)";

	private static final String SQL_SELECT_TROUVER_FIL = "SELECT filiere.idFiliere, filiere.nomFiliere, filiere.idResponsable, filiere.niveau FROM filiere WHERE filiere.idFiliere = ?";

	private static final String SQL_SELECT_FIL_RESP = "SELECT filiere.idFiliere, filiere.nomFiliere, filiere.idResponsable, filiere.niveau FROM filiere WHERE filiere.idResponsable = ?";

	private static final String SQL_DELETE_FIL = "DELETE FROM filiere WHERE idFiliere = ?";

	private static EnseignantDao enseignantDao;

	FiliereDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
		enseignantDao = new EnseignantDaoImpl(daoFactory);
	}

	private static Filiere map(ResultSet resultSet) throws SQLException {

		Filiere filiere = new Filiere();

		filiere.setId(resultSet.getInt("idFiliere"));
		filiere.setNom(resultSet.getString("nomFiliere"));
		filiere.setRespFil(enseignantDao.trouverById(resultSet.getInt("idResponsable")));
		filiere.setNiveau(resultSet.getString("niveau"));

		return filiere;

	}

	@Override
	public void creer(Filiere filiere) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_INSERT_FIL, false,
					filiere.getNom(), filiere.getRespFil().getId(), filiere.getNiveau());

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException("Échec de la création de la filiere, aucune ligne ajoutée dans la table.");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

	}

	@Override
	public Filiere trouver(int id) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		Filiere filiere = new Filiere();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_TROUVER_FIL, false,
					id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				filiere = map(resultSet);
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return filiere;

	}

	@Override
	public ArrayList<Filiere> lister() throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		filieres = new ArrayList<Filiere>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_LIST_FIL, false);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				filieres.add(map(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return filieres;
	}

	@Override
	public ArrayList<Filiere> listerFilSansMat(int idResponsable) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		filieres = new ArrayList<Filiere>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_LIST_FIL_SANS_MAT,
					false, idResponsable);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				filieres.add(map(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return filieres;
	}

	@Override
	public ArrayList<Filiere> listerFilAvecMat() throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		filieres = new ArrayList<Filiere>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_LIST_FIL_AVEC_MAT,
					false);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				filieres.add(map(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return filieres;
	}

	@Override
	public void supprimerFiliere(int idFiliere) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_DELETE_FIL, false,
					idFiliere);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException("Échec de la suppression de la filiere");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
	}

	@Override
	public ArrayList<Filiere> listerFilAvecMatResp(int idResp) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		filieres = new ArrayList<Filiere>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion,
					SQL_SELECT_LIST_FIL_AVEC_MAT_RESP, false, idResp);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				filieres.add(map(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return filieres;
	}

	public void modifierFiliere(String nomFiliere, int idResponsable, String niveau, int idFiliere)
			throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_UPDATE_FIL, false,
					nomFiliere, idResponsable, niveau, idFiliere);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException("Échec de la modification de la filiere, aucune ligne modifiée dans la table.");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
	}

	public Filiere filiereResp(int idResponsable) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		Filiere filiere = new Filiere();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_FIL_RESP, false,
					idResponsable);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				filiere = map(resultSet);
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return filiere;
	}

}
