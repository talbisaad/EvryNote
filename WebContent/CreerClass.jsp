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
<title>Cr�ation de la classe</title>
</head>

<body>
	<div class="header">
		<ul>
			<li><a href="#" class="dcnx">Deconnexion</a></li>
		</ul>
	</div>
	<div class="inner">
		<div class="opr" id="importFil">
			<jsp:include page="menuAdmin.jsp"></jsp:include>
		</div>
		<br> <br> <span class="ttl">Creer une classe</span><br>
		<br>


		<form action="CreerClass" method="POST">

			<table width="70%">

				<tr>
					<td>Nom classe</td>
					<td>:</td>
					<td><input type="text" name="NomClasse" size="30"></td>

					<td>Fili�re</td>
					<td>:</td>
					<td><select class="selectfilter filterSrch" name="filiere" id="filtersh">
					<optgroup label="">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
					</optgroup>
					</select></td>
				</tr>
				<tr>
					<td>Niveau</td>
					<td>:</td>
					<td><select class="selectfilter filterSrch" name="niveau" id="filtersh"></select></td>
					<td>Ann�e Universitaire</td>
					<td>:</td>
					<td><select class="selectfilter filterSrch" name="annee" id="filtersh">
					<optgroup label="">
					<option>1000</option>
					<option>2000</option>
					<option>3000</option>
					<option>4000</option>
					<option>5000</option>
					</optgroup>
					</select></td>
				</tr>

				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr id="lign">
					<td></td>
					<td></td>
					<td><input type="submit" name="valider" value="Valider"
						class="submit" /> <input type="reset" value="R�initialiser"
						class="submit" name="renit"/></td>
				</tr>

			</table>
		</form>

	</div>

	<div class="inner" style="display: none;" id="tableFil">
		<br> <br> <span class="ttl">Affiche Classe</span><br> <br>
		<form action="Controle?act=ajouteremp" method="POST">

			<table width="70%">

				<tr>
					<td>Nom classe</td>
					<td>:</td>
					<td><label class="selectfilter filterSrch" id="filtersh"></label></td>

					<td>Fili�re</td>
					<td>:</td>
					<td><label class="selectfilter filterSrch" id="filtersh"></label></td>
				</tr>
				<tr>
					<td>Niveau</td>
					<td>:</td>
					<td><label class="selectfilter filterSrch" id="filtersh"></label></td>
					<td>Ann�e Universitaire</td>
					<td>:</td>
					<td><label class="selectfilter filterSrch" id="filtersh"></label></td>
				</tr>

				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr id="lign">
					<td></td>
					<td></td>
					<td><a href="/EvryNote/CreerClass.jsp" class="submit">Modifier</a>
					</td>
				</tr>

			</table>
		</form>


	</div>
	<!-- <script>
		$(document).ready(function() {
			var $tableFil = $("#tableFil");
			$tableFil.hide();
			$('input[name=saisieFil]:submit').click(function() {
				$("#importFil").hide();
				$tableFil.hide();
				var divId = $(this).val();
				$("#" + divId).show();
			});
		});
	</script> -->

</body>
</html>

