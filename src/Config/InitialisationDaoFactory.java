package Config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import Dao.DAOFactory;

public class InitialisationDaoFactory implements ServletContextListener {

	private DAOFactory daoFactory;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		/* Récupération du ServletContext lors du chargement de l'application */
		ServletContext servletContext = event.getServletContext();
		/* Instanciation de notre DAOFactory */
		this.daoFactory = DAOFactory.getInstance();
		/*
		 * Enregistrement dans un attribut ayant pour portée toute l'application
		 */
		servletContext.setAttribute("daofactory", this.daoFactory);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		/* Rien à réaliser lors de la fermeture de l'application... */
	}
}
