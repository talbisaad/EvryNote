<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<title>Lister les notes d'une classe</title>
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
		<br> <br> <span class="ttl">Lister les notes d'une
			classe</span><br> <br> <br>
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
					<br>
					<br>
				</form>
			</div>
		</div>
		<br>
		<br>
		<script type="text/javascript">
			function open_infos() {
				window
						.open('modif.jsp', 'modifNote',
								'menubar=no, scrollbars=no, top=100, left=100, width=300, height=200');
			}
		</script>
		<div class="tables">
			<table cellpadding="0" cellspacing="0" class="tabs">
				<thead>
					<tr>
						<td>Matière</td>
						<td>Code etudiant</td>
						<td>Nom</td>
						<td>Prenom</td>
						<td>Note</td>
						<td>Modifier</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><a href="" class="ajouteragd"><img alt="modify"
								class="modifyicone" src="CSS/modify.png"></a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
 <%@include file="modifNote.jsp" %>

