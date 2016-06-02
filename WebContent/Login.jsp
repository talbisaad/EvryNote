<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css" />
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<title>Authentification</title>
</head>

<body>
	<div class="header"></div>
	<div class="inner">


		<form name="loginForm" method="post" action="<c:url value="/Login" />"
			style="background-image: url(CSS/evry.jpg); background-repeat: no-repeat; background-position: 50% 0%">
			<input type="hidden" name="dispatch">
			<fieldset>
				<br> <br> <br> <br> <br> <br> <br>
				<br> <br> <br> <br>

				<table border="0" align="center" cellspacing="0" cellpadding="5"
					width="42%">
					<tr>
						<td align="center" colspan="3"></td>
					</tr>
					<tr>
						<td align="right" width="50%">Login</td>

						<td><input type="text" name="login" size="20"></td>

					</tr>
					<tr>
						<td></td>
						<td><font size="-2" style="color: red"> ${loginE} </font></td>
					</tr>
					<tr>
						<td align="right" width="50%">Mot de passe</td>


						<td class="login_t"><input type="password" name="pass"
							size="20" maxlength="20" /></td>

					</tr>
					<tr>
						<td></td>
						<td><font size="-2" style="color: red"> ${passE} </font></td>
					</tr>
					<tr>
						<td align="center" colspan="3">&nbsp;</td>



					</tr>
					<tr>
						<td>&nbsp;</td>

						<td align="center">


							<table border="0" cellpadding="0" cellspacing="0" width="113">
								<tr>
									<td><input type="submit" value="Valider" class="submit" /></td>
								</tr>
							</table> <br> <font size="-2" style="color: green">
								${resultat} </font>

						</td>

					</tr>
				</table>

				<br> <br> <br> <br> <br> <br>
				<table border="0" align="center" cellspacing="0" cellpadding="5"
					width="25%">
					<tr>
						<td align="center" colspan="3"><font size="-2"> Vous
								avez perdu votre mot de passe ?<a href="#">Cliquez ici</a>

						</font></td>

					</tr>
				</table>
				<br> <br>
			</fieldset>
		</form>
	</div>

</body>
</html>

