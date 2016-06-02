package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Beans.Classe;
import Treatment.TreatementClasse;

public class ClasseDaoImpl implements ClasseDao {

	private static final String SQL_INSERT_CLASSE = "INSERT INTO classe VALUES (NULL, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_CLASSE = "UPDATE classe SET NomClasse= ?, Niveau= ?, Moyenne= ? , IdFiliere= ? , AnneeUniversitaire= ? WHERE IdClasse= ?";
	private static final String SQL_GetId_By_Attributs = "SELECT IdClasse FROM classe WHERE NomClasse= ? AND Niveau= ? AND IdFiliere= ? AND AnneeUniversitaire= ?";
	private static final String SQL_Get_Class_List = "SELECT * FROM classe";
	private DAOFactory daofactory;
	private TreatementClasse treatementclasse;

	public ClasseDaoImpl(DAOFactory daofactory) {

		this.daofactory = daofactory;
		treatementclasse = new TreatementClasse();
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

				throw new DAOException("échec de la création de la classe, aucune ligne ajoutée dans la table.");

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

				throw new DAOException("échec de la mise à jour de la classe, aucune ligne ajoutée dans la table.");

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
	public ArrayList<Classe> GetClassList() throws DAOException {
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
				return treatementclasse.MapResultSetToClasse(result);
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

		return null;
	}

}