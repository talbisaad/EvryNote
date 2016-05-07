<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="CSS/style.css"/>

<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>

<script type="text/javascript" src="JS/jquery-ui.js"></script>

<script type="text/javascript" src="JS/script.js"></script>

<title>Admin</title>

</head>



<body>

  <div class="box">

  <img alt="adminicone" src="CSS/adm.png" class="admicone"/>

 <form action="Controle?act=login" method="post">

  <table>

  <tr><td>Nom Utilisateur</td><td><input type="text" name="username"  class="input username" autocomplete="off" /></td></tr>

  <tr><td>Mot de Passe</td><td><input type="password" name="password"  class="input password"/></td></tr>

  <tr><td></td><td><input type="submit" value="Se Connecter" class="submit"/></td></tr>

  </table>

  </form>

  </div>

</body>

</html>