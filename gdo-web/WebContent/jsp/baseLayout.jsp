<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%><%@ taglib prefix="s" uri="/struts-tags" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<s:head/>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/displaytag.css">
<link rel="stylesheet" href="css/jquery-ui-1.11.4.min.css" type="text/css" />
<link rel="stylesheet" type="text/css" media="all" href="script/skins/aqua/theme.css" title="Aqua" />
<link rel="stylesheet" href="css/jquery-te.css" type="text/css" />
<link rel="stylesheet" href="css/jquery.alerts.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/jquery-confirm.min.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/tiktok.css" type="text/css" />

<script type="text/javascript" src="script/tiktok.min.js"></script>
<script type="text/javascript" src="script/funzioni.js"></script>
<script type="text/javascript" src="script/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="script/jquery.steps.min.js"></script>
<script type="text/javascript" src="script/script.js"></script>
<script type="text/javascript" src="script/calendar.js"></script>
<script type="text/javascript" src="script/calendar-it.js"></script>
<script type="text/javascript" src="script/jquery-te.min.js"></script>
<script type="text/javascript" src="script/jquery.alerts.js" ></script>
<script type="text/javascript" src="script/jquery.confirm.min.js" ></script>
<script type="text/javascript" src="script/jquery-1.1.2.js" ></script>
<script type="text/javascript" src="script/jquery-ui-1.11.4.custom.min.js" ></script>

<% //jquery UI inserito per anteprima news con lo stesso widget usato in SSO
   //La versione è di jqueryUI è diversa da quella di SSO perchè qui c'è un jquery piu nuovo
   //che non era compatibile con la versione 1.8.5 in uso a luglio 2015 su SSO. %>
<script type="text/javascript" src="script/jquery-ui-1.11.4.custom.min.js"></script> 
 
              
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
 </head>

<body class="gdo">
<s:form action="../autenticazione/home.do">
	<s:hidden name="service"/>
	<s:hidden name="indiceSelezionato"/>	
	<s:hidden name="history"/>
</s:form>
<!-- <div id="upper"></div> -->

	
	<div id="container" class="mod">
	 	
	 	
	 	<tiles:insertAttribute name="header" />
	 		
		<div id="body">
			<div id="subheader">Gestione Documentale</div>
			
				<div id="mprincipale">
					<ul>
						<li class="attivo">
							&nbsp;&nbsp;
							GESTIONE DOCUMENTALE
						</li>
					</ul>
				</div>
				<!-- /mprincipale -->
			
			

		
		
		
		
		   <tiles:insertAttribute name="content" />

		</div>

		<div id="footer">
		<div id="bottom"></div>
	</div>
	
</body>
</html>