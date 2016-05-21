package Treatment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.ResponsableFiliere;

public class TreatmentFiliere {

	String msg;

	public void creerFiliere(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomFil = request.getParameter("nomFil");
		ResponsableFiliere responsable = (ResponsableFiliere) request.getAttribute("respFil");
		System.out.println(responsable.toString());
		if (nomFil != null && responsable != null) {

		}

	}

}
