package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.Enseignant;
import Beans.Filiere;

public class FiliereDaoImpl implements FiliereDao {

	private DAOFactory daoFactory;

	private static final String SQL_INSERT_FIL = "INSERT INTO filiere (idFiliere, idResponsable) VALUES (?, ?)";

	private static EnseignantDao enseignantDao;

	FiliereDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
		enseignantDao = new EnseignantDaoImpl(daoFactory);
	}

	private static Filiere map(ResultSet resultSet) throws SQLException {

		Filiere filiere = new Filiere();

		filiere.setNom(resultSet.getString("nom"));
		filiere.setRespFil(enseignantDao.trouver(resultSet.getInt("idResponsable")));

		return filiere;

	}

	@Override
	public void creer(Filiere filiere) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet valeursAutoGenerees = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_INSERT_FIL, false, filiere.getNom(),
					filiere.getRespFil().getId());

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
	public Filiere trouver(String nom) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
