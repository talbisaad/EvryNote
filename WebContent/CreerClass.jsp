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
<title>Cr�ation de la classe</title>
</head>

<body>
	<div class="header">
		<ul>
			<li><a href="#" class="dcnx">Deconnexion</a></li>
		</ul>
	</div>
	<div class="inner">
		<div class="opr" id="importFil">
			<jsp:include page="menuAdmin.jsp"></jsp:include>
		</div>

		<div id="creation">
			<br> <br> <span class="ttl">Creer une classe</span><br>
			<br>


			<form action="ServletClass?action=CreerClass" method="POST">

				<table width="70%">

					<tr>
						<td>Nom classe</td>
						<td>:</td>
						<td><input type="text" name="NomClasse" size="30" class="inpt"></td>

						<td>Fili�re</td>
						<td>:</td>
						<td><select class="selectfilter filterSrch" name="filiere"
							id="filtersh">
								<c:forEach items="${listfiliere}" var="f">
									<option>${f.nom}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>Niveau</td>
						<td>:</td>
						<td><select class="selectfilter filterSrch" name="niveau"
							id="filtersh">
								<c:forEach items="${ListNiveau}" var="n">
									<option>${n}</option>
								</c:forEach>
						</select></td>
						<td>Ann�e Universitaire</td>
						<td>:</td>
						<td><input type="text" name="annee" id="filtersh" class="inpt" /></td>
					</tr>

					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr id="lign">
						<td></td>
						<td></td>
						<td><input type="submit" name="valider" value="Valider"
							class="submit" /> <input type="reset" value="R�initialiser"
							class="submit" name="renit" /></td>
					</tr>

				</table>
			</form>

		</div>
	</div>


	<!-- Affichage de la classe  -->


	<div class="inner" id="affichage" style="display: none">
		<br> <br> <span class="ttl">Affiche Classe</span><br> <br>
		<form action="Controle?act=ajouteremp" method="POST">

			<table width="70%">
				<tr>
					<td>Nom classe</td>
					<td>:</td>
					<td><label class="selectfilter filterSrch" id="filtersh" ><c:out
								value="${classe.nomClasse}" /></label></td>

					<td>Fili�re</td>
					<td>:</td>
					<td><label class="selectfilter filterSrch" id="filtersh"><c:out
								value="${classe.filiere.nom}" /></label></td>
				</tr>
				<tr>
					<td>Niveau</td>
					<td>:</td>
					<td><label class="selectfilter filterSrch" id="filtersh"><c:out
								value="${classe.niveau}" /></label></td>
					<td>Ann�e Universitaire</td>
					<td>:</td>
					<td><label class="selectfilter filterSrch" id="filtersh"><c:out
								value="${classe.anneeUniversitaire}" /></label></td>
				</tr>

				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr id="lign">
					<td></td>
					<td></td>

				</tr>

			</table>
		</form>
		<table>
			<tr>
				<td>
					<form action="ServletClass?action=ModifierClass" method="POST">
						<input type="submit" name="Modifier" value="Modifier"
							class="submit" />
					</form>
				</td>
				<td>
					<form action="ServletClass?action=Annuler" method="POST">
						<input type="submit" name="Annuler" value="Annuler"
							class="submit" />

					</form>
				</td>
			</tr>
		</table>
	</div>

	<script>
		<c:if test="${creation eq 'false'}">
		creation.style.display = "none";
		</c:if>
		<c:if test="${affichage eq 'true'}">
		affichage.style.display = "block";
		</c:if>
	</script>

</body>
</html>

