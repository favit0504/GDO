<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="content">
	<div id="innerContent">
		<div class="inner">
			<h3 class="pagetitle">Si &egrave; verificato un errore</h3>
			<div style="visibility: hidden" id="message">
				<p align="center">Caricamento in corso...</p>
			</div>
			
			<div id="main">
				<jsp:include page="messages.jsp"></jsp:include>
								
				
			</div>
		</div>
	
	</div>

</div>
<!--  TODO AGGIORNA RIFERIMENTO AL MENU -->
<jsp:include page="/jsp/tiles/menu.jsp"></jsp:include>
