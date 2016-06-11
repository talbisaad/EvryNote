<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<title>Liste des Filieres</title>

</head>

<body>
	<div class="header">
		<ul>
			<li><a href="/EvryNote/Deconnexion" class="dcnx">Deconnexion</a></li>
		</ul>
	</div>
	<div class="inner">
		<div class="opr">
			<c:if
				test="${!empty sessionScope.sessionUtilisateur && sessionScope.sessionUtilisateur.reponsableFil && sessionScope.sessionUtilisateur.chefDepart}">
				<jsp:include page="/menuAdmin.jsp"></jsp:include>
			</c:if>
			<c:if
				test="${!empty sessionScope.sessionUtilisateur && !sessionScope.sessionUtilisateur.reponsableFil && !sessionScope.sessionUtilisateur.chefDepart}">
				<jsp:include page="/menuProf.jsp"></jsp:include>
			</c:if>
			<c:if
				test="${!empty sessionScope.sessionUtilisateur && sessionScope.sessionUtilisateur.reponsableFil && !sessionScope.sessionUtilisateur.chefDepart}">
				<jsp:include page="/menuProfResp.jsp"></jsp:include>
			</c:if>
			<c:if
				test="${!empty sessionScope.sessionUtilisateur && !sessionScope.sessionUtilisateur.reponsableFil && sessionScope.sessionUtilisateur.chefDepart}">
				<jsp:include page="/menuCDProf.jsp"></jsp:include>
			</c:if>
		</div>
		<c:if test="${modification == 'modification'}">
			<br>
			<br>
			<span class="ttl">Liste des Matieres</span>

			<div id="tableFil">
				<div class="tables">
					<form action="<c:url value="/platform/ListFil" />" method="POST">
						<br> <br>
						<c:if
							test="${!empty sessionScope.sessionUtilisateur && sessionScope.sessionUtilisateur.chefDepart}">
							<table>
								<tr>
									<td><span>Nom de la filiere</span></td>
									<td><input class="inpt" type="text"
										value="<c:out value="${filiere.nom}"/>" name="nomFil" /></td>
									<td><span>Niveau de la filière</span></td>
									<td><select class="selectfilter filterSrch" name="niveau"
										id="niveau">
											<option value="<c:out value="${filiere.niveau}"/>">${filiere.niveau}</option>
											<option value="L1">L1</option>
											<option value="L2">L2</option>
											<option value="L3">L3</option>
											<option value="M1">M1</option>
											<option value="M2">M2</option>
									</select></td>
								</tr>
								<tr>
									<td><br></td>
								</tr>
								<tr>
									<td><span>Responsable de filiere</span></td>
									<td><select class="selectfilter filterSrch"
										name="respFilN" id="respFil">
											<option value="<c:out value="${filiere.respFil.id}"/>">${filiere.respFil.nom}
												${filiere.respFil.prenom}</option>
											<c:forEach items="${resps}" var="resp">
												<option value="<c:out value="${resp.id}"/>">${resp.nom}
													${resp.prenom}</option>
											</c:forEach>
									</select></td>
									<td><input name="respFilO" type="hidden"
										value="<c:out value="${filiere.respFil.id}"/>"></td>
								</tr>
							</table>
							<br>
							<br>
						</c:if>
						<input class="inpt" type="hidden"
							value="<c:out value="${filiere.id}"/>" name="idFil" />
						<%
							int i = 0, j = 0;
						%>
						<table border="1" cellpadding="0" cellspacing="0" class="tabs"
							id="table">
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
									<tr id="kid_<%=i%>">
										<td><input type="text" name="idMatiere_<%=i%>"
											value="<c:out value="${matiere.id}"/>" readonly></td>
										<td><input type="text" name="nomMatiere_<%=i%>"
											value="<c:out value="${matiere.nom}"/>"></td>
										<td><input type="text" name="coeffMatiere_<%=i%>"
											value="<c:out value="${matiere.coefficient}"/>"></td>
										<td><input type="text" name="heureMatiere_<%=i%>"
											value="<c:out value="${matiere.nbrHeure}"/>"></td>
										<td><select class="selectfilter filterSrch"
											name="respFil_<%=i%>" id="respFil">
												<option value="<c:out value="${matiere.prof.id}"/>">${matiere.prof.nom}
													${matiere.prof.prenom}</option>
												<c:forEach items="${enseignants}" var="enseignant">
													<option value="<c:out value="${enseignant.id}"/>">${enseignant.nom}
														${enseignant.prenom}</option>
												</c:forEach>
										</select></td>
										<td><input type="button" id="add_kid1()"
											onClick="addKid1()" value="+" /></td>
										<td><input type="button" id="deleteMat_<%=i%>" value="-"
											name="deleteMat_<%=i%>" onclick="supprimer(<%=i%>)" /></td>
									</tr>
									<%
										i = i + 1;
									%>

								</c:forEach>
							</tbody>
						</table>

						<!-- hidden table -->
						<table>
							<tbody id="kids1">
								<c:forEach items="${matieres}" var="matiere">
									<tr id="kid_<%=j%>">
										<td><input type="hidden" name="idMatiere_<%=j%>"
											value="<c:out value="${matiere.id}"/>"></td>
										<td><input type="hidden" name="nomMatiere_<%=j%>"
											value="<c:out value="${matiere.nom}"/>"></td>
										<td><input type="hidden" name="coeffMatiere_<%=j%>"
											value="<c:out value="${matiere.coefficient}"/>"></td>
										<td><input type="hidden" name="heureMatiere_<%=j%>"
											value="<c:out value="${matiere.nbrHeure}"/>"></td>
										<td><input type="hidden" name="respFil_<%=j%>"
											value="<c:out value="${matiere.prof.id}"/>"></td>
										<td><input type="hidden" name="delete_<%=j%>"
											id="delete_<%=j%>" value="" /></td>
									</tr>
									<%
										j = j + 1;
									%>
								</c:forEach>
							</tbody>
						</table>

						<script type="text/javascript">
							var j =
						<%=i%>
							;

							function addKid1() {
								if (j < 8) {
									var newRow = document.createElement('tr');

									newRow.innerHTML = '<td><input type="text" name="idMatiere_'+j+'" readonly/></td><td><input type="text" name="nomMatiere_'+j+'" /></td><td> <input type="text" name="coeffMatiere_'+j+'" ></td><td><input type="text" name="heureMatiere_'+j+'" ></td><td><select class="selectfilter filterSrch" name="respFil_'+j+'" id="respFil"><c:forEach items="${enseignants}" var="enseignant"><option value="<c:out value="${enseignant.id}"/>">${enseignant.nom} ${enseignant.prenom}</option></c:forEach></select></td><td><input type="button" id="add_kid1()" onClick="addKid1()" value="+" /></td><td><input type="button" value="-" id="deleteMat_"'+j+'" name="deleteMat_"'+j+'" onClick="supprimer("'+j+'")"/></td>';
									j++;
									document.getElementById('kids1')
											.appendChild(newRow);

								}
							}

							function supprimer(i) {							
								document.getElementById('kids1').removeChild(document.getElementById('kid_'+i));
								document.getElementById('delete_'+i).value="supprimer";
							}
						</script>
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
			<br>
			<br>
			<div id="listFil">
				<div class="tables">
					<form action="<c:url value="/platform/ListFil" />" method="POST">
						<table cellpadding="0" cellspacing="0" class="tabs">
							<thead>
								<tr>
									<td>Nom filière</td>
									<td>Niveau filière</td>
									<td>Responsable</td>
									<td>Modifier</td>
									<td>Supprimer</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${fils}" var="fil">
									<tr>
										<td>${fil.nom}</td>
										<td>${fil.niveau}</td>
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


