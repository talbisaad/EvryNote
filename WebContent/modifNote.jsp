<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<title>Modification de la note d'etudiant</title>
</head>

<body>
<div class="modifnote">
	<div class="headadddept">
		<span class="headtext">Modification de la note d'etudiant</span>
	</div>
	<div class="center">
		<form action="#" method="POST">
			<table>
				<tr>
					<td><span>Code Etduaint</span></td>
					<td><input class="inpt" type="text" name="idEtud" readonly /></td>
				<tr>
					<td><span>Nom</span></td>
					<td><input class="inpt" type="text" name="nomEtud" readonly /></td>
				<tr>
					<td><span>Prénom</span></td>
					<td><input class="inpt" type="text" name="prenomEtud" readonly /></td>
				<tr>
					<td><span>Code Filière</span></td>
					<td><input class="inpt" type="text" name="idFil" readonly /></td>
				</tr>
				<tr>
					<td><span>Nom Filière</span></td>
					<td><input class="inpt" type="text" name="nomFil" readonly /></td>
				</tr>
				<tr>
					<td><span>Note matière</span></td>
					<td><input class="inpt" type="text" name="noteMat" /></td>
				</tr>
				<tr>
					<td></td>
					<td><br> <br> <input type="submit" value="Valider"
						class="submit" /></td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>