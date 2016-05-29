package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Classe;
import Dao.ClasseDao;
import Dao.DAOFactory;
import Treatment.TreatementClasse;

/**
 * Servlet implementation class ServletClass
 */
@WebServlet("/ServletClass")
public class ServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
TreatementClasse treaitmentClasse;
ClasseDao classeDao;
    
	public void init(){
		
		treaitmentClasse= new TreatementClasse();
		classeDao=  ((DAOFactory) getServletContext().getAttribute("daofactory")).getClasseDao();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/CreerClass.jsp").forward(request, response);
		//treaitmentClasse.CreatClass(request, classeDao);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		treaitmentClasse.CreatClass(request, classeDao);
		this.getServletContext().getRequestDispatcher("/CreerClass.jsp").forward(request, response);

	}

}
