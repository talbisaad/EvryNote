package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Beans.Etudiant;
import Treatment.TreatementEtudiant;

public class EtudiantDaoImpl implements EtudiantDao {

	private DAOFactory daofactory;
	private TreatementEtudiant treatementetudiant;
private static final String GET_LISTETUDIANT="select * from etudiant where idClasse= ?"; 
	public EtudiantDaoImpl(DAOFactory daofactory) {
		// TODO Auto-generated constructor stub

		this.daofactory = daofactory;
		treatementetudiant= new TreatementEtudiant();

	}

	@Override
	public ArrayList<Etudiant> GetListOfStudent(int idClasse) throws DAOException {

		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					GET_LISTETUDIANT, false, idClasse);
			ResultSet result = preparedStatement.executeQuery();

			return treatementetudiant.MapResultSetToClasse(result);
			
		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}		
				
	}

}
