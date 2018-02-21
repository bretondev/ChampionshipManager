<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
  <head>
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  	
  	<style type="text/css">
	    /* Add some padding on document's body to prevent the content
	    to go underneath the header and footer */
	    body{        
	        padding-top: 100px;
	        padding-bottom: 40px;
	        background: #e8f0ff;
	    }
	    #body{
	        width: 80%;
	        margin: 40px auto 0 auto; /* Center the DIV horizontally */
	    }
	    #pageheader, #pagefooter{
	        width: 100%;
	        position: fixed;        
	        background: #1a64db;
	        padding: 0 0 10px 0;
	        color: #fff;
	    }
	    #pageheader{
	        top: 0;
	    }
	    #pagefooter{
	        bottom: 0;
	    }    
	</style>
  
  </head>
  <body>
    <div id="pageheader">
    
		<nav class="navbar navbar-inverse">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand">ChampionshipManager</a>
		    </div>
		    <ul class="nav navbar-nav">
		      <li><a href="${pageContext.request.contextPath}/championship/">Compétitions</a></li>
		      <li><a href="${pageContext.request.contextPath}/team/">Equipes</a></li>
		      <li><a href="${pageContext.request.contextPath}/player/">Joueurs</a></li>
		    </ul>
		  </div>
		</nav>
		
      <jsp:invoke fragment="header"/>
      
    </div>
    <div id="body">
      <jsp:doBody/>
    </div>
    <div id="pagefooter">
      <jsp:invoke fragment="footer"/>
    </div>
  </body>
</html>