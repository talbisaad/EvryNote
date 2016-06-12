package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Etudiant;
import Dao.DAOFactory;
import Dao.InscriptionDao;
import Treatment.TreatementInscription;


@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InscriptionDao inscriptionDao;
	private TreatementInscription treatementInscription;
	Etudiant etudiant;
  
	
	public void init(){
		this.inscriptionDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getInscriptionDao();
		treatementInscription= new TreatementInscription();
		etudiant= new Etudiant();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getRequestURL().substring(31);
		
		if(page.equals("InscriptionEtudiant")){
			this.getServletContext().getRequestDispatcher("/InscriptionEtudiant.jsp").forward(request, response);
			
		}else{
			
			this.getServletContext().getRequestDispatcher("/InscriptionResponsable.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		switch(action){
		
		case "Search":
			etudiant=treatementInscription.SearchStudent(request, inscriptionDao);
			request.setAttribute("etudiant", etudiant);
			request.setAttribute("visible", "true");
			this.getServletContext().getRequestDispatcher("/InscriptionEtudiant.jsp").forward(request, response);
			break;
			
		case "UpdateStudent":
			treatementInscription.UpdateStudent(request, inscriptionDao);
			this.getServletContext().getRequestDispatcher("/InscriptionEtudiant.jsp").forward(request, response);
			break;
		
		case "SaveResponsable":	
			
			treatementInscription.InsertResponsable(request, inscriptionDao);
			this.getServletContext().getRequestDispatcher("/InscriptionResponsable.jsp").forward(request, response);
			break;
			}
		
		
		}
		
		
		
		
		
		
		
		
		
	}


