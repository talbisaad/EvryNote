package Treatment;

import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import Beans.Classe;
import Beans.Etudiant;
import Beans.Filiere;
import Beans.Matiere;
import Dao.ClasseDao;
import Dao.EtudiantDao;
import au.com.bytecode.opencsv.CSVReader;

public class TreatementEtudiant {

	private Classe classe;
	private Etudiant etudiant;
	private Filiere filiere;
	private TreatementClasse treatementclasse;

	public void AddStudent(HttpServletRequest request, EtudiantDao etudiantDao) {
		etudiant = new Etudiant();
		etudiant = MapRequestToObject(request);
		etudiantDao.AddStudent(etudiant);
	}

	public void Upload(HttpServletRequest request, EtudiantDao etudiantDao) {
		ArrayList<Etudiant> etudiantlist = new ArrayList<Etudiant>();
		
		Part filePart;
		try {
			filePart = request.getPart("file");
			String fileName = getSubmittedFileName(filePart);
			@SuppressWarnings("resource")
			CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Saâd TALBI\\Desktop\\" + fileName));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				etudiant = new Etudiant();
				etudiant.setIne(Integer.parseInt(nextLine[0]));
				etudiant.setNomEtudiant(nextLine[1]);
				etudiant.setPrenomEtudiant(nextLine[2]);
				try {
					java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(nextLine[3]);
					etudiant.setDateDeNaissance(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				etudiant.setTelEtud(Integer.parseInt(nextLine[4]));
				etudiant.setEmailEtudiant(nextLine[5]);
				etudiant.getClasse().setIdClasse(Integer.parseInt(nextLine[6]));				
				etudiantlist.add(etudiant);
			}
			
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Retrieves <input type="file" name="file">

		 etudiantDao.Upload(etudiantlist);
	}

	public void UpdateStudent(HttpServletRequest request, EtudiantDao etudiantDao) {
		etudiant = new Etudiant();
		etudiant = MapRequestToObject(request);
		etudiantDao.UpdateStudent(etudiant);

	}

	public void DeleteStudent(HttpServletRequest request, EtudiantDao etudiantDao) {
		etudiant = new Etudiant();
		etudiant.setIne(Integer.parseInt(request.getParameter("IdRow")));
		etudiantDao.DeleteStudent(etudiant);

	}

	public ArrayList<Matiere> GetMatiereForSimulate(HttpServletRequest request, EtudiantDao etudiantDao, Boolean var) {
		if (!var) {
			filiere = new Filiere();
			filiere.setNom(request.getParameter("filiere"));
			filiere.setNiveau(request.getParameter("niveau"));
		}

		return etudiantDao.GetMatiereForSimulate(filiere);

	}

	public void DisplayStudentForModify(HttpServletRequest request, ArrayList<Etudiant> listetudiant) {

		etudiant = new Etudiant();
		etudiant = getEtudiantFromList(request, listetudiant);
		request.setAttribute("etudiant", etudiant);

	}

	public Etudiant getEtudiantFromList(HttpServletRequest request, ArrayList<Etudiant> listetudiant) {

		for (Etudiant i : listetudiant) {
			if (i.getIne() == Integer.parseInt(request.getParameter("IdRow"))) {
				return i;
			}
		}
		return null;

	}

	public ArrayList<Etudiant> GetListOfStudent(HttpServletRequest request, ArrayList<Filiere> listfiliere,
			ClasseDao classeDao, EtudiantDao etudiantDao, Boolean var) {
		if (!var) {
			classe = new Classe();
			treatementclasse = new TreatementClasse();

			classe.setNomClasse(request.getParameter("NomClasse"));
			classe.getFiliere().setNom(request.getParameter("filiere").toString());
			classe.getFiliere()
					.setId(treatementclasse.getFiliereByName(request.getParameter("filiere").toString(), listfiliere));
			classe.setNiveau(request.getParameter("niveau"));
			classe.setMoyenne(0);
			classe.setAnneeUniversitaire(request.getParameter("annee"));
			classe.setIdClasse(treatementclasse.getIdClasseByAttributs(classe, classeDao));
		}
		if (classe.getIdClasse() != 0) {
			return etudiantDao.GetListOfStudent(classe.getIdClasse());
		}
		return null;
	}

	public ArrayList<Etudiant> MapResultSetToClasse(ResultSet resultSet) {
		ArrayList<Etudiant> listetudiant = new ArrayList<Etudiant>();

		try {
			while (resultSet.next()) {

				Etudiant etudiant = new Etudiant();
				etudiant.setIne(resultSet.getInt("INE"));
				etudiant.setNomEtudiant(resultSet.getString("NomEtudiant"));
				etudiant.setPrenomEtudiant(resultSet.getString("PrenomEtudiant"));
				etudiant.setDateDeNaissance(resultSet.getDate("DateDeNaissance"));
				etudiant.setTelEtud(resultSet.getInt("TelEtud"));
				etudiant.setEmailEtudiant(resultSet.getString("EmailEtudiant"));
				etudiant.getClasse().setIdClasse(resultSet.getInt("IdClasse"));
				etudiant.setActive(resultSet.getBoolean("Active"));
				listetudiant.add(etudiant);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listetudiant;

	}

	public Etudiant MapRequestToObject(HttpServletRequest request) {
		etudiant = new Etudiant();
		etudiant.setIne(Integer.parseInt(request.getParameter("idEtud")));

		try {
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("datenaissance"));
			etudiant.setDateDeNaissance(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		etudiant.setNomEtudiant(request.getParameter("nomEtud"));
		etudiant.setPrenomEtudiant(request.getParameter("prenomEtud"));
		etudiant.setEmailEtudiant(request.getParameter("mailEtud"));
		etudiant.setTelEtud(Integer.parseInt(request.getParameter("numEtud")));
		etudiant.getClasse().setIdClasse(classe.getIdClasse());
		return etudiant;
	}

	private static String getSubmittedFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); 
			}
		}
		return null;
	}

}
