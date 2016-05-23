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

	private static final String SQL_INSERT_MAT = "INSERT INTO matiere (idMatiere, Coefficient, Nombreheure) VALUES (?, ?, ?)";

	private static final String SQL_INSERT_MAT_ENS = "INSERT INTO filMatEns (idMatiere, idFiliere, idResponsable) VALUES (?, ?, ?)";

	private static final String SQL_SELECT_MAT_FIL = "SELECT matiere.idMatiere, Coefficient, Nombreheure FROM filiere , matiere , filMatEns WHERE filiere.idFiliere=filMatEns.idFiliere AND matiere.IdMatiere = filMatEns.idMatiere AND filiere.idFiliere = ? ";

	private static Matiere map(ResultSet resultSet) throws SQLException {

		Matiere matiere = new Matiere();

		matiere.setNom(resultSet.getString("idMatiere"));

		matiere.setCoefficient(resultSet.getInt("Coefficient"));

		matiere.setNbrHeure(resultSet.getInt("Nombreheure"));

		return matiere;

	}

	@Override
	public void ajouter(Matiere matiere) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_INSERT_MAT, false,
					matiere.getNom(), matiere.getCoefficient(), matiere.getNbrHeure());

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
	public void ajouterMatEns(Matiere matiere, String filiere, int idResp) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_INSERT_MAT_ENS, false,
					matiere.getNom(), filiere, idResp);

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
	public ArrayList<Matiere> trouverMatFil(String filiere) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		matieres = new ArrayList<Matiere>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_MAT_FIL, false,
					filiere);

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

}
