<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
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
<title>Liste des Filieres</title>
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
		<br> <br> <span class="ttl">Gerer une filiere</span><br>
		<br> <span>Choisir une filiere : </span> &nbsp <select
			class="selectfilter filterSrch" id="filiere">
			<option value="nom">MIAGE M1</option>
		</select> <br> <br> <br>&nbsp &nbsp &nbsp &nbsp &nbsp <input
			type="radio" name="saisieFil" value="importFil" checked> <span>Importer
			la liste des matieres de la filiere </span> <br> <br>&nbsp &nbsp
		&nbsp &nbsp &nbsp <input type="radio" name="saisieFil"
			value="tableFil"> <span>Ajouter manuellement les
			matieres de la filiere </span><br> <br>
		<div id="importFil">
			<form action="" method="post">
				<fieldset>
					<legend>Importer le fichier</legend>
					<br> <label for="fichier">Emplacement du fichier <span
						class="requis">*</span></label> <input type="file" id="fichier"
						name="fichier" value="" /> <br /> <input type="submit"
						value="Valider" class="submit" />
				</fieldset>
			</form>
		</div>

		<div id="tableFil">
			<fieldset>
				<legend>Remplir mannuellement les matieres de la filiere :
				</legend>
				<table>
					<tr>
						<td><span>Semestre </span></td>
						<td><select class="selectfilter filterSrch" id="sem">
								<option value="sem1">1</option>
								<option value="sem2">2</option>
						</select></td>
					</tr>
					<tr>
						<td><span>Code du module</span></td>
						<td><input class="inpt" type="text" name="idModul" /></td>
					</tr>
					<tr>
						<td><span>Nom du module</span></td>
						<td><input class="inpt" type="text" name="nomModul" /></td>
					</tr>
				</table>

				<br> <br>


				<table border="1">
					<caption>LISTE DES MATIERES</caption>
					<tr>
						<th>Code matiere</th>
						<th>Coefficient matiere</th>
						<th>Nombre d'heure</th>
						<th>Enseignant</th>
					</tr>
					<tbody id="kids1">
						<tr>
							<td><input type="text" name="idMatiere"></td>
							<td><input type="text" name="coeffMatiere"></td>
							<td><input type="text" name="heureMatiere"></td>
							<td><select class="selectfilter filterSrch" id="enseignant">
									<option value="enseignant1">enseignant 1</option>
									<option value="enseignant2">enseignant 2</option>
							</select></td>
							<td><input type="button" id="add_kid1()" onClick="addKid1()"
								value="+" /></td>
						</tr>

					</tbody>
				</table><br>
				<input type="submit" value="Valider" class="submit" />
			</fieldset>
		</div>


		<script>
			$(document).ready(function() {
				var $tableFil = $("#tableFil");
				$tableFil.hide();
				$('input[name=saisieFil]:radio').click(function() {
					$("#importFil").hide();
					$tableFil.hide();
					var divId = $(this).val();
					$("#" + divId).show();
				});
			});
		</script>
	</div>
</body>
</html>

