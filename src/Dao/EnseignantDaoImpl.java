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

	ArrayList<Enseignant> enseignants = new ArrayList<Enseignant>();

	private static final String SQL_SELECT_PAR_ID = "SELECT idResponsable, nom, prenom, CD, RF FROM responsable, droit ";

	private static Enseignant map(ResultSet resultSet) throws SQLException {

		Enseignant enseignant = new Enseignant();

		enseignant.setId(resultSet.getInt("idResponsable"));

		enseignant.setNom(resultSet.getString("nom"));

		enseignant.setPrenom(resultSet.getString("prenom"));

		enseignant.setChefDepart(resultSet.getBoolean("CD"));

		enseignant.setReponsableFil(resultSet.getBoolean("RF"));

		return enseignant;

	}

	EnseignantDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Enseignant enseignant) throws DAOException {

	}

	@Override
	public ArrayList<Enseignant> trouver() throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		Enseignant enseignant = null;

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_PAR_ID, false);

			resultSet = preparedStatement.executeQuery();

			/*
			 * Parcours de la ligne de données de l'éventuel ResulSet retourné
			 */

			if (resultSet.next()) {

				enseignant = map(resultSet);
				enseignants.add(enseignant);

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

}
