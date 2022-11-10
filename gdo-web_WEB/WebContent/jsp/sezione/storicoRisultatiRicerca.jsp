<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<div id="content">
<div id="innerContent">
	<div class="inner">
		<h3 class="pagetitle">Elenco Storico Sezione</h3>
			<div style="visibility:hidden" id="message">
				<p align="center">Caricamento in corso...</p>
			</div>
			<div id="errorMessage">
			</div>
			
			<div id="main">
				
				<jsp:include page="../tiles/messages.jsp"></jsp:include> 
				
				<s:if test="%{elencoSezioni.size()>0}">
				<s:set name="selectedPageSize" value="risultatiPerPagina" scope="request"/>
				
					<div class="boxformup"></div>
					<div class="boxform">	
							<display:table id="risultatiRicerca" name="elencoSezioni" pagesize="${selectedPageSize}" export="false" requestURI="" class="simple">
								<display:column property="titolo" title="Titolo" sortable="true"/>
								<display:column property="descrizioneValida" title="Valida" sortable="true" />
								<display:column property="owner" title="Autore" sortable="true"/>
								<display:column property="dataCreazione" format="{0,date,dd/MM/yyyy}" title="Data Creazione" sortable="true"/>											
								<display:column property="ownerModifica" title="Autore Modifica" sortable="true" />
								<display:column property="dataModifica" format="{0,date,dd/MM/yyyy}" title="Data Modifica" sortable="true" />											
								<display:column title="Dettaglio"  url="/sezione!visualizza.action" paramId="sezione.id" paramProperty="id" style="text-align:center;">
                                 			<img src="img/ico_information.gif" alt="Dettaglio Sezione"/>
                                 		</display:column>                                                                       
								<display:setProperty name="paging.banner.placement" value="bottom" />
							</display:table></div> <!-- boxform -->
				</s:if>	
			

			</div>	<!-- #main -->
		</div> <!-- inner -->
		
	</div>

</div>

<jsp:include page="/jsp/tiles/menu.jsp"></jsp:include>
