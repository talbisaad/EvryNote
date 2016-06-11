<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<title>Gestion des enseignants</title>
</head>

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
		<br> <br> <span class="ttl">Gerer les enseignants</span><br>
		<br>
		<form action="<c:url value="/platform/ListEns"/>" method="GET">
			<fieldset class="fieldsetfilter">
				<legend>Rechercher</legend>
				<table>
					<tr>
						<td><input type="text" name="rechEns" size="20" /></td>&nbsp;&nbsp;
						<td><button type="submit" name="search">
								<img alt="Rechercher" class="submitfilter" src="CSS/search.png" />
							</button></td>
					</tr>
				</table>
			</fieldset>
		</form>
		<br> <br> <br> <br>
		<div class="tables">
			<br> <br> <br>
			<form action="<c:url value="/platform/ListEns"/>" method="POST">
				<table cellpadding="0" cellspacing="0" class="tabs">
					<thead>
						<tr>
							<td>Code enseignant</td>
							<td>Nom</td>
							<td>Prenom</td>
							<td>Responsable</td>
							<td>Chef de departement</td>
							<td>Supprimer</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${enseignants}" var="enseignant">
							<tr>
								<td>${enseignant.id}</td>
								<td>${enseignant.nom}</td>
								<td>${enseignant.prenom}</td>
								<c:choose>
									<c:when test="${enseignant.reponsableFil}">
										<td><center>
												<img alt="responsable" class="cheficone"
													src="CSS/responsable.png" />
											</center></td>
									</c:when>
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${enseignant.chefDepart}">
										<td><center>
												<img alt="responsable" class="cheficone"
													src="CSS/responsable.png" />
											</center></td>
									</c:when>
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>

								<td><button type="submit"
										value="<c:out value="${enseignant.id}"/>" name="delete">
										<img alt="supprimer" class="deleteicone" src="CSS/delete.png" />
									</button></td>
							</tr>
						</c:forEach>
				</table>
			</form>
		</div>
	</div>

</body>
</html>

