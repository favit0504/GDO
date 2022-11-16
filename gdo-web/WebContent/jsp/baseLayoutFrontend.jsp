<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<head>
<s:head/>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<link rel="stylesheet" href="css/jquery-ui.css" type="text/css" />
<link rel="stylesheet" href="css/jquery-ui-1.8.22.custom.css" type="text/css" />
<script type="text/javascript" src="script/funzioni.js"></script>
<script type="text/javascript" src="script/script.js"></script>              
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" />
</title>
 
</head>

<body class="gdo">
<s:form action="../autenticazione/home.do">
	<s:hidden name="service"/>
	<s:hidden name="indiceSelezionato"/>	
	<s:hidden name="history"/>
</s:form>
<div id="upper"></div>

	
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
	<!-- 	<div id="bottom"></div> -->
	</div>
	
</body>
</html>