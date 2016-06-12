<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<title>Simulation</title>
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
		<br> <br> <span class="ttl">Simulation</span><br> <br>
		<fieldset>
			<legend>Filière</legend>
			<form action="ServletEtudiant?action=SearchMatiere" method="POST">
				<div>

					<table width="70%">

						<tr>
							<td>Filière</td>
							<td>:</td>
							<td><select class="selectfilter filterSrch" name="filiere"
								id="filtersh">
									<c:forEach items="${listfiliere}" var="f">
										<option>${f.nom}</option>
									</c:forEach>
							</select></td>
							<td>Niveau</td>
							<td>:</td>
							<td><select class="selectfilter filterSrch" name="niveau"
								id="filtersh">
									<c:forEach items="${ListNiveau}" var="n">
										<option>${n}</option>
									</c:forEach>
							</select></td>
						</tr>

						<tr>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
						<td><input type="submit" class="submit" value="Rechercher" />
							</td>
						</tr>

					</table>
				</div>
			</form>
		</fieldset>
		<fieldset>
			<legend>Liste des matières</legend>
		<form action="ServletEtudiant?action=Calculate"  method="POST">
			<div class="tables" id="lign">
				<table cellpadding="0" cellspacing="0" class="tabs">
					<thead>
						<tr>
							<td>Matière</td>
							<td>Coefficient</td>
							<td>Note</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach begin="0" end="${lengh}" step="1"
							varStatus="loopCounter" items="${listmatiere}" var="c">
							<tr>
								<td><c:out value="${c.nom}" /></td>
								<td><c:out value="${c.coefficient}" /></td>
								<td><input type="number" step="0.01" class="inpt" name="${loopCounter.index}" size="30"></td>
							</tr>
						</c:forEach>
						
						<tr>
							
							<td id="content"><input type="hidden" name="IdRow" value="${lengh}" />Total</td>
							<td><c:out value="${sumcoeff}"/></td>
							<td><c:out value="${result}"/></td>
							<td></td>
						</tr>
						<tr></tr>
					</tbody>
				</table>
			</div>
			<div>
			 
				<button type="submit" class="submit" id="hideshow"  >Calculer</button>
			 
		</div>
			
		</form>
		</fieldset>

		
	</div>
	 <script>
		var button = document.getElementById('hideshow');

		button.onclick = function() {
			var div = document.getElementById('content');
			
			if (div.style.display !== 'none') {
				div.style.display = 'none';
				 
			} else {
				 
				div.style.display = 'block';

			}
		};
	</script>
</body>
</html>