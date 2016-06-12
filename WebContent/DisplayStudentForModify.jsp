<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<title>Modification</title>
</head>
<body>
<div class="header">
		<ul>
			<li><a href="#" class="dcnx">Deconnexion</a></li>
		</ul>
	</div>
	<div class="inner">
		<div class="opr">
			<jsp:include page="menuAdmin.jsp"></jsp:include>
		</div>
	
	<div class="modif" id="lign">
		<br> <br> <span class="ttl">Modification de l'étudiant</span><br>
		<br>
		<fieldset>
			<legend>Etudiant</legend>
			<form action="ServletEtudiant?action=UpdateStudent" method="POST">
				<table width="100%">
					<tr>
						<td><span>INE Etudiant</span></td>
						<td><input class="inpt" type="text"  readonly name="idEtud"
							value="<c:out value="${etudiant.ine}"/>"> </td>
						
				
						<td>	<input class="inpt"
							type="text" name="HiddenidEtud" style="display: none" 
							value="<c:out value="${etudiant.ine}"/>">
							</td>
						<td><span>Date de naissance</span></td>
						<td><input class="inpt" type="date" name="datenaissance"
							value="<c:out value="${etudiant.dateDeNaissance}"/>" /> </td>
							
							<td><input
							class="inpt" type="date" style="display: none" name="Hiddendatenaissance"
							value="<c:out value="${etudiant.dateDeNaissance}"/>" /></td>
					</tr>
					<tr>
						<td><span>Nom</span></td>
						<td><input class="inpt" type="text" name="nomEtud"
							value="<c:out value="${etudiant.nomEtudiant}"/>" /> 
							</td>
							<td><input
							class="inpt" type="text" style="display: none" name="HiddennomEtud"
							value="<c:out value="${etudiant.nomEtudiant}"/>" />
							</td>
						<td><span>Prénom</span></td>
						<td><input class="inpt" type="text" name="prenomEtud"
							value="<c:out value="${etudiant.prenomEtudiant}"/>" />
							</td>
							 <td><input
							class="inpt" type="text" style="display: none" name="HiddenprenomEtud"
							value="<c:out value="${etudiant.prenomEtudiant}"/>" /></td>
					</tr>
					<tr>
						<td><span>E-mail</span></td>
						<td><input class="inpt" type="email" name="mailEtud"
							value="<c:out value="${etudiant.emailEtudiant}"/>" /> 
							</td>
							<td><input
							class="inpt" style="display: none" type="email" name="HiddenmailEtud"
							value="<c:out value="${etudiant.emailEtudiant}"/>" />
							</td>
							
						<td><span>Tel</span></td>
						<td><input class="inpt" type="tel" name="numEtud"
							value="<c:out value="${etudiant.telEtud}"/>" />
							</td>
							 <td><input
							class="inpt" type="tel" style="display: none" name="HiddennumEtud"
							value="<c:out value="${etudiant.telEtud}"/>" /></td>
					</tr>

				</table>
				<br> <br> <input type="submit" value="Valider"
					class="submit" />
			</form>
		</fieldset>
	</div>
	</div>
</body>
</html>