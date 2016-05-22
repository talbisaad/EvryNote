package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Classe;

/**
 * Servlet implementation class ServletClass
 */
@WebServlet("/ServletClass")
public class ServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	Classe classe ;
    
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		public Classe( String nomClasse, String niveau, float moyenne, String anneeUniversitaire,
//				int idFiliere) 
		classe= new Classe(request.getParameter("NomClasse"),request.getParameter("niveau"),0,request.getParameter("annee"),request.getParameter("filiere").toString());
	System.out.println(classe.toString());
	}

}
