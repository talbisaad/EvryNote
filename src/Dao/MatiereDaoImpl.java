package Dao;

import java.util.ArrayList;
import java.sql.*;
import Dao.DAOUtilitaire;
import Beans.Matiere;

public class MatiereDaoImpl implements MatiereDao {

	private DAOFactory daoFactory;
	ArrayList<Matiere> matieres;
	private static EnseignantDao enseignant;

	MatiereDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
		enseignant = (EnseignantDaoImpl) daoFactory.getEnseignantDao();
	}

	private static final String SQL_INSERT_MAT_ENS = "INSERT INTO filMatEns (idMatiere, idFiliere , idResponsable, Coefficient, nbrHeure, idClasse) VALUES (?, ?, ?, ?, ?, 0)";

	private static final String SQL_INSERT_MAT = "INSERT INTO matiere (nom) VALUES (?)";

	private static final String SQL_UPDATE_MAT_ENS = "UPDATE filMatEns SET idResponsable=? , Coefficient=? , nbrHeure=? WHERE idMatiere=? AND idFiliere = ?";

	private static final String SQL_UPDATE_MAT = "UPDATE matiere SET nom=? WHERE idMatiere=?";

	private static final String SQL_DELETE_MAT_FIL = "DELETE FROM filMatEns WHERE idFiliere = ?";

	private static final String SQL_DELETE_MAT_FIL_ENS = "DELETE FROM filMatEns WHERE idMatiere = ? AND coefficient=? AND nbrHeure=? AND idResponsable = ? AND idFiliere = ?";

	private static final String SQL_SELECT_MAT_FIL_ENS = "SELECT DISTINCT idMatiere FROM filMatEns where idMatiere= ? AND idFiliere= ?";

	private static final String SQL_SELECT_MAT_ID = "SELECT DISTINCT idMatiere, nom FROM matiere where idMatiere = ?";

	private static final String SQL_SELECT_MAT_NOM = "SELECT DISTINCT idMatiere, nom FROM matiere where nom = ?";

	private static final String SQL_SELECT_MAT_FIL = "SELECT DISTINCT matiere.idMatiere, filMatEns.idResponsable, matiere.nom, coefficient, nbrHeure FROM filiere , matiere , filMatEns WHERE filiere.idFiliere=filMatEns.idFiliere AND matiere.IdMatiere = filMatEns.idMatiere AND filiere.idFiliere = ? ";

	private static final String SQL_SELECT_MATS_ENS = "SELECT DISTINCT matiere.idMatiere, matiere.nom FROM matiere, filMatEns where idResponsable = ? AND idClasse = ? AND matiere.idMatiere=filMatEns.idMatiere";

	private static final String SQL_SELECT_MATS_ENS_AVEC_NOTE = "SELECT DISTINCT matiere.idMatiere, matiere.nom FROM matiere, filMatEns where idResponsable = ? AND idClasse = ? AND matiere.idMatiere=filMatEns.idMatiere AND matiere.idMatiere IN (SELECT notematiere.idMatiere FROM notematiere)";

	private static final String SQL_SELECT_MATS_ENS_SANS_NOTE = "SELECT DISTINCT matiere.idMatiere, matiere.nom FROM matiere, filMatEns where idResponsable = ? AND idClasse = ? AND matiere.idMatiere=filMatEns.idMatiere AND matiere.idMatiere NOT IN (SELECT notematiere.idMatiere FROM notematiere)";

	private static final String SQL_SELECT_MATS_CLASSE_FILIERE_AVEC_NOTE = "SELECT matiere.idMatiere, nom FROM matiere, filMatEns WHERE matiere.idMatiere=filMatEns.idMatiere AND filMatEns.idFiliere = ? AND filMatEns.idClasse = ? AND matiere.idMatiere IN (SELECT idMatiere FROM notematiere)";

	private static Matiere map(ResultSet resultSet) throws SQLException {

		Matiere matiere = new Matiere();

		matiere.setId(resultSet.getInt("idMatiere"));

		matiere.setNom(resultSet.getString("nom"));

		matiere.setCoefficient(resultSet.getInt("Coefficient"));

		matiere.setNbrHeure(resultSet.getInt("nbrHeure"));

		matiere.setProf(enseignant.trouverById(resultSet.getInt("idResponsable")));

		return matiere;

	}

	private static Matiere map1(ResultSet resultSet) throws SQLException {

		Matiere matiere = new Matiere();

		matiere.setId(resultSet.getInt("idMatiere"));
		matiere.setNom(resultSet.getString("nom"));

		return matiere;

	}

	private static Matiere map2(ResultSet resultSet) throws SQLException {

		Matiere matiere = new Matiere();

		matiere.setId(resultSet.getInt("idMatiere"));

		return matiere;

	}

	@Override
	public void ajouterMatEns(Matiere matiere, int idFil) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_INSERT_MAT_ENS, false,
					matiere.getId(), idFil, matiere.getProf().getId(), matiere.getCoefficient(), matiere.getNbrHeure());

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException("Échec de la création de la matiere, aucune ligne ajoutée dans la table.");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

	}

	@Override
	public void modifierMatEns(Matiere matiere, int idFil, int idMat) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_UPDATE_MAT_ENS, false,
					matiere.getProf().getId(), matiere.getCoefficient(), matiere.getNbrHeure(), idMat, idFil);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException("Échec de la modification de la matiere, aucune ligne modifiée dans la table.");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
	}

	@Override
	public boolean trouverMatFilEns(int idMat, int idFil) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		matieres = new ArrayList<Matiere>();

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_MAT_FIL_ENS, false,
					idMat, idFil);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				matieres.add(map2(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

		return matieres.isEmpty();
	}

	@Override
	public void ajouter(String matiere) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_INSERT_MAT, false, matiere);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException("aucune ligne ajoutée dans la table matiere");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

	}

	@Override
	public void modifier(int id, String matiere) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_UPDATE_MAT, false, matiere,
					id);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException("aucune ligne modifiée dans la table matiere");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

	}

	@Override
	public Matiere trouveById(int idMat) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		Matiere matiere = new Matiere();

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_MAT_ID, false, idMat);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				matiere = map1(resultSet);
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

		return matiere;

	}

	@Override
	public int trouveByNom(String nom) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		matieres = new ArrayList<Matiere>();

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_MAT_NOM, false, nom);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				matieres.add(map1(resultSet));
			}

			if (matieres.isEmpty()) {
				return 0;
			} else {
				return matieres.get(0).getId();
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

	}

	@Override
	public ArrayList<Matiere> trouverMatFil(int id) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		matieres = new ArrayList<Matiere>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_MAT_FIL, false, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				matieres.add(map(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return matieres;
	}

	@Override
	public void supprimerMatieres(int idFiliere) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_DELETE_MAT_FIL, false,
					idFiliere);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException("Échec de la suppression des matieres");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
	}

	@Override
	public void supprimerMatiere(int idMatiere, int coeff, int nbrHeure, int idEnseignant, int idFiliere)
			throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_DELETE_MAT_FIL_ENS, false,
					idMatiere, coeff, nbrHeure, idEnseignant, idFiliere);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException("Échec de la suppression des matieres");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
	}

	@Override
	public ArrayList<Matiere> listerMatEns(int idEnseignant, int idClasse) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		matieres = new ArrayList<Matiere>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_MATS_ENS, false,
					idEnseignant, idClasse);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				matieres.add(map1(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return matieres;
	}

	@Override
	public ArrayList<Matiere> listerMatEnsAvecNote(int idEnseignant, int idClasse) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		matieres = new ArrayList<Matiere>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_MATS_ENS_AVEC_NOTE,
					false, idEnseignant, idClasse);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				matieres.add(map1(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return matieres;
	}

	@Override
	public ArrayList<Matiere> listerMatEnsSansNote(int idEnseignant, int idClasse) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		matieres = new ArrayList<Matiere>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_MATS_ENS_SANS_NOTE,
					false, idEnseignant, idClasse);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				matieres.add(map1(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return matieres;
	}

	@Override
	public ArrayList<Matiere> listerMatClasseFiliereAvecNote(int idFiliere, int idClasse) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		matieres = new ArrayList<Matiere>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion,
					SQL_SELECT_MATS_CLASSE_FILIERE_AVEC_NOTE, false, idFiliere, idClasse);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				matieres.add(map1(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return matieres;
	}

}
