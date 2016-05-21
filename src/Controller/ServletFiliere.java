package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Enseignant;
import Beans.ResponsableFiliere;
import Treatment.TreatmentFiliere;

public class ServletFiliere extends HttpServlet {
	ArrayList<Enseignant> resps = new ArrayList<Enseignant>();
	TreatmentFiliere treatmentFiliere = new TreatmentFiliere();

	public void init() {
		Enseignant resp1 = new Enseignant(1, "zahir", "badr");
		Enseignant resp2 = new Enseignant(2, "talbi", "saad");
		resps.add(resp1);
		resps.add(resp2);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("resps", resps);
		this.getServletContext().getRequestDispatcher("/CreerFil.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomFil = request.getParameter("nomFil");
		ResponsableFiliere responsable = (ResponsableFiliere) request.getAttribute("respFil");
		System.out.println(responsable.toString());
		request.setAttribute("resps", resps);
		this.getServletContext().getRequestDispatcher("/CreerFil.jsp").forward(request, response);
	}

}
