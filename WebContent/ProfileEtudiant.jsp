<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<title></title>
</head>
<body>
	<div class="modif" id="lign">
		<fieldset>
			<legend>Ajouter �tudiant</legend>
			<form action="ServletEtudiant?action=AddStudent" method="POST">
				<table width="100%">
					<tr>
						<td><span>INE Etudiant</span></td>
						<td><input class="inpt" type="text" name="idEtud" /></td>
						<td><span>Date de naissance</span></td>
						<td><input class="inpt" type="date" name="datenaissance" /></td>
					</tr>
					<tr>
						<td><span>Nom</span></td>
						<td><input class="inpt" type="text" name="nomEtud" /></td>
						<td><span>Pr�nom</span></td>
						<td><input class="inpt" type="text" name="prenomEtud" /></td>
					</tr>
					<tr>
						<td><span>E-mail</span></td>
						<td><input class="inpt" type="email" name="mailEtud" /></td>
						<td><span>Tel</span></td>
						<td><input class="inpt" type="tel" name="numEtud" /></td>
					</tr>


				</table>
				<br>
				<br> <input type="submit" value="Valider" class="submit" />
			</form>
		</fieldset>
	</div>
</body>
</html>