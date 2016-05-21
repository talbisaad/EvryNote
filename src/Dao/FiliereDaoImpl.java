package Dao;

import Beans.Filiere;

public class FiliereDaoImpl implements FiliereDao {
	
    private DAOFactory          daoFactory;

    FiliereDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

	@Override
	public void creer(Filiere filiere) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Filiere trouver(String nom) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
