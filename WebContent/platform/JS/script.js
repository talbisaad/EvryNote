
  $( document ).ready(function() {
	  /* get param from url */
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
		  $(".modifnote").fadeOut();
		  $(".addalertt").fadeOut();
	  });
	  
	  $(".ajouterdept").on("click",function(){
		  $(".adddept").fadeIn();
		  $(".shadow").fadeIn();
		  return false;
	  });
	  $(".modifnote").on("click",function(){
		  $(".modifnote").fadeIn();
		  $(".shadow").fadeIn();
		  return false;
	  });
});
 