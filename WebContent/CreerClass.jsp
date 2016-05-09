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
<title>Création de la classe</title>
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
		<br><br><span class="ttl">Creer une classe</span><br><br>


		<form action="Controle?act=ajouteremp" method="POST">
			 
				<table width="70%">
	
					<tr >
						<td>Nom classe</td>
						<td>:</td>
						<td><input type="text" name="classname" size="20"></td>

						<td>Filière</td>
						<td>:</td>
						<td><select class="selectfilter filterSrch" id="filtersh"></select></td>
					</tr>
					<tr>
						<td>Niveau</td>
						<td>:</td>
						<td><select class="selectfilter filterSrch" id="filtersh"></select></td>
						<td>Année Universitaire</td>
						<td>:</td>
						<td><select class="selectfilter filterSrch" id="filtersh"></select></td>
					</tr>

					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr id="lign">
						<td></td>
						<td></td>
						<td><input type="submit" name="submit" value="Valider"
							class="submit"> <input type="reset" value="Réinitialiser"
							class="submit"></td>
					</tr>

				</table>
		</form>

	</div>

</body>
</html>

