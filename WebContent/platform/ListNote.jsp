<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	function activer() {
		document.getElementById('note').disabled = false;
		document.getElementById('boutonVal').style.display = 'block';
		document.getElementById('boutonAnul').style.display = 'block';
	}
</script>
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
		<br> <br> <span class="ttl" hidden>Lister les notes
			d'une classe</span><br> <br> <br>
		<div>
			<fieldset class="fieldsetfilter">
				<legend>Rechercher</legend>
				<input type="text" name="rechFil" size="20" />&nbsp&nbsp<img
					alt="Rechercher" class="submitfilter" src="CSS/search.png" />
			</fieldset>
			<div class="rechclasse">
				<form action="#" method="POST">
					<table>
						<tr>
							<td><span>Choisir une classe </span></td>
							<td>&nbsp&nbsp&nbsp&nbsp</td>
							<td><select class="selectfilter filterSrch" id="respFil">
									<option value="nom">MIAGE M1 Apprentissage</option>
							</select></td>
						</tr>
					</table>
				</form>
				<form action="#" method="POST">
					<table>
						<tr>
							<td><span>Choisir une matière</span></td>
							<td>&nbsp&nbsp&nbsp&nbsp</td>
							<td><select class="selectfilter filterSrch" id="respFil">
							</select></td>
						</tr>
					</table>
					<br> <br>
				</form>
			</div>
		</div>
		<br> <br>
		<div class="tables" action="#" method="POST">
			<form action="#" method="POST">
				<table cellpadding="0" cellspacing="0" class="tabs">
					<thead>
						<tr>
							<td>Matière</td>
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
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td><input type="text" name="noteEtu" id="note" disabled
								size="10"></td>
							<td><a href="#" class="modif" id='boutonModif'
								onclick="activer()"><img alt="modify" class="modifyicone"
									src="CSS/modify.png"></a></td>
							<td><input type="submit" style="display: none;"
								name="validNote" value="Valider" class="submit" id="boutonVal" /></td>
							<td><input type="submit" style="display: none;"
								name="annulNote" value="Annuler" class="submit" id="boutonAnul" /></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>


