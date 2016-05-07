
  $( document ).ready(function() {
	  /*get param from url*/
      function getUrlParameter(sParam)
     {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
         }
       } 

	  $(".titleitm").on("click",function(){
		 $(this).next().slideToggle();
	  });
	  $(".fermer").on("click",function(){
		  $(".adddept").fadeOut();
		  $(".shadow").fadeOut();
		  $(".addegd").fadeOut();
		  $(".addalertt").fadeOut();
	  });
	  
	  $(".ajouterdept").on("click",function(){
		  $(".adddept").fadeIn();
		  $(".shadow").fadeIn();
		  return false;
	  });
	  $(".ajouteragd").on("click",function(){
		  $(".addegd").fadeIn();
		  $(".shadow").fadeIn();
		  return false;
	  });
	  $(".slcttypeagd").on("change",function()
			  {
		     var fct=$(this).val();
		     if(fct==1){
		    	 $(".slctagdemp").show();
		     }else
		     {
			      $(".slctagdemp").hide();
		     }
	});
	  $(".slcttypeagdfil").on("change",function()
			  {
		     var fct=$(this).val();
		     if(fct==1){
		    	 $(".slctagdempfil").show();
		     }else
		     {
			      $(".slctagdempfil").hide();
		     }
	});
	  
	  $('.agdfilter').click(function(event) {  
		  listerAgendas();
		  return false;
      });
	  
	  /*lister les agendas*/
	  if(getUrlParameter("page")=="Agendas")
	  listerAgendas();
	  function listerAgendas()
	  {
		  var typeagenda=$('.slcttypeagdfil').val();
		  var date1=$(".dt1").val();
		  var date2=$(".dt2").val();
		  var empagd=$(".slctagdempfil").val();
       
       $.ajax({
    	      type: "GET",
    	      url: "ajax/agendaAjax.jsp",
    	      datatype: "json",
    	      contentType: "application/json; charset=utf-8",
    	      data: ({typagd:typeagenda,dt1:date1,dt2:date2,emp:empagd}),
    	      cache: false,
    	      success: function(data) {   	    	  
    	    	  $(".agdtab").children("tbody").children("tr").remove();
    	    	actaliserAgenadDept(data["agendas"]);
    	      },
    	      error:function(data)
    	      {
    	    	  
    	      }
    	    });
	  }
	  
	  /*actualiser les agendas*/
	  function actaliserAgenadDept(data)
	  {
		  var tablbody=$(".agdtab").children("tbody");
		  $.each(data,function(k,v) {
			  var nom="";
			  if(v["typeag"]=="1"){
				  $(".agddeff").text("Nom Employe");
				  nom=v["nomemp"];
			  }
			  else if(v["typeag"]=="2"){
				  $(".agddeff").text("Nom Departement");
				  nom=v["nomdept"];
			  }
				  
				var tr=$('<tr>'+
                        '<td><span >'+nom+'</span></td>'+
                        '<td><span >'+v["idagenda"]+'</span></td>'+
                        '<td><span>'+v["date_agenda"]+'</span></td>'+
                        '<td><span>'+v["nbractivite"]+'</span></td>'+
                        '<td class="opttd">'+
                        '<a href="Controle?act=supprimeragd&id='+v["idagenda"]+'&typ='+v["typeag"]+'"  class="supprimeragd">'+
                        '<img alt="delete" class="deleteicone" src="css/delete.png"></a></td>'+
                       '</tr>');
				  $(tablbody).append(tr);
	  });
	  }
	  
	  /*ajouter activité visible*/
	  $(".slcttypeagaddact").on("change",function()
			  {
		       var vl=$(this).val();		       
		       if(vl==1){
			    	 $(".visibleact").show();
			    	 $(".agd").hide();
			    	 $(".agp").show();
			     }else
			     {
				      $(".visibleact").hide();
				      $(".agp").hide();
				      $(".agd").show();
			     }
			  });
	  
	  /*activite dept et pers*/
	  $(".actfilter").on("click",function()
			  {
		  listerActivites();
			  });
	  if(getUrlParameter("page")=="Activites")
		  listerActivites();
	  function listerActivites()
	  {
		  var typeagenda=$('.slcttypeagaddact').val();
		  var idagemp=$(".slctagdempfil").val();
		  var idagdept=$(".slctagddeptfil").val();
       $.ajax({
    	      type: "GET",
    	      url: "ajax/activiteAjax.jsp",
    	      datatype: "json",
    	      contentType: "application/json; charset=utf-8",
    	      data: ({typagd:typeagenda,emp:idagemp,dept:idagdept}),
    	      cache: false,
    	      success: function(data) {   
    	    	$(".tabs").children("tbody").children("tr").remove();
    	    	actaliserActivites(data["activites"]);
    	      },
    	      error:function(data)
    	      {
    	    	  
    	      }
    	    });
	  }
	  /*actualiser les activites*/
	  function actaliserActivites(data)
	  {
		  var tablbody=$(".tabs").children("tbody");
		  $.each(data,function(k,v) {	
			  var alrt="";
			  if(v['typeag']=="2")
				  alrt='<a href="Controle?act=ajouteralert" rel="'+v['numAct']+'" class="addalert" id="ajtalert" ><img alt="modify" class="alerticone emmd" src="css/alert.png"></a>';

				var tr=$('<tr>'+
						'<td>'+v['type']+'</td>'+
			               '<td>'+v['descript']+'</td>'+
			               '<td>'+v['dateAct']+'</td>'+
			               '<td>'+v['hDebut']+'</td>'+
			               '<td>'+v['hFin']+'</td>'+
			               '<td>'+v['createur']+'</td>'+
			               '<td>'+alrt+
			               '<a href="Controle?act=modifierAct&id='+v['numAct']+'"><img alt="modify" class="modifyicone emmd" src="css/modify.png"></a>'+
	                       '<a href="Controle?act=supprimerAct&id='+v["numAct"]+'&typ='+v["typeag"]+'"><img alt="delete" class="deleteicone emmd" src="css/delete.png"></a>'+
			               '</td>'+
                       '</tr>');
				  $(tablbody).append(tr);
	  });
	  }
	  /*add alert*/
	  $(document).on( "click", ".addalert", function() 
			  {
		         var id=$(this).attr("rel");
		         $(".frmalert").append("<input type='hidden' name='idact' value='"+id+"'/>");
		         $(".shadow").fadeIn();
		         $(".addalertt").fadeIn();
		  return false;
			  });
	  
	  /*les conflits*/
	  $(".conflits").on("click",function(){
		  listerConflits(); 
	  });
	  
	  /*lister les conflits*/
	  if(getUrlParameter("page")=="conflits")
		  listerConflits();
	  function listerConflits()
	  {
		  var nomemp=$('.slctagdempfil').val();
		  
       $.ajax({
    	      type: "GET",
    	      url: "ajax/conflitsAjax.jsp",
    	      datatype: "json",
    	      contentType: "application/json; charset=utf-8",
    	      data: ({emp:nomemp}),
    	      cache: false,
    	      success: function(data) {   
    	    	$(".tabs").children("tbody").children("tr").remove();
    	    	actaliserConflit(data["conflits"]);
    	      },
    	      error:function(data)
    	      {
    	    	  
    	      }
    	    });
	  }
	  
	  /*actualiser les conflit*/
	  function actaliserConflit(data)
	  {
		  var tablbody=$(".tabs").children("tbody");
		  $.each(data,function(k,v) {	
				var tr=$('<tr>'+
						   '<td>'+v['act_pers']+'</td>'+
			               '<td>'+v['Dh_Activ']+'</td>'+
			               '<td>'+v['Fh_Activ']+'</td>'+
			               '<td>'+v['act_dept']+'</td>'+
			               '<td>'+v['Dh_Activ_Dept']+'</td>'+
			               '<td>'+v['Fh_Activ_Dept']+'</td>'+
                       '</tr>');
				  $(tablbody).append(tr);
	  });
	  }
	  
	  //volume horaire
	  $(".volH").on("click",function(){
		  listerVolh(); 
	  });
	  
	  /*lister les conflits*/
	  if(getUrlParameter("page")=="volumeHoraire")
		  listerVolh();
	  function listerVolh()
	  {
		  var nomemp=$('.slctempvolh').val();
		  
       $.ajax({
    	      type: "GET",
    	      url: "ajax/volumeHorAjax.jsp",
    	      datatype: "json",
    	      contentType: "application/json; charset=utf-8",
    	      data: ({emp:nomemp}),
    	      cache: false,
    	      success: function(data) {   
    	    	$(".tabs").children("tbody").children("tr").remove();
               actaliserVolumeH(data["volh"]);
    	      },
    	      error:function(data)
    	      {
    	    	  
    	      }
    	    });
	  }
	  
	  /*actualiser les conflit*/
	  function actaliserVolumeH(data)
	  {
		  var tablbody=$(".tabs").children("tbody");
		  $.each(data,function(k,v) {	
				var tr=$('<tr>'+
						   '<td>'+v['typeA']+'</td>'+
			               '<td>'+v['description']+'</td>'+
			               '<td>'+v['dateAct']+'</td>'+
			               '<td>'+v['hDebut']+'</td>'+
			               '<td>'+v['hFin']+'</td>'+
			               '<td>'+v['duree']+'</td>'+
                       '</tr>');
				  $(tablbody).append(tr);
	  });
	  }
	  
	  /*ajax recherche Employe*/
		  $('.subfilemp').click(function(event) {  
	          var searchval=$(".valsearch").val();
	          var seachfilter=$('.filterSrch').val();
	       $.ajax({
	    	      type: "GET",
	    	      url: "ajax/searchEmpAjax.jsp",
	    	      datatype: "json",
	    	      contentType: "application/json; charset=utf-8",
	    	      data: ({shval:searchval,shfilter:seachfilter}),
	    	      cache: false,
	    	      success: function(data) {
	    	      $(".tabs").children("tbody").children("tr").remove();
	             actualiserRechercheEmp(data["listEmp"]);
	    	      },
	    	      error:function(data)
	    	      {
	    	    	  console.log(data);
	    	      }
	    	    });
			  return false;
	      });
		  /*actualiser la rechercher Emp*/
		 function actualiserRechercheEmp(data)
		  {
			 var chefp="";
			  var tablbody=$(".tabs").children("tbody");
				  $.each(data,function(vk,vv) {
					  if(vv["ischef"]=="oui")
						  chefp="css/chef.png";
					  else
						  chefp="css/dchef.png";

					var tr=$('<tr>'+
	                           '<td><span >'+vv["nom"]+'</span></td>'+
	                           '<td><span >'+vv["prenom"]+'</span></td>'+
	                           '<td><span>'+vv["telIntern"]+'</span></td>'+
	                           '<td><span>'+vv["email"]+'</span></td>'+
	                           '<td><span>'+vv["nomd"]+'</span></td>'+
	                           '<td><a href="Controle?act=etrechef&idemp='+vv["numEmploye"]+'&iddept='+vv["numDept"]+'">'+
	                            '<img alt="chef" class="cheficone" src="'+chefp+'"></a>'+
	                            '</td>'+
	                          '<td>'+
	                          '<a href="Controle?act=getemp&id='+vv["numEmploye"]+'"><img alt="modify" class="modifyicone emmd" src="css/modify.png"></a>'+
	                          '<a href="Controle?act=supprimeremp&id='+vv["numDept"]+'"><img alt="delete" class="deleteicone" src="css/delete.png"></a>'+
	                          '</td></tr>');
					  $(tablbody).append(tr);
					  
	            });
		  }
	  
		 /*ajax search dept */
		  $('.subfildept').click(function(event) {  
	          var searchval=$("#valsh").val();
	          var seachfilter=$('#filtersh').val();
	       $.ajax({
	    	      type: "GET",
	    	      url: "ajax/searchDeptAjax.jsp",
	    	      datatype: "json",
	    	      contentType: "application/json; charset=utf-8",
	    	      data: ({shval:searchval,shfilter:seachfilter}),
	    	      cache: false,
	    	      success: function(data) {
	    	       $(".tabs").children("tbody").children("tr").remove();
	    	      actualiserRechercheDept(data["listDept"]);
	    	      },
	    	      error:function(data)
	    	      {
	    	    	  console.log(data)
	    	      }
	    	    });
			  return false;
	      });
		  
		  /*actualiser la rechercher*/
		  function actualiserRechercheDept(data)
		  {
			  var tablbody=$(".tabs").children("tbody");
				  $.each(data,function(vk,vv) {
					  var tr=$('<tr><td class="lalign">'+vv["nom"]+'</td>'+
	                            '<td>'+vv["nom_chef"]+'</td>'+
	                            '<td>'+vv["nbr_emp"]+'</td>'+
	                            '<td>'+vv["numAgendaDept"]+'</td>'+
	                            '<td><a href="Controle?act=getdept&id='+vv["num"]+'"><img alt="modify" class="modifyicone" src="css/modify.png"></a></td>'+
	                           '</tr>');
					  $(tablbody).append(tr);
					  
	            });
		  }
	  
	  
	  
	  
	  
	  
	  
	  
	  });