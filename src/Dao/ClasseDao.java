package Dao;

import java.util.ArrayList;

import Beans.Classe;

public interface ClasseDao {
	
	void CreatClass(Classe classe) throws DAOException; 
	void UpdateClass(Classe classe)throws DAOException;
	int getIdClasseByAttributs(Classe classe)throws DAOException;
	ArrayList<Classe> GetClassList()throws DAOException;

}
