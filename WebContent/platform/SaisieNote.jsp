<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<title>La saisie des notes</title>
</head>

<%
	int i = 0;
%>

<body>
	<div class="header">
		<ul>
			<li><a href="/EvryNote/Deconnexion" class="dcnx">Deconnexion</a></li>
		</ul>
	</div>
	<div class="inner">
		<div class="opr">
			<c:if
				test="${!empty sessionScope.sessionUtilisateur && sessionScope.sessionUtilisateur.reponsableFil && sessionScope.sessionUtilisateur.chefDepart}">
				<jsp:include page="/menuAdmin.jsp"></jsp:include>
			</c:if>
			<c:if
				test="${!empty sessionScope.sessionUtilisateur && !sessionScope.sessionUtilisateur.reponsableFil && !sessionScope.sessionUtilisateur.chefDepart}">
				<jsp:include page="/menuProf.jsp"></jsp:include>
			</c:if>
			<c:if
				test="${!empty sessionScope.sessionUtilisateur && sessionScope.sessionUtilisateur.reponsableFil && !sessionScope.sessionUtilisateur.chefDepart}">
				<jsp:include page="/menuProfResp.jsp"></jsp:include>
			</c:if>
			<c:if
				test="${!empty sessionScope.sessionUtilisateur && !sessionScope.sessionUtilisateur.reponsableFil && sessionScope.sessionUtilisateur.chefDepart}">
				<jsp:include page="/menuCDProf.jsp"></jsp:include>
			</c:if>
		</div>
		<br> <br> <span class="ttl">La saisie des notes</span><br>
		<br>
		<form action="<c:url value="/platform/SaisieNote" />" method="POST">
			<table>
				<tr>
					<td><span>Choisir une classe </span></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><select class="selectfilter filterSrch" id="classe"
						name="classe" onchange="this.form.submit();">
							<option></option>
							<c:forEach items="${classes}" var="classe">
								<option value="<c:out value="${classe.idClasse}"/>">${classe.nomClasse}</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
		</form>
		<br>
		<form action="<c:url value="/platform/SaisieNote" />" method="POST">
			<table>
				<tr>
					<td><span>Choisir une mati�re</span></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><select class="selectfilter filterSrch" id="matiere"
						name="matiere">
							<c:forEach items="${matieres}" var="matiere">
								<option value="<c:out value="${matiere.id}"/>">${matiere.nom}</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<br> <br>
			<fieldset>
				<legend>Saisir les notes : </legend>
				<br>
				<div class="tables">
					<table border="1" cellpadding="0" cellspacing="0" class="tabs">
						<caption class="ttl">La Liste des etudiants :</caption>

						<thead>
							<tr>
								<td>Code �tudiant</td>
								<td>Nom</td>
								<td>Pr�nom</td>
								<td>La note</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${etudiants}" var="etud">
								<tr>
									<td>${etud.ine}<input type="hidden" name="ine_<%=i%>"
										value="<c:out value="${etud.ine}"/>" />
									</td>
									<td>${etud.nomEtudiant}<input type="hidden" name="nom_<%=i%>"
										value="<c:out value="${etud.nomEtudiant}"/>" />
									</td>
									<td>${etud.prenomEtudiant}<input type="hidden"
										name="prenom_<%=i%>" value="<c:out value="${etud.prenomEtudiant}"/>" /></td>
									<td><input type="text" name="noteMat_<%=i%>" value=""></td>
									<%
										i++;
									%>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<input type="hidden" name="insertion"
						value="<c:out value="${insertion}"/>" />
				</div>
				<br> <input type="submit" value="Valider" class="submit" />&nbsp&nbsp&nbsp&nbsp
				<input type="reset" value="Reset" class="submit" />
			</fieldset>
		</form>
	</div>

</body>
</html>

