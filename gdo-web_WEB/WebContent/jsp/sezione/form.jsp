<%@ taglib
	uri="http://tiles.apache.org/tags-tiles"
	prefix="tiles"%>
<%@ taglib
	prefix="s"
	uri="/struts-tags"%>
<%@page
	pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
	
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<div id="content">
	<div id="innerContent">
		<div class="inner">
			<h3 class="pagetitle"><s:property value="titoloPagina" /></h3>
			<div id="main">


				<div class="boxformup"></div>
				<div class="boxform boxformricerca"	id="wizard-n" style="margin-top: 0px">					
					<s:fielderror escape="false" fieldName="errori-form" />
					<s:fielderror fieldName="titolo" />
					<s:fielderror fieldName="descrizione" />
					<s:fielderror fieldName="stato" />
					
					<s:form	action="%{formAction}" theme="gdo_ext" id="form-sezione" enctype="multipart/form-data"	method="POST">


						<s:hidden id="modalita_sola_lettura" value="%{isModalitaVisualizza()}" />


						<div class="sottosteps sezioni clearfix">
							<ul>
								<li class="current"><a
									name="sezione0"
									id="tab_sezione_0">Generale</a></li>
								<li><a
									name="sezione1"
									id="tab_sezione_1">Utenti abilitati</a></li>
							</ul>
						</div>

						<div class="clearfix"></div>
						
						<div id="content_tab_sezione_0"	class="content_tab_sottosteps first">
							<section>
							
								<s:hidden name="sezione.id" />
								<s:hidden name="sezione.codice" />
								<s:hidden name="sezione.owner" />
								<s:hidden name="sezione.dataCreazione" />
								<s:hidden name="sezione.valida" />
								
								<s:textfield cssStyle="width:500px;" maxlength="255" readonly="%{isModalitaVisualizza()}"
									name="sezione.titolo" label="Titolo della sezione*"	cssClass="input"
									size="50" />
									
								<s:textarea cssStyle="width:500px; resize: none;" maxlength="1024" readonly="%{isModalitaVisualizza()}"
								name="sezione.descrizione" label="Descrizione della sezione*" cols="20"
								rows="10" wrap="true"></s:textarea>
								
								<s:radio disabled="%{isModalitaVisualizza()}" name="sezione.stato"
								cssClass="radio-form" label="Stato"	list="opzioniStato" />
								
								<br/>
								
								<div class="clearfix">
		                        	<p>* = Campo obbligatorio</p>
		                        	<p></p>
		                        </div>
		                        
		                        <br/>
								
								<s:if test="sezione.valida == 1">
									<s:if test="#session.documentiValidi.size() > 0">
										
										<label class="label" style="width: 100%">
											Lista documenti:
										</label>
										
										<s:set name="selectedPageSize" value="risultatiPerPagina" scope="request"/>
										<display:table id="risultatiRicerca" name="${session.documentiValidi}" pagesize="${selectedPageSize}" export="false" requestURI="" class="simple">
											<display:column property="titolo" title="Titolo" sortable="true"/>
											<display:column property="descrizione" title="Descrizione" sortable="true"/>
											<display:column property="descrizioneStato" title="Stato" sortable="true" />										
											<display:column property="descrizioneTipoUtente" title="Destinatari" sortable="true" />
											<s:if test="abilitaNuovoDocumento">
												<display:column title="Dettaglio" url="/documento!modifica.action" paramId="documento.id" paramProperty="id" style="text-align:center;" >
													<img src="img/ico_information.gif" alt="Dettaglio Documento"/>
												</display:column>
											</s:if>
											<s:else>
												<display:column title="Dettaglio" url="/documento!visualizza.action" paramId="documento.id" paramProperty="id" style="text-align:center;" >
													<img src="img/ico_information.gif" alt="Dettaglio Documento"/>
												</display:column>
											</s:else>
		                               		<display:setProperty name="paging.banner.placement" value="bottom" />
										</display:table>
									</s:if>
								</s:if>
								
								<br />
							
								<s:if test="!isModalitaCreazione()">
									<a
										href="<s:url action="sezione!storico.action"><s:param name="sezione.codice" value="sezione.codice" /></s:url>"
										class="storico">Mostra storico</a>
								</s:if>
								
								<s:if test="abilitaNuovoDocumento">
									<a
										href="<s:url action="documento!form-nuovo.action"><s:param name="idSezione" value="sezione.id" /></s:url>"
										class="storico">Aggiungi Documento</a>
								</s:if>

								

							</section>
						</div>

						<div
							id="content_tab_sezione_1"
							class="content_tab_sottosteps">

							
							<table>
								<tr style="height: 35px">
									<td	valign="top" width="270">
										<label for="utente_da_abilitare" class="label" style="width: 100%">
											Utenza da abilitare:
										</label>
									</td>
									<td valign="top">
									<!-- readonly="<s:property value="isModalitaVisualizza()"/>"  NON FUNZIONA, fare un tag personalizzato
									come doubleselect oppure fare un IF sulla property -->
										<input type="text"
										name="utente_da_abilitare" size="50" maxlength="8"
										value="" id="utente_da_abilitare" class="input">
									</td>
									<td valign="top">
										<input	type="button" name="addAssociations" value="Aggiungi" class="submit" 
										id="addAssociations_utenze_abilitate" title="Aggiungi" alt="Aggiungi">
									</td>
								</tr>
								<tr>
									<td	valign="top" width="270">
										<label for="utenze_abilitate" class="label"	style="width: 100%">Utenze abilitate:</label>
									</td>
									<td valign="top">
										<s:select size="10" multiple="true" name="utenze_abilitate"
										id="utenze_abilitate" list="utenze_abilitate" cssStyle="width: 100%"></s:select>
									</td>
									<td valign="top">
										<input type="button" name="removeAssociations" value="Rimuovi" 
										id="removeAssociations_utenze_abilitate" class="submit"	title="Rimuovi"	alt="Rimuovi">
									</td>
								</tr>
							</table>
						</div>

						<s:if test="!isModalitaVisualizza()">
							<s:submit value="Salva" cssClass="submit" style="cursor:pointer;font-family: Verdana, Arial, Helvetica, sans-serif;border:0;margin:5px"/>
						</s:if>
					</s:form>
				</div>

			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	$(function () {

	/*wizard personalizzati senza libreria*/          	
   	function creaSottoWizard(classeDiv) {
       	
       	$("div.sottosteps." + classeDiv +" li a").each( function()	{

	        $(this).unbind('click').click( function() {
	
				//navigation
		       	$("div.sottosteps." + classeDiv + " li a").parent().removeClass("current");
		       	$(this).parent().addClass("current");
		
		      	//content
		      	$("div.sottosteps." + classeDiv + " li a").each(function () {
		      		$('#content_' + $(this).attr('id')).hide();
		       	});
		        $('#content_' + $(this).attr('id')).show();
	
	          });
   		 });	
  	}	
  	
   	creaSottoWizard("sezioni");

    /* Auto selezione al momento dell'invio degli utenti abilitati */
    $("#form-sezione").submit(function() {
	   	$("#utenze_abilitate > option").each(function() {
	    	$(this).prop("selected",true);
		});
    });
    
    <s:if test="isModalitaVisualizza() == false"> 

	    /* Eventi associati ai pulsanti aggiungi/elimina utenti abilitati */
		 $("#addAssociations_utenze_abilitate").unbind('click').click( function() { 
		 
		 	var valoreInserito = $("#utente_da_abilitare").val();			
			if ( valoreInserito != null && $.trim(valoreInserito) != "" )
		 	{
				var valoreGiaPresente = false;
				$('#utenze_abilitate option').each(function(){
				    if (this.value == valoreInserito)
						valoreGiaPresente = true;
				});
	
				if (!valoreGiaPresente) {			 	
		 			$("#utenze_abilitate").append( '<option value="' + valoreInserito + '">' + valoreInserito + '</option>');
		 			ordinaAlfabeticamenteSelect("utenze_abilitate");
				}
		 	}	
	 		});
	
		
		 $("#removeAssociations_utenze_abilitate").unbind('click').click( function() { 
		 
			$("#utenze_abilitate option:selected").each( function()	{
				$(this).remove();	
			 	ordinaAlfabeticamenteSelect("utenze_abilitate");		 
		  	});
		});

    </s:if>


});
</script>

<jsp:include page="/jsp/tiles/menu.jsp"></jsp:include>