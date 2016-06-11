package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Beans.Classe;
import Beans.Filiere;
import Treatment.TreatementClasse;

public class ClasseDaoImpl implements ClasseDao {

	private static final String SQL_INSERT_CLASSE = "INSERT INTO classe VALUES (NULL, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_CLASSE = "UPDATE classe SET NomClasse= ?, Niveau= ?, Moyenne= ? , IdFiliere= ? , AnneeUniversitaire= ? WHERE IdClasse= ?";
	private static final String SQL_GetId_By_Attributs = "SELECT IdClasse FROM classe WHERE NomClasse= ? AND Niveau= ? AND IdFiliere= ? AND AnneeUniversitaire= ?";
	private static final String SQL_Get_Class_List = "SELECT * FROM classe ORDER BY Niveau,AnneeUniversitaire DESC,NomClasse";
	private static final String SQL_DELETE_Classe = "DELETE FROM classe WHERE IdClasse= ? ";
	// mes modifs (ZAHIR BADR)
	private static final String SQL_SELECT_CLASSES_FILIERE = "SELECT idClasse, nomClasse, classe.idFiliere FROM classe WHERE classe.idFiliere= ? ";
	private static final String SQL_SELECT_TROUVER_CLASSE = "SELECT idClasse, nomClasse, classe.idFiliere FROM classe WHERE idClasse= ? ";
	private static final String SQL_SELECT_CLASSES_ENSEIGNANT = "SELECT DISTINCT classe.idClasse, nomClasse, classe.idFiliere FROM classe, filMatEns WHERE filMatEns.idResponsable= ? AND classe.idFiliere=filMatEns.idFiliere";

	private TreatementClasse treatementclasse;
	private DAOFactory daofactory;
	private static FiliereDao filiere;

	public ClasseDaoImpl(DAOFactory daofactory) {
		this.daofactory = daofactory;
		filiere = (FiliereDaoImpl) daofactory.getFiliereDao();
	}

	private static Classe map(ResultSet resultSet) throws SQLException {
		Classe classe = new Classe();
		classe.setIdClasse(resultSet.getInt("idClasse"));
		classe.setNomClasse(resultSet.getString("nomClasse"));
		classe.setFiliere(filiere.trouver(resultSet.getInt("classe.idFiliere")));
		return classe;
	}

	@Override
	public void CreatClass(Classe classe) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					SQL_INSERT_CLASSE, false, classe.getNomClasse(), classe.getNiveau(), classe.getMoyenne(),
					classe.getFiliere().getId(), classe.getAnneeUniversitaire());
			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException("�chec de la cr�ation de la classe, aucune ligne ajout�e dans la table.");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

	}

	public void DeleteClass(Classe classe) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					SQL_DELETE_Classe, false, classe.getIdClasse());
			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException("�chec de la supression de la classe, aucune ligne ajout�e dans la table");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

	}

	@Override
	public void UpdateClass(Classe classe) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					SQL_UPDATE_CLASSE, false, classe.getNomClasse(), classe.getNiveau(), classe.getMoyenne(),
					classe.getFiliere().getId(), classe.getAnneeUniversitaire(), classe.getIdClasse());
			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException("�chec de la mise � jour de la classe, aucune ligne ajout�e dans la table.");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

	}

	@Override
	public int getIdClasseByAttributs(Classe classe) throws DAOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					SQL_GetId_By_Attributs, false, classe.getNomClasse(), classe.getNiveau(),
					classe.getFiliere().getId(), classe.getAnneeUniversitaire());
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			return result.getInt("IdClasse");

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

	}

	@Override
	public ArrayList<Classe> GetClassList(ArrayList<Filiere> filierelist) throws DAOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					SQL_Get_Class_List, false);
			ResultSet result = preparedStatement.executeQuery();

			if (result != null) {
				return treatementclasse.MapResultSetToClasse(result, filierelist);
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

		return null;
	}

	
	//zahir badr
	@Override
	public Classe trouverClasse(int idClasse) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		Classe classe = new Classe();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daofactory.getConnection();

			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					SQL_SELECT_TROUVER_CLASSE, false, idClasse);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				classe = map(resultSet);
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return classe;
	}

	@Override
	public ArrayList<Classe> classesFiliere(int idFiliere) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		ArrayList<Classe> classes = new ArrayList<Classe>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daofactory.getConnection();

			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					SQL_SELECT_CLASSES_FILIERE, false, idFiliere);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				classes.add(map(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return classes;
	}

	@Override
	public ArrayList<Classe> classesEnseignant(int idEnseignant) throws DAOException {
		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		ArrayList<Classe> classes = new ArrayList<Classe>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daofactory.getConnection();

			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					SQL_SELECT_CLASSES_ENSEIGNANT, false, idEnseignant);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				classes.add(map(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return classes;
	}
}