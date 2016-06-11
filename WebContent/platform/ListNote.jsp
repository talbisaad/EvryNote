<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<title>Lister les notes d'une classe</title>

<script language="javascript">
	function activer(i) {
		document.getElementById('noteMat_'+i).disabled = false;
		document.getElementById('boutonVal').style.display = 'block';
		document.getElementById('boutonAnul_'+i).style.display = 'block';
	}
	
	function desactiver(i) {
		document.getElementById('noteMat_'+i).disabled = true;
		document.getElementById('boutonAnul_'+i).style.display = 'none';
	}
	
	
</script>
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
		<br> <br> <span class="ttl">Lister les notes
			d'une classe</span><br> <br> <br> <br>

		<form action="<c:url value="/platform/ListNote" />" method="POST">
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
		<form action="<c:url value="/platform/ListNote" />" method="POST">
			<table>
				<tr>
					<td><span>Choisir une matière</span></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><select class="selectfilter filterSrch" id="matiere"
						name="matiere" onChange="this.form.submit();">
							<option></option>
							<c:forEach items="${matieres}" var="matiere">
								<option value="<c:out value="${matiere.id}"/>">${matiere.nom}</option>
							</c:forEach>
					</select></td>
					<td><input type=hidden name="classe"
						value="<c:out value="${classe}"/>" /></td>
				</tr>
			</table>
		</form>
		<br> <br>
		<fieldset>
			<legend>Lister les notes : </legend>
			<form action="<c:url value="/platform/ListNote" />" method="POST">
				<br> <br>
				<div class="tables">
					<table cellpadding="0" cellspacing="0" class="tabs">
						<thead>
							<tr>
								<td>Code etudiant</td>
								<td>Nom</td>
								<td>Prenom</td>
								<td>Note</td>
								<td>Modifier</td>
								<td></td>
								<td></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${noteMatieres}" var="noteMatiere">
								<tr>
									<td>${noteMatiere.etudiant.ine}<input type="hidden"
										name="ine_<%=i%>"
										value="<c:out value="${noteMatiere.etudiant.ine}"/>" />
									</td>
									<td>${noteMatiere.etudiant.nomEtudiant}<input type="hidden"
										name="nom_<%=i%>"
										value="<c:out value="${noteMatiere.etudiant.nomEtudiant}"/>" />
									</td>
									<td>${noteMatiere.etudiant.prenomEtudiant}<input type="hidden"
										name="prenom_<%=i%>"
										value="<c:out value="${noteMatiere.etudiant.prenomEtudiant}"/>" /></td>
									<td><input type="text" name="noteMat_<%=i%>"
										value="<c:out value="${noteMatiere.note}"/>"
										id="noteMat_<%=i%>" disabled size="10"></td>
									<td><a href="#" class="modif" id='boutonModif'
										onclick="activer(<%=i%>)"><img alt="modify"
											class="modifyicone" src="CSS/modify.png"></a></td>
									<td><input type="submit" style="display: none;"
										name="annulNote_<%=i%>" value="Annuler" class="submit"
										id="boutonAnul_<%=i%>" onclick="desactiver(<%=i%>)" /></td>
									<td><input type="hidden" name="matiere"
										value="<c:out value="${noteMatiere.matiere.id}"/>" /></td>
									<%
										i++;
									%>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<br> <input type="submit" style="display: none;"
						name="validNote" value="Valider" class="submit" id="boutonVal" />
					<input type="hidden" name="insertion" id="insertion"
						value="<c:out value="${insertion}"/>" />
				</div>
			</form>
		</fieldset>
	</div>
</body>
</html>


