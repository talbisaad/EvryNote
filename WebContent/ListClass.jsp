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
		<div class="rechwidth">
			<fieldset>
				<legend>Rechercher</legend>
				<input type="text" name="rechClass" size="20" />&nbsp&nbsp<img
					alt="Rechercher" class="submitfilter" src="CSS/search.png" />
			</fieldset>
		</div>
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
					<tr>
						<td><a></a></td>
						<td></td>
						<td></td>
						<td></td>
						<td><img alt="modify" class="modifyicone"
							src="CSS/modify.png"></td>
						<td><img alt="delete" class="deleteicone"
							src="CSS/delete.png"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>