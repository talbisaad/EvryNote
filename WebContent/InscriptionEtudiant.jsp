<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<title>Inscription</title>
<body>
	<div class="header"></div>
	<div class="inner">
		<div class="opr">
			<jsp:include page="menuAdmin.jsp"></jsp:include>
		</div>
		<br> <br> <span class="ttl">Inscription de l'étudiant</span><br>
		<br>
		<fieldset>
			<legend>Rechercher</legend>
			<form action="ServletInscription?action=Search" method="POST">
				<table width="70%">

					<tr>
						<td>INE</td>
						<td>:</td>
						<td><input type="text" class="inpt" name="ine" size="30"></td>
						<td><input type="submit" class="submit" value="Rechercher"
							id="hideshow" /></td>
					</tr>
				</table>
			</form>
		</fieldset>

		<div class="modif" id="content" style="display: none">
			<br> <br> <span class="ttl"></span><br> <br>
			<fieldset>
				<legend>Etudiant</legend>
				<form action="ServletInscription?action=UpdateStudent" method="POST">
					<table width="100%">
						<tr>
							<td><span>INE Etudiant</span></td>
							<td><input class="inpt" type="text" readonly name="idEtud"
								value="<c:out value="${etudiant.ine}"/>"></td>



							<td><span>Date de naissance</span></td>
							<td><input class="inpt" type="date" name="datenaissance"
								value="<c:out value="${etudiant.dateDeNaissance}"/>" /></td>


						</tr>
						<tr>
							<td><span>Nom</span></td>
							<td><input class="inpt" type="text" name="nomEtud"
								value="<c:out value="${etudiant.nomEtudiant}"/>" /></td>

							<td><span>Prénom</span></td>
							<td><input class="inpt" type="text" name="prenomEtud"
								value="<c:out value="${etudiant.prenomEtudiant}"/>" /></td>
						</tr>
						<tr>
							<td><span>E-mail</span></td>
							<td><input class="inpt" type="email" name="mailEtud"
								value="<c:out value="${etudiant.emailEtudiant}"/>" /></td>

							<td><span>Tel</span></td>
							<td><input class="inpt" type="tel" name="numEtud"
								value="<c:out value="${etudiant.telEtud}"/>" /></td>

						</tr>
						<tr>
							<td><span>Mot de passe</span></td>
							<td><input class="inpt" type="password" name="password" /></td>
							<td><span>Confirmer mot de passe</span></td>
							<td><input class="inpt" type="password" name="numEtud" /></td>

						</tr>

					</table>
					<br> <br> <input type="submit" value="Valider"
						class="submit" />
				</form>
			</fieldset>
		</div>
	</div>

	<c:set var="visible" scope="session" />
	<c:choose>
		<c:when test="${visible eq 'true'}">
			<script>
				var div = document.getElementById('content');
				div.style.display = 'block';
			</script>
		</c:when>
	</c:choose>

</body>
</html>