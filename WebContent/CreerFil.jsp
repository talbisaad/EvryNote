<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css"/>
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<title>Creer une Filiere</title>
</head>

<body>
<div class="header">
   <ul >
       <li><a href="#" class="dcnx" >Deconnexion</a></li>
    </ul>
</div>
 <div class="inner">
 <div class="opr">
<jsp:include page="menuAdmin.jsp"></jsp:include>
 </div>
<br><br>
<span class="ttl">Creer une filiere</span><br><br>
<form action="#" method="POST">
<table>
<tr><td><span>Nom de la filiere</span></td><td><input class="inpt" type="text" name="nomFil" /></td></tr>
<tr><td><br></td></tr>
<tr><td><span>Responsable de filiere</span></td><td>
        <select class="selectfilter filterSrch"  id="respFil">
               <option value="nom">Nom Prenom</option>
        </select></td></tr>
</table>
<br><br>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="submit" value="Valider" class="submit" />
</form>

</div>
 
 </body>
</html>
 
