<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<title>Creer une Filiere</title>
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
		<br> <br> <span class="ttl">Creer une filiere</span><br>
		<br>
		<form action="CreerFil" method="POST">
			<table>
				<tr>
					<td><span>Nom de la filiere</span></td>
					<td><input class="inpt" type="text" value="" name="nomFil" /></td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr>
					<td><span>Niveau de la filière</span></td>
					<td><select class="selectfilter filterSrch" name="niveau"
						id="niveau">
							<option value="L3">L3</option>
							<option value="M1">M1</option>
							<option value="M2">M2</option>
					</select></td>
				</tr>
				<tr>
					<td><span>Responsable de la filière</span></td>
					<td><select class="selectfilter filterSrch" name="respFil"
						id="respFil">
							<c:forEach items="${resps}" var="resp">
								<option value="<c:out value="${resp.id}"/>">${resp.nom}
									${resp.prenom}</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<br> <br> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input
				type="submit" value="Valider" class="submit" />
		</form>

	</div>

</body>
</html>

