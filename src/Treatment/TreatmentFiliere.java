package Treatment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.ResponsableFiliere;

public class TreatmentFiliere {

	String msg;

	public void creerFiliere(HttpServletRequest request)
			throws ServletException, IOException {
		String nomFil = request.getParameter("nomFil");
		String responsable = request.getParameter("respFil");
		
		if (nomFil != null && responsable != null && nomFil == "" && responsable == "") {
			
		}

	}
	
	
	
	

}
