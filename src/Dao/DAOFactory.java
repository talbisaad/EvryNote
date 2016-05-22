package Dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class DAOFactory {

	private static final String FICHIER_PROPERTIES = "/Dao/dao.properties";

	private static final String PROPERTY_URL = "url";

	private static final String PROPERTY_DRIVER = "driver";

	private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";

	private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";

	private String url;

	private String username;

	private String password;

	DAOFactory(String url, String username, String password) {

		this.url = url;

		this.username = username;

		this.password = password;

	}

	/*
	 * 
	 * Méthode chargée de récupérer les informations de connexion à la base de
	 * 
	 * données, charger le driver JDBC et retourner une instance de la Factory
	 * 
	 */

	public static DAOFactory getInstance() throws DAOConfigurationException {

		Properties properties = new Properties();

		String url;

		String driver;

		String nomUtilisateur;

		String motDePasse;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

		if (fichierProperties == null) {

			throw new DAOConfigurationException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");

		}

		try {

			properties.load(fichierProperties);

			url = properties.getProperty(PROPERTY_URL);

			driver = properties.getProperty(PROPERTY_DRIVER);

			nomUtilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);

			motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);

		} catch (IOException e) {

			throw new DAOConfigurationException("Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e);

		}

		try {

			Class.forName(driver);

		} catch (ClassNotFoundException e) {

			throw new DAOConfigurationException("Le driver est introuvable dans le classpath.", e);

		}

		DAOFactory instance = new DAOFactory(url, nomUtilisateur, motDePasse);

		return instance;

	}

			/* Méthode chargée de fournir une connexion à la base de données */

			/* package */ java.sql.Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/evrynote?user=root&password=root");

	}

	/*
	 * 
	 * Méthodes de récupération de l'implémentation des différents DAO (un seul
	 * 
	 * pour le moment)
	 * 
	 */

	public FiliereDao getFiliereDao() {

		return new FiliereDaoImpl(this);

	}

	public EnseignantDao getEnseignantDao() {

		return new EnseignantDaoImpl(this);

	}

}
