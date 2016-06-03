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
		<br>
		<form action="#" method="POST">
			<table>
				<tr>
					<td><span>Choisir une mati�re</span></td>
					<td>&nbsp&nbsp&nbsp&nbsp</td>
					<td><select class="selectfilter filterSrch" id="respFil">
							<option value="nom">ANALDON</option>
							<option value="nom">COOL</option>
							<option value="nom">RECHOP</option>
					</select></td>
				</tr>
			</table>
		</form>
		<br> <br>
		<fieldset>
			<legend>Saisir les notes : </legend>
			<br>
			<div class="tables">
				<table border="1" cellpadding="0" cellspacing="0" class="tabs">
					<caption class="ttl">COOL</caption>

					<thead>
						<tr>
							<td>Code �tudiant</td>
							<td>Nom</td>
							<td>Pr�nom</td>
							<td>La note</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><input type="text" name="noteMat"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<br> <input type="submit" value="Valider" class="submit" />&nbsp&nbsp&nbsp&nbsp
			<input type="reset" value="Reset" class="submit" />
		</fieldset>
	</div>

</body>
</html>

