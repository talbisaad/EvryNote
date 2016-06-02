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
<title>Modification de la classe</title>
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

		<div id="modification">
			<br> <br> <span class="ttl">Modification de la classe</span><br> <br>


			<form action="ServletClass?action=SaveClass" method="POST">

				<table width="70%">

					<tr>
						<td>Nom classe</td>
						<td>:</td>
						<td><input type="text" name="NomClasse" size="30"
							value="<c:out value="${classe.nomClasse}"/>"></td>
							
							<td><input type="text" name="NomClasseHidden"  style ="display: none;"size="30"
							value="<c:out value="${classe.nomClasse}"/>"></td>
						

						<td>Filière</td>
						<td>:</td>
						<td><select class="selectfilter filterSrch" name="filiere"
							id="filtersh">
								<c:forEach items="${listfiliere}" var="f">
									<c:if test="${f.nom==classe.filiere.nom}">
										<option selected="selected">${f.nom}</option>
									</c:if>
									<c:if test="${f.nom!=classe.filiere.nom}">
										<option>${f.nom}</option>
									</c:if>

								</c:forEach>
						</select></td>
						<td><input type="text" name="filiereHidden"  style ="display: none;"size="30"
							value="<c:out value="${classe.filiere.nom}"/>"></td>
					</tr>
					<tr>
						<td>Niveau</td>
						<td>:</td>
						<td><select class="selectfilter filterSrch" name="niveau"
							id="filtersh">
								<c:forEach items="${ListNiveau}" var="n">
									<c:if test="${n==classe.niveau}">
										<option selected="selected">${n}</option>
									</c:if>
									<c:if test="${n!=classe.niveau}">
										<option>${n}</option>
									</c:if>
								</c:forEach>
						</select></td>
						
						<td><input type="text" name="niveauHidden"  style ="display: none;"size="30"
							value="<c:out value="${classe.niveau}"/>"></td>
							
						<td>Année Universitaire</td>
						<td>:</td>
						<td><input type="text" name="annee" id="filtersh"
							value="<c:out value="${classe.anneeUniversitaire}"/>"></td>
							
							
						<td><input type="text" name="anneeHidden"  style ="display: none;"size="30"
							value="<c:out value="${classe.anneeUniversitaire}"/>"></td>
							
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
							class="submit" /> <input type="reset" value="Réinitialiser"
							class="submit" name="renit" /></td>
					</tr>

				</table>
			</form>

		</div>
	</div>
</body>
</html>