<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Liste des classes</title>
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
		<br> <br> <span class="ttl">Liste des classes</span><br>
		<br>
		<br>
		<div class="tables">
			<table cellpadding="0" cellspacing="0" class="tabs">
				<thead>
					<tr>
						<td>Nom classe</td>
						<td>Filière</td>
						<td>Niveau</td>
						<td>Année universitaire</td>
						<td>Modifier</td>
						<td>Supprimer</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach begin="0" end="${lengh}" step="1" varStatus="loopCounter" items="${listclasse}" var="c">
						<tr>

							<td ><c:out  value="${c.nomClasse}"/></td>
							<td><c:out value="${c.filiere.nom}"/></td>
							<td><c:out value="${c.niveau}"/></td>
							<td><c:out value="${c.anneeUniversitaire}" /></td>
							<td><form action="ServletClass?action=ModifierClassFromListClass" method="POST"><button style="border:0px solid black; background-color: transparent;" type="submit" name="Modifier"><img alt="modify" class="modifyicone"
								src="CSS/modify.png"></button>
								<input type="hidden" name="IdRow" value="${c.idClasse}"/>
								</form></td>
							<td><form action="ServletClass?action=SupprimerClass" method="POST"><button style="border:0px solid black; background-color: transparent;" type="submit" name="Modifier"> <img alt="delete" class="deleteicone"
								src="CSS/delete.png"></button>
							  <input type="hidden" name="IdRow" value="${c.idClasse}"/>
								</form></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>