package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import Beans.Etudiant;

public class InscriptionDaoImpl implements InscriptionDao {

	private DAOFactory daofactory;
	
	private static final String GET_STUDENT_BY_ID="SELECT * from etudiant WHERE INE= ?";
	private static final String UPDATE_STUDENT = "UPDATE etudiant SET NomEtudiant= ?, PrenomEtudiant= ?, DateDeNaissance= ?, TelEtud= ?, EmailEtudiant= ?, Password= ?,Active= 1  WHERE  INE= ?";

	public InscriptionDaoImpl(DAOFactory daoFactory) {
		
		this.daofactory = daoFactory;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Etudiant SearchStudent(int ine) {
		// TODO Auto-generated method stub
		

		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					GET_STUDENT_BY_ID, false, ine);
			ResultSet result = preparedStatement.executeQuery();

			return MapResultsetToEtudiant(result);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
		
	}
	
	@Override
	public void UpdateStudent(Etudiant e) throws DAOException {
		// TODO Auto-generated method stub
		

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		java.sql.Date mySqlDate = new java.sql.Date(e.getDateDeNaissance().getTime());

		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					UPDATE_STUDENT, false, e.getNomEtudiant(), e.getPrenomEtudiant(), mySqlDate, e.getTelEtud(),
					e.getEmailEtudiant(),e.getPasseword(), e.getIne());
			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException("échec de la modification de l'étudiant, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException ex) {

			throw new DAOException(ex);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}
		
	}

	
	public Etudiant MapResultsetToEtudiant(ResultSet resultSet){
		Etudiant etudiant = new Etudiant();
		
		try {
			while(resultSet.next()){
				etudiant.setIne(resultSet.getInt("INE"));
				etudiant.setNomEtudiant(resultSet.getString("NomEtudiant"));
				etudiant.setPrenomEtudiant(resultSet.getString("PrenomEtudiant"));
				etudiant.setDateDeNaissance(resultSet.getDate("DateDeNaissance"));
				etudiant.setTelEtud(resultSet.getInt("TelEtud"));
				etudiant.setEmailEtudiant(resultSet.getString("EmailEtudiant"));
				etudiant.getClasse().setIdClasse(resultSet.getInt("IdClasse"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return etudiant;
	}

	
}
