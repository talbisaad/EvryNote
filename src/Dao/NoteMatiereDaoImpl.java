package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Beans.Matiere;
import Beans.NoteMatiere;

public class NoteMatiereDaoImpl implements NoteMatiereDao {

	private DAOFactory daoFactory;
	private static EtudiantDao etudiant;
	private static MatiereDao matiere;

	NoteMatiereDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
		etudiant = (EtudiantDaoImpl) daoFactory.getEtudiantDao();
		matiere = (MatiereDaoImpl) daoFactory.getMatiereDao();
	}

	// selectionner les notes des etudiants pour une matiere
	private static final String SQL_SELECT_NOTE_ETUDAINTS = "SELECT notematiere.INE, notematiere.NoteMatiere, notematiere.idMatiere FROM notematiere,etudiant WHERE idMatiere = ? AND idResponsable = ? AND etudiant.INE=notematiere.INE";

	private static final String SQL_INSERT_NOTE_ETUDAINTS = "INSERT INTO notematiere (INE, idMatiere, NoteMatiere, idResponsable) VALUES (?, ?, ?, ?)";

	private static final String SQL_UPDATE_NOTE_ETUDAINTS = "UPDATE notematiere SET NoteMatiere = ? WHERE INE=? AND idMatiere=? AND idResponsable=? ";

	private static NoteMatiere map(ResultSet resultSet) throws SQLException {
		NoteMatiere noteMatiere = new NoteMatiere();
		noteMatiere.setEtudiant(etudiant.trouverEtudiant(resultSet.getInt("INE")));
		noteMatiere.setNote(resultSet.getFloat("NoteMatiere"));
		noteMatiere.setMatiere(matiere.trouveById(resultSet.getInt("idMatiere")));
		return noteMatiere;

	}

	@Override
	public ArrayList<NoteMatiere> listerNoteClasseEtudaints(int idMatiere, int idResponsable) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		ArrayList<NoteMatiere> noteMatieres = new ArrayList<NoteMatiere>();

		try {

			/* Récupération d'une connexion depuis la Factory */

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_NOTE_ETUDAINTS, false,
					idMatiere, idResponsable);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				noteMatieres.add(map(resultSet));
			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);

		}

		return noteMatieres;
	}

	@Override
	public void insererNoteMatiere(int ine, int idMatiere, float note, int idResponsable) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_INSERT_NOTE_ETUDAINTS, false,
					ine, idMatiere, note, idResponsable);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException("Échec de l'insertion des notes, aucune ligne ajoutée dans la table.");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

	}

	@Override
	public void modifierNoteMatiere(float note, int ine, int idMatiere, int idResponsable) throws DAOException {

		Connection connexion = null;

		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_UPDATE_NOTE_ETUDAINTS, false,
					note, ine, idMatiere, idResponsable);

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {

				throw new DAOException("Échec de l'insertion des notes, aucune ligne ajoutée dans la table.");

			}

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {

			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);

		}

	}

}
