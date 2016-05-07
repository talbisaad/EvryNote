<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="style.css"/>

<script type="text/javascript" src="jquery-2.1.1.min.js"></script>

<script type="text/javascript" src="jquery-ui.js"></script>

<script type="text/javascript" src="script.js"></script>

<title>L'ajout d'un Employe</title>

</head>



<body>

<div class="header">

   <ul >

       <li><a href="Controle?act=decnx" class="dcnx" >Deconnexion</a></li>

    </ul>

</div>

 <div class="inner">

 <div class="opr">

      <ul class="listop">

          <li><div class="titleitm">Employe<span class="togl">[ - ]</span></div>

             <ul class="subItm">

                 <li><a href="?page=Ajouter">Ajouter Employe</a></li>

                 <li><a href="?page=employes">Lister les Employes</a></li>

             </ul>

          </li>

          <li><div class="titleitm">Departement<span class="togl">[ - ]</span></div>

           <ul class="subItm">

                 <li><a href="" class="ajouterdept">Ajouter Departement</a></li>

                 <li><a href="?page=departement">Lister les Departements</a></li>

           </ul>

          </li>

          <li><div class="titleitm">Agenda<span class="togl">[ - ]</span></div>

          <ul class="subItm">

                 <li><a href="" class="ajouteragd">Ajouter Agenda</a></li>

                 <li><a href="?page=Agendas">Lister les Agendas</a></li>

          </ul>

          </li>

          <li><div class="titleitm">Activites<span class="togl">[ - ]</span></div>

           <ul class="subItm">

                 <li><a href="?page=AjouterActivite">Ajouter Activite</a></li>

                 <li><a href="?page=Activites">Lister les Activites</a></li>

            </ul>

          </li>

          <li><div class="titleitm">Horaires<span class="togl">[ - ]</span></div>

            <ul class="subItm">

                 <li><a href="?page=conflits">Les conflits horaires</a></li>

                 <li><a href="?page=volumeHoraire">Le volume horaires</a></li>

            </ul>

          </li>

      </ul>

 </div>

 <div class="addClient">

<br>

<span class="ttl">L'ajout d'un Employe</span><br><br>

<form action="Controle?act=ajouteremp" method="POST">

<table>

<tr><td><span>Nom</span></td><td><input class="inpt" type="text" name="nom" /></td></tr>

<tr><td><span>Prenom</span></td><td><input class="inpt" type="text" name="prenom" /></td></tr>

<tr><td><span>Email</span></td><td><input class="inpt" type="text" name="email" /></td></tr>

<tr><td><span>Telephone</span></td><td><input class="inpt" type="text" name="tel" /></td></tr>

<tr><td><span>Niveau</span></td><td><input class="inpt" type="text" name="niv" /></td></tr>

<tr><td><span>Departement</span></td><td>

                     <select name="dept" class="inpt slct">

                    	   <option></option>

                     </select>

</td></tr>

<tr><td></td><td><input type="submit" value="Ajouter" class="submit" /> <input type="reset" class="submit" value="Effacer" /></td></tr>

</table>

</form>

</div>

 </div>

 

 </body>

</html>

 

