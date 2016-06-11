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
<title>Géstion de la classe</title>
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
		<br> <br> <span class="ttl">Gestion de la classe</span><br>
		<br>
		<fieldset>
			<legend>Rechercher</legend>
			<form action="ServletEtudiant?action=GestionClass" method="POST">
				<div>

					<table width="70%">

						<tr>
							<td>Nom classe</td>
							<td>:</td>
							<td><input type="text" name="NomClasse" size="30"></td>

							<td>Filière</td>
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
							<td>Année Universitaire</td>
							<td>:</td>
							<td><input type="text" name="annee" id="filtersh" /></td>
						</tr>


						<tr>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td><input type="submit" class="submit" value="Rechercher" />
							</td>
						</tr>

					</table>
				</div>
			</form>
		</fieldset>
		<div id="lign" class="tables">
			<table cellpadding="0" cellspacing="0" class="tabs">
				<thead>
					<tr>
						<td>INE Etudiant</td>
						<td>Nom</td>
						<td>Prénom</td>
						<td>Modifier</td>
						<td>Supprimer</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach begin="0" end="${lengh}" step="1"
						varStatus="loopCounter" items="${listetudiant}" var="e">
						<tr>

							<td><c:out value="${e.ine}" /></td>
							<td><c:out value="${e.nomEtudiant}" /></td>
							<td><c:out value="${e.prenomEtudiant}" /></td>
							<td><form
									action="ServletEtudiant?action=ModifierEtudiantFromListEtudiant"
									method="POST">
									<button
										style="border: 0px solid black; background-color: transparent;"
										type="submit" name="Modifier">
										<img alt="modify" class="modifyicone" src="CSS/modify.png">
									</button>
									<input type="hidden" name="IdRow" value="${e.ine}" />
								</form></td>
							<td><form action="ServletEtudiant?action=SupprimerEtudiant"
									method="POST">
									<button
										style="border: 0px solid black; background-color: transparent;"
										type="submit" name="Modifier">
										<img alt="delete" class="deleteicone" src="CSS/delete.png">
									</button>
									<input type="hidden" name="IdRow" value="${e.ine}" />
								</form></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div id="lign">
			<input type="hidden" name="listetudiant" value="${listetudiant}" />
			<input type="button" id="hideshow" value="Ajouter" class="submit" />
			<input type="file" id="fichier" name="fichier" value="" />
		</div>
		<div id="content" style="display: none">
			<dir>
				<jsp:include page="ProfileEtudiant.jsp"></jsp:include>
			</dir>
		</div>
	</div>
	<script>
		var button = document.getElementById('hideshow');

		button.onclick = function() {
			var div = document.getElementById('content');
			if (div.style.display !== 'none') {
				div.style.display = 'none';
				button.value = "Ajouter";
			} else {
				button.value = "Annuler";
				div.style.display = 'block';

			}
		};
	</script>
</body>
</html>