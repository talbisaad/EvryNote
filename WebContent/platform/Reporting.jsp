<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<!-- <script src="https://cdn.plot.ly/plotly-latest.min.js"></script> -->
<script type="text/javascript" src="JS/plotly-latest.min.js"></script>

<title>Reporting</title>

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

		<br> <br> <span class="ttl">Reporting de la classe</span><br>
		<br> <br>

		<form action="<c:url value="/platform/Reporting" />" method="POST">
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
		<form action="<c:url value="/platform/Reporting" />" method="POST">
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


		<br> <br> <br>

		<div id="myDiv"
			style="width: 800px; height: 400px; margin-left: auto; margin-right: auto;"></div>


		<script type="text/javascript">
			var matiere = '';
			var classe = '';
			var title = '';
			var xi = new Array();
			var yi = new Array();
			str = '<c:forEach items="${noteMatieres}" var="noteMatiere">';
			xi.push('${noteMatiere.etudiant.nomEtudiant}');
			yi.push('${noteMatiere.note}');
			matiere = '<c:out value="${noteMatiere.matiere.nom}" />';
			classe = '<c:out value="${noteMatiere.etudiant.classe.nomClasse}" />';
			strf = '</c:forEach>';

			window.onload = function() {

				title = 'Graphe des notes des etudiants de la classe : '
						+ classe + ' pour la matiere : ' + matiere

				var data = [ {
					x : xi,
					y : yi,
					type : 'scatter'
				} ];

				var layout = {
					title : title
				};

				Plotly.newPlot('myDiv', data, layout);
			}
		</script>


		<input type="hidden" name="affichage" id="affichage"
			value="<c:out value="${affichage}"/>" />
	</div>
</body>
</html>


