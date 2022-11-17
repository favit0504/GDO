<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="content">
<div id="innerContent">
				<div class="inner">
					<h3 class="pagetitle">Operazione eseguita</h3>
						<div style="visibility:hidden" id="message">
							<p align="center">Caricamento in corso...</p>
						</div>
						<div id="errorMessage">
						</div>
						
						<div id="main">
							
							<!-- TODO: questo va fatto con tiles? Mi da problema -->
							<jsp:include page="../tiles/messages.jsp"></jsp:include> 
							
						<a href="<s:url action="news!visualizza.action"><s:param name="news.id" value="news.id" /></s:url>" class="storico">Vai alla news</a>

						</div>	<!-- #main -->
					</div> <!-- inner -->
					
				</div>

</div>

<jsp:include page="/jsp/tiles/menu.jsp"></jsp:include>
