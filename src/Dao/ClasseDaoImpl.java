package Dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import Beans.Classe;

public class ClasseDaoImpl implements ClasseDao {

	private static final String SQL_INSERT_CLASSE="INSERT INTO classe VALUES (NULL, ?, ?, ?, ?, ?)";
	private DAOFactory daofactory;

	public ClasseDaoImpl(DAOFactory daofactory) {

		this.daofactory = daofactory;
	}

	@Override
	public void creer(Classe classe) throws DAOException {
		
		Connection connexion= null;
		PreparedStatement preparedStatement=null;
		
		try {
			connexion = daofactory.getConnection() ;
			preparedStatement=(PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_INSERT_CLASSE, false, classe.getNomClasse(),classe.getNiveau(),classe.getMoyenne(),classe.getFiliere().getId(),classe.getAnneeUniversitaire());
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
}