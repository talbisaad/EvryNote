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
	private static final String GET_LISTETUDIANT = "select * from etudiant where idClasse= ?";
	private static final String CREAT_STUDENT = "INSERT INTO etudiant (`INE`, `NomEtudiant`, `PrenomEtudiant`, `DateDeNaissance`, `TelEtud`, `EmailEtudiant`, `IdClasse`) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STUDENT = "UPDATE etudiant SET NomEtudiant= ?, PrenomEtudiant= ?, DateDeNaissance= ?, TelEtud= ?, EmailEtudiant= ? WHERE  INE= ?";
	private static final String DELETE_STUDENT="DELETE FROM `evrynote`.`etudiant` WHERE `etudiant`.`INE` = ?";
	public EtudiantDaoImpl(DAOFactory daofactory) {
		// TODO Auto-generated constructor stub

		this.daofactory = daofactory;
		treatementetudiant = new TreatementEtudiant();

	}

	@Override
	public void AddStudent(Etudiant e) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		java.sql.Date mySqlDate = new java.sql.Date(e.getDateDeNaissance().getTime());

		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					CREAT_STUDENT, false, e.getIne(), e.getNomEtudiant(), e.getPrenomEtudiant(), mySqlDate,
					e.getTelEtud(), e.getEmailEtudiant(), e.getClasse().getIdClasse());
			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException("échec de la création de l'étudiant, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException ex) {

			throw new DAOException(ex);

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
					e.getEmailEtudiant(), e.getIne());
			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException("échec de la création de l'étudiant, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException ex) {

			throw new DAOException(ex);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

	}
	

	@Override
	public void DeleteStudent(Etudiant e) throws DAOException {
		// TODO Auto-generated method stub
		

		Connection connexion = null;
		PreparedStatement preparedStatement = null;


		try {
			connexion = daofactory.getConnection();
			preparedStatement = (PreparedStatement) DAOUtilitaire.initialisationRequetePreparee(connexion,
					DELETE_STUDENT, false,e.getIne());
			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException("échec de la suppression de l'étudiant, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException ex) {

			throw new DAOException(ex);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

		
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
