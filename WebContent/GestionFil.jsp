<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	var i = 1;

	function addKid1() {
		if (i < 8) {
			var newRow = document.createElement('tr');

			newRow.innerHTML = '<td> <input type="text" name="nomMatiere_'+i+'" ><td> <input type="text" name="coeffMatiere_'+i+'" ></td><td><input type="text" name="heureMatiere_'+i+'" ></td><td><select class="selectfilter filterSrch" name="respFil"id="respFil_'+i+'""><c:forEach items="${enseignants}" var="enseignant"><option value="<c:out value="${enseignant.id}"/>">${enseignant.nom}${enseignant.prenom}</option></c:forEach></select></td><td><input type="button" id="add_kid1()" onClick="addKid1()" value="+" /><input type="button" value="-" onclick="removeKid1(this.parentNode)"></td>';
			document.getElementById('kids1').appendChild(newRow);
			i++;
		}
	}

	function removeKid1(element) {
		document.getElementById('kids1').removeChild(element.parentNode);
		i--;
	}
</script>
<title>Gerer une filiere</title>
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
		<form action="GestionFil" method="POST">
			<br> <span>Choisir une filiere : </span> &nbsp <select
				class="selectfilter filterSrch" id="filiere" name="filiere">
				<c:forEach items="${fils}" var="fil">
					<option value="<c:out value="${fil.id}"/>">${fil.nom}</option>
				</c:forEach>
			</select><br> <br> <br>&nbsp &nbsp &nbsp &nbsp &nbsp <input
				type="radio" name="saisieFil" value="importFil" checked> <span>Importer
				la liste des matieres de la filiere </span> <br> <br>&nbsp &nbsp
			&nbsp &nbsp &nbsp <input type="radio" name="saisieFil"
				value="tableFil"> <span>Ajouter manuellement les
				matieres de la filiere </span><br> <br>
			<div id="importFil">
				<fieldset>
					<legend>Importer le fichier</legend>
					<br> <label for="fichier">Emplacement du fichier <span
						class="requis">*</span></label> <input type="file" id="fichier"
						name="fichier" value="" /> <br /> <input type="submit"
						value="Valider" class="submit" />
				</fieldset>
			</div>

			<div id="tableFil">
				<fieldset>
					<legend>Remplir mannuellement les matieres de la filiere :
					</legend>
					<br> <br>
					<div class="tables">

						<table border="1" cellpadding="0" cellspacing="0" class="tabs">
							<caption class="ttl">LISTE DES MATIERES</caption>

							<thead>
								<tr>
									<td>Nom matiere</td>
									<td>Coefficient matiere</td>
									<td>Nombre d'heure</td>
									<td>Enseignant</td>
								</tr>
							</thead>
							<tbody id="kids1">
								<tr>
									<td><input type="text" name="nomMatiere_0"></td>
									<td><input type="text" name="coeffMatiere_0"></td>
									<td><input type="text" name="heureMatiere_0"></td>
									<td><select class="selectfilter filterSrch"
										name="respFil_0" id="respFil">
											<c:forEach items="${enseignants}" var="enseignant">
												<option value="<c:out value="${enseignant.id}"/>">${enseignant.nom}
													${enseignant.prenom}</option>
											</c:forEach>
									</select></td>
									<td><input type="button" id="add_kid1()"
										onClick="addKid1()" value="+" /></td>
								</tr>
							</tbody>
						</table>
					</div>
					<br> <input type="submit" value="Valider" class="submit" />
				</fieldset>
			</div>
		</form>


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

