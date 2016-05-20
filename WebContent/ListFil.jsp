<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<title>Liste des Filieres</title>
<script type="text/javascript">
	var i = 0;

	function addKid1() {
		if (i < 5) {
			var newRow = document.createElement('tr');

			newRow.innerHTML = '<td> <input type="text" name="idMatiere_'+i+'" ><td> <input type="text" name="coeffMatiere_'+i+'" ></td><td><input type="text" name="heureMatiere_'+i+'" ></td><td><select class="selectfilter filterSrch" id="enseignant"><option value="enseignant1">enseignant 1</option><option value="enseignant2">enseignant 2</option></select></td><td><input type="button" id="add_kid1()" onClick="addKid1()" value="+" /><input type="button" value="-" onclick="removeKid1(this.parentNode)"></td>';

			document.getElementById('kids1').appendChild(newRow);
			i++;
		}
	}

	function removeKid1(element) {
		document.getElementById('kids1').removeChild(element.parentNode);
		i--;
	}
</script>
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
		<br> <br> <span class="ttl">Liste des filieres</span><br>
		<br>
		<div class="rechwidth">
			<fieldset>
				<legend>Rechercher</legend>
				<form>
					<input type="text" name="rechFil" size="20" />&nbsp&nbsp<img
						alt="Rechercher" class="submitfilter" src="CSS/search.png" />
				</form>
			</fieldset>
		</div>
		<br> <br>
		<div id="listFil" style="display: block">
			<div class="tables">
				<table cellpadding="0" cellspacing="0" class="tabs">
					<thead>
						<tr>
							<td>Code filière</td>
							<td>Nom de la filière</td>
							<td>Responsable</td>
							<td>Modifier</td>
							<td>Supprimer</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a></a></td>
							<td></td>
							<td></td>
							<td><a href="#" id="modify" onclick="afficheModif()"><img
									alt="modify" class="modifyicone" src="CSS/modify.png"></a></td>
							<td><a><img alt="delete" class="deleteicone"
									src="CSS/delete.png"></a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<br>
		<div id="tableFil" style="display: none">
			<div class="tables">
				<form>
					<table border="1" cellpadding="0" cellspacing="0" class="tabs">
						<caption class="ttl">LISTE DES MATIERES</caption>

						<thead>
							<tr>
								<td>Code matiere</td>
								<td>Coefficient matiere</td>
								<td>Nombre d'heure</td>
								<td>Enseignant</td>
							</tr>
						</thead>
						<tbody id="kids1">
							<tr>
								<td><input type="text" name="idMatiere"></td>
								<td><input type="text" name="coeffMatiere"></td>
								<td><input type="text" name="heureMatiere"></td>
								<td><select class="selectfilter filterSrch" id="enseignant">
										<option value="enseignant1">enseignant 1</option>
										<option value="enseignant2">enseignant 2</option>
								</select></td>
								<td><input type="button" id="add_kid1()"
									onClick="addKid1()" value="+" /></td>
							</tr>
						</tbody>
					</table>
					<input type="submit" value="Valider" class="submit" /> <input
						type="submit" value="Annuler" class="submit" />
				</form>
			</div>
		</div>

		<script>
			function afficheModif() {
				document.getElementById('listFil').style.display = "none";
				document.getElementById('tableFil').style.display = "block";
			}
		</script>
	</div>
</body>
</html>

