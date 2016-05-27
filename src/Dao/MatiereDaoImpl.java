package Dao;

import java.util.ArrayList;
import java.sql.*;
import Dao.DAOUtilitaire;
import Beans.Matiere;

public class MatiereDaoImpl implements MatiereDao {

	private DAOFactory daoFactory;
	ArrayList<Matiere> matieres;

	MatiereDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	private static final String SQL_INSERT_MAT_ENS = "INSERT INTO filMatEns (idMatiere, idFiliere , idResponsable, Coefficient, Nombreheure) VALUES (?, ?, ?, ?, ?)";

	private static final String SQL_INSERT_MAT = "INSERT INTO matiere (idMatiere) VALUES (?)";

	private static final String SQL_SELECT_MAT_FIL = "SELECT DISTINCT matiere.idMatiere, matiere.nom, coefficient, nbrHeure FROM filiere , matiere , filMatEns WHERE filiere.idFiliere=filMatEns.idFiliere AND matiere.IdMatiere = filMatEns.idMatiere AND filiere.idFiliere = ? ";

	private static final String SQL_UPDATE_MAT_FIL = "UPDATE filMatEns SET coefficient=? , nbrHeure = ? WHERE idMatiere = ? AND idFiliere = ? ";

	private static Matiere map(ResultSet resultSet) throws SQLException {

		Matiere matiere = new Matiere();

		matiere.setId(resultSet.getInt("idMatiere"));

		matiere.setNom(resultSet.getString("nom"));

		matiere.setCoefficient(resultSet.getInt("Coefficient"));

		matiere.setNbrHeure(resultSet.getInt("nbrHeure"));

		return matiere;

	}

	@Override
	public void ajouterMatEns(Matiere matiere, int idEns, int idFil) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_INSERT_MAT_ENS, false,
					matiere.getNom(), idFil, idEns, matiere.getCoefficient(), matiere.getNbrHeure());

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
	public void ajouter(String matiere) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_INSERT_MAT, false, matiere);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException("aucune ligne ajoutée dans la table filMatEns");

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
	public void modifierMat(Matiere matiere, String filiere) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_UPDATE_MAT_FIL, false,
					matiere.getCoefficient(), matiere.getNbrHeure(), matiere.getNom(), filiere);

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

}
