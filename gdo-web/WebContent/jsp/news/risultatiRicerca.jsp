<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<div id="content">
<div id="innerContent">
	<div class="inner">
		<h3 class="pagetitle">Elenco News</h3>
			<div style="visibility:hidden" id="message">
				<p align="center">Caricamento in corso...</p>
			</div>
			<div id="errorMessage">
			</div>
			
			<div id="main">
				
				<jsp:include page="../tiles/messages.jsp"></jsp:include> 
				
				<s:if test="%{elencoNews.size()>0}">
					<div class="boxformup"></div>
					<div class="boxform">	
					
					
					
					<!--<s:form action="news!modifica.action" theme="gdo_ext" id="form-dettaglio-news"   enctype="multipart/form-data" method="POST">-->
						<s:set name="selectedPageSize" value="risultatiPerPagina" scope="request"/>
						<display:table id="risultatiRicerca" name="elencoNews" pagesize="${selectedPageSize}" export="false" requestURI="" class="simple">
								<display:column property="titolo" title="Titolo" sortable="true"/>
								<display:column property="owner" title="Autore" sortable="true"/>
								<display:column property="descrizioneStato" title="Stato" sortable="true" />
								<display:column property="dataInizioPubblicazione" title="Data Inizio" sortable="true" />
								<display:column property="dataFinePubblicazione" title="Data Fine" sortable="true" />
								<display:column property="descrizioneTipoUtente" title="Destinatari" sortable="true" />
								<display:column property="allegato.nomeFile" title="Allegato" sortable="true" url="/allegato!download.action" paramId="id" paramProperty="allegato.id" style="text-decoration: underline;" />
								<display:column property="visualizzaPopUpDescr" title="Visualizza Pop Up" sortable="true" />
								<display:column title="Dettaglio" url="/news!modifica.action" paramId="news.id" paramProperty="id" style="text-align:center;">
                           			<img src="img/ico_information.gif" alt="Dettaglio News"/>
                           		</display:column>                                                                       
								<display:setProperty name="paging.banner.placement" value="bottom" />
							</display:table>
						<!--</s:form>-->		
					</div> <!-- boxform -->
				</s:if>	
			

			</div>	<!-- #main -->
		</div> <!-- inner -->
		
	</div>

</div>

<jsp:include page="/jsp/tiles/menu.jsp"></jsp:include>
