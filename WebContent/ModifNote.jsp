<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<title>Modifier la note d'un etudiant</title>
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
		<br> <br> <span class="ttl">Modifier la note de
			l'étudiant</span><br> <br>
		<div class="modif">
			<form action="#" method="POST">
				<table>
					<tr>
						<td><span>Code Etduaint</span></td>
						<td><input class="inpt" type="text" name="idEtud" /></td>
					<tr>
						<td><span>Nom</span></td>
						<td><input class="inpt" type="text" name="nomEtud" /></td>
					<tr>
						<td><span>Prénom</span></td>
						<td><input class="inpt" type="text" name="prenomEtud" /></td>
					<tr>
						<td><span>Code Filière</span></td>
						<td><input class="inpt" type="text" name="idFil" /></td>
					</tr>
					<tr>
						<td><span>Nom Filière</span></td>
						<td><input class="inpt" type="text" name="nomFil" /></td>
					</tr>
					<tr>
						<td><span>Note matière</span></td>
						<td><input class="inpt" type="text" name="noteMat" /></td>
					</tr>
				</table>
				<br><br>
				<input type="submit" value="Valider" class="submit" />
			</form>
		</div>
	</div>

</body>
</html>

