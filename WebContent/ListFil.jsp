<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<c:if test="${modification == 'modification'}">
			<br>
			<br>
			<span class="ttl">Liste des Matieres</span>

			<div id="tableFil">
				<div class="tables">
					<form action="ListFil" method="POST">
						<br> <br>
						<table>
							<tr>
								<td><span>Nom de la filiere</span></td>
								<td><input class="inpt" type="text"
									value="<c:out value="${filiere.nom}"/>" name="nomFil" /></td>
								<td><input class="inpt" type="text"
									value="<c:out value="${filiere.id}"/>" name="idFil"
									style="visibility: hidden" /></td>
							</tr>
							<tr>
								<td><br></td>
							</tr>
							<tr>
								<td><span>Responsable de filiere</span></td>
								<td><select class="selectfilter filterSrch" name="respFil"
									id="respFil">
										<option value="<c:out value="${filiere.respFil.id}"/>">${filiere.respFil.nom}
											${filiere.respFil.prenom}</option>
										<c:forEach items="${resps}" var="resp">
											<option value="<c:out value="${resp.id}"/>">${resp.nom}
												${resp.prenom}</option>
										</c:forEach>
								</select></td>
							</tr>
						</table>
						<br> <br>
						<%
							int i = 0;
						%>
						<table border="1" cellpadding="0" cellspacing="0" class="tabs">
							<thead>
								<tr>
									<td>Code matiere</td>
									<td>Nom matiere</td>
									<td>Coefficient matiere</td>
									<td>Nombre d'heure</td>
									<td>Enseignant</td>
								</tr>
							</thead>
							<tbody id="kids1">
								<c:forEach items="${matieres}" var="matiere">
									<tr>
										<td><input type="text" name="idMatiere_<%=i%>"
											value="<c:out value="${matiere.id}"/>"></td>
										<td><input type="text" name="nomMatiere_<%=i%>"
											value="<c:out value="${matiere.nom}"/>"></td>
										<td><input type="text" name="coeffMatiere_<%=i%>"
											value="<c:out value="${matiere.coefficient}"/>"></td>
										<td><input type="text" name="heureMatiere_<%=i%>"
											value="<c:out value="${matiere.nbrHeure}"/>"></td>
										<td><select class="selectfilter filterSrch"
											name="respFil_<%=i%>" id="respFil">
											<option value="<c:out value="${matiere.enseignant.id}"/>">${matiere.enseignant.nom}
														${matiere.enseignant.prenom}</option>
												<c:forEach items="${enseignants}" var="enseignant">
													<option value="<c:out value="${enseignant.id}"/>">${enseignant.nom}
														${enseignant.prenom}</option>
												</c:forEach>
										</select></td>
										<td><input type="button" id="add_kid1()"
											onClick="addKid1()" value="+" /></td>
									</tr>
									<%
										i++;
									%>
								</c:forEach>
							</tbody>
						</table>
						<input type="submit" value="Valider" class="submit" name="valider" />
						<input type="button" value="Annuler" class="submit"
							onclick="history.go(-1)"> <input type="text"
							value="<c:out value="${modification}"/>" name="mode"
							style="visibility: hidden" />
					</form>
				</div>
			</div>
		</c:if>
		<c:if test="${modification != 'modification'}">
			<br>
			<br>
			<span class="ttl">Liste des filieres</span>
			<br>
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
			<br>
			<br>
			<div id="listFil">
				<div class="tables">
					<form action="ListFil" method="POST">
						<table cellpadding="0" cellspacing="0" class="tabs">
							<thead>
								<tr>
									<td>Nom filière</td>
									<td>Responsable</td>
									<td>Modifier</td>
									<td>Supprimer</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${fils}" var="fil">
									<tr>
										<td><a>${fil.nom}</a></td>
										<td>${fil.respFil.nom}</td>
										<td><button type="submit"
												value="<c:out value="${fil.id}"/>" name="modify">
												<img alt="modify" class="modifyicone" src="CSS/modify.png" />
											</button></td>
										<td><button type="submit"
												value="<c:out value="${fil.id}"/>" name="delete">
												<img alt="delete" class="deleteicone" src="CSS/delete.png" />
											</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<input type="text" value="<c:out value="${modification}"/>"
							name="mode" style="visibility: hidden" />
					</form>
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>

