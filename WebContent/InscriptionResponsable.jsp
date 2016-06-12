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
		<br> <br> <span class="ttl">Inscription du
			résponsable</span><br> <br>
		<fieldset>
			<legend>Résponsable</legend>
			<form action="ServletInscription?action=SaveResponsable" method="POST">
				<table width="70%">

					<tr>
						<td>Nom</td>
						<td>:</td>
						<td><input type="text" class="inpt" name="nom" size="30"></td>
						<td>Prénom</td>
						<td>:</td>
						<td><input type="text" class="inpt" name="prenom" size="30"></td>
					</tr>



					<tr>
						<td>Login</td>
						<td>:</td>
						<td><input type="text" class="inpt" name="login" size="30"></td>
						<td>Mot de passe</td>
						<td>:</td>
						<td><input type="password" class="inpt" name="password"
							size="30"></td>
					</tr>
				</table>
				<table>
					<tr>
						<td><input type="checkbox" name="droit" value="CD" />Chef de
							département</td>
						<td><input type="checkbox" name="droit" value="RF"/>Résponsable de
							filiere</td>
						
					</tr>


					<tr>
						
						<td></td>
						<td></td>
						<td></td>
						<td><input type="submit" class="submit" value="Rechercher"
							id="hideshow" /></td>
					</tr>

				</table>
			</form>

		</fieldset>
	</div>
</body>
</html>