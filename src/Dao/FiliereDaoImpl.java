package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Beans.Filiere;

public class FiliereDaoImpl implements FiliereDao {

	private DAOFactory daoFactory;

	private ArrayList<Filiere> filieres;

	private static final String SQL_INSERT_FIL = "INSERT INTO filiere (nomFiliere, idResponsable) VALUES (?, ?)";

	private static final String SQL_SELECT_LIST_FIL = "SELECT filiere.idFiliere, filiere.nomFiliere, filiere.idResponsable FROM filiere , responsable WHERE filiere.idResponsable=responsable.idResponsable";

	private static final String SQL_SELECT_LIST_FIL_SANS_MAT = "SELECT DISTINCT filiere.idFiliere, filiere.nomFiliere, filiere.idResponsable FROM filiere, filMatEns WHERE filiere.idFiliere not in (SELECT filMatEns.idFiliere FROM filMatEns)";

	private static final String SQL_SELECT_LIST_FIL_AVEC_MAT = "SELECT DISTINCT filiere.idFiliere, filiere.nomFiliere, filiere.idResponsable FROM filiere, filMatEns WHERE filiere.idFiliere in (SELECT filMatEns.idFiliere FROM filMatEns)";
	
	private static final String SQL_SELECT_TROUVER_FIL = "SELECT filiere.idFiliere, filiere.nomFiliere, filiere.idResponsable FROM filiere WHERE filiere.idFiliere = ?";
	
	private static EnseignantDao enseignantDao;

	FiliereDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
		enseignantDao = new EnseignantDaoImpl(daoFactory);
	}

	private static Filiere map(ResultSet resultSet) throws SQLException {

		Filiere filiere = new Filiere();

		filiere.setId(resultSet.getInt("idFiliere"));
		filiere.setNom(resultSet.getString("nomFiliere"));
		filiere.setRespFil(enseignantDao.trouver(resultSet.getInt("idResponsable")));

		return filiere;

	}

	@Override
	public void creer(Filiere filiere) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_INSERT_FIL, false,
					filiere.getNom(), filiere.getRespFil().getId());

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

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_TROUVER_FIL, false,id);

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
	public ArrayList<Filiere> listerFilSansMat() throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		filieres = new ArrayList<Filiere>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_LIST_FIL_SANS_MAT,
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

}
