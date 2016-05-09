<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css"/>
<script type="text/javascript" src="JS/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<script type="text/javascript" src="JS/script.js"></script>
<title>Lister les notes d'une classe</title>
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
<span class="ttl">Lister les notes d'une classe</span><br><br>
     <div class="tables"> 
           <table cellpadding="0" cellspacing="0" class="tabs">
           <thead>
           <tr>
               <td>Code etudiant</td>
               <td>Nom</td>
               <td>Prenom</td>
               <td>Modifier</td>
           </tr>
           </thead>
           <tbody>
                    	  <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a><img alt="modify" class="modifyicone" src="CSS/modify.png"></a></td>
                           </tr>          
           </tbody>
           </table>
</div>
</div>
 
 </body>
</html>
 
