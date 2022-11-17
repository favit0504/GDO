<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<div id="content">
	<div id="innerContent">
		<div class="inner">
			<h3 class="pagetitle"><s:property value="titoloPagina" /></h3>
			<div id="main">
			
			
				<div class="boxformup"></div>
				<div class="boxform">
					<div class="boxformricerca">
							
						<s:fielderror escape="false" fieldName="errori-form" />
        				<s:fielderror fieldName="titolo" />
        				<s:fielderror fieldName="descrizione" />  
        				<s:fielderror fieldName="stato" />      				
        				
						<s:form action="%{formAction}" theme="gdo_ext" id="form-documento" enctype="multipart/form-data" method="POST">
				
							<s:hidden id="modalita_sola_lettura" value="%{isModalitaVisualizza()}" />
		
			                <div id="wizard-n">
			                    <h3 style="display:none;">Generale</h3>
			                    <section style="display:none;">
								
									<s:hidden name="documento.id"/>
									<s:hidden name="documento.codice"/>								
									<s:hidden name="documento.owner"/>
									<s:hidden name="documento.dataCreazione"/>
									<s:hidden name="documento.sezione.id"/>
									<s:hidden name="documento.sezione.titolo"/>
									<s:hidden name="documento.nomeFile"/>
									<s:hidden name="documento.svecchiato"/>
									
									<s:textarea maxlength="255" disabled="true" readonly="true" name="documento.sezione.titolo" cssStyle="width:75%;resize: none;" label="Sezione di appartenenza" cols="20" rows="2" wrap="true"></s:textarea>	
			                        	
			                        <s:textfield cssStyle="width:75%;" maxlength="255" readonly="%{isModalitaVisualizza()}" name="documento.titolo" label="Titolo del documento*" cssClass="input" size="255" />
			                        
			                        <s:textarea cssStyle="width:75%; resize: none;" maxlength="1024" readonly="%{isModalitaVisualizza()}" name="documento.descrizione" label="Descrizione del documento*" cols="20" rows="10" wrap="true"></s:textarea>	
			                        		                        			                        				                      
			   						<s:radio disabled="%{isModalitaVisualizza()}" name="documento.stato" cssClass="radio-form" label="Stato" list="opzioniStato" />			   									   						
			    					
			                        <s:checkboxlist disabled="%{isModalitaVisualizza()}" name="tipologiaUtenteCheck" cssClass="radio-form" label="Destinatari del documento*" list="opzioniTipologiaUtenteDocumento"></s:checkboxlist>
			                        
			                        <br/><br/>
			                        
			                        <div class="clearfix">
				                        <p>* = Campo Obbligatorio</p>
				                        <p></p>
			                        </div>
			                        
			                        <s:if test="!isModalitaCreazione()">	
			                        	<a href="<s:url action="documento!storico.action"><s:param name="documento.codice" value="documento.codice" /></s:url>" class="storico">Mostra storico</a> 
			                        </s:if>
			                        
			                        <s:if test="isModalitaModifica() or isModalitaCreazione()">	
			                        	<a href="<s:url action="sezione!modifica.action"><s:param name="sezione.id" value="documento.sezione.id" /></s:url>" class="storico">Vai alla sezione</a> 
			                        </s:if>
			                        
			                        <s:if test="isModalitaVisualizza()">	
			                        	<a href="<s:url action="sezione!visualizza.action"><s:param name="sezione.id" value="documento.sezione.id" /></s:url>" class="storico">Vai alla sezione</a> 
			                        </s:if>
			                        
			                        <div class="clearfix">
				                        <p></p>
				                        <p></p>
			                        </div>
				                					
				                	        
				               </section>
			
			                    <h3 style="display:none;">Ente</h3>
			                    <section style="display:none;">
			                          
				                    <div class="sottosteps enti clearfix">
					                    <ul>
						                    <li class="current"><a name="enti1"  id="tab_visibilita_enti_0">Singolo</a></li>
						                    <li><a name="enti2" id="tab_visibilita_enti_1">Tipologia</a></li>
						                    <li><a name="enti3" id="tab_visibilita_enti_2">Provincia</a></li>
						                    <li><a name="enti4" id="tab_visibilita_enti_3">Regione</a></li>
						                    <li><a name="enti5" id="tab_visibilita_enti_4">Servizio</a></li>
					                    </ul>
				                    </div>
			   
			                         <div id="content_tab_visibilita_enti_0" class="content_tab_sottosteps first">
				                 
				                     	  <s:textfield readonly="%{isModalitaVisualizza()}"  name="documento.filtroEnte.codiceEnte" label="Codice ente" cssClass="input" maxlength="5" size="5" />
				                     	  <s:textfield readonly="%{isModalitaVisualizza()}"  name="documento.filtroEnte.tipoUfficioEnte" label="Tipo Ufficio" cssClass="input" maxlength="1" size="1" />
				                     	  <s:textfield readonly="%{isModalitaVisualizza()}"  name="documento.filtroEnte.codiceUfficioEnte" label="Codice Ufficio" cssClass="input" maxlength="6" size="6" />
				                     
			   						 </div>
			                          
			                        <div id="content_tab_visibilita_enti_1" class="content_tab_sottosteps">                          
				                     	<s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniTipoEnte" name="tipo_ente_options" id="tipo_ente_options"
				   						 doubleList="opzioniScelteTipoEnte" doubleId="tipo_ente_selected" doubleName="documento.filtroTipologiaEnte.valori"></s:doubleselect>
			   						</div>
			   					
			                          
									<div id="content_tab_visibilita_enti_2"  class="content_tab_sottosteps"> 							
				                    	<s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniProvincia" name="provincia_ente_options" id="provincia_ente_options"
				   						 doubleList="opzioniScelteProvinciaEnte" doubleId="provincia_ente_selected" doubleName="documento.filtroProvinciaEnte.valori"></s:doubleselect>
			   						</div> 
			                          
			                        <div id="content_tab_visibilita_enti_3"  class="content_tab_sottosteps">
				                         <s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniRegione" name="regione_ente_options" id="regione_ente_options"
				   						 doubleList="opzioniScelteRegioneEnte" doubleId="regione_ente_selected" doubleName="documento.filtroRegioneEnte.valori"></s:doubleselect>
			   						</div>
			                        
			                        <div id="content_tab_visibilita_enti_4"  class="content_tab_sottosteps">
				                         <s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniServizioEnte" name="servizio_ente_options" id="servizio_ente_options"
				   						 doubleList="opzioniScelteServizioEnte" doubleId="servizio_ente_selected" doubleName="documento.filtroServizioEnte.valori"></s:doubleselect>
			                        </div>
			                        
			                    </section>
			                    
			                    <h3 style="display:none;">Agente</h3>
			                    <section style="display:none;">
			                    
			                    	<div class="sottosteps agenti clearfix">
					                	<ul>
						                	<li class="current"><a name="agenti1" id="tab_visibilita_agenti_0">Ambito</a></li>
						                    <li><a name="agenti2" id="tab_visibilita_agenti_1">Regione</a></li>
						                    <li><a name="agenti3" id="tab_visibilita_agenti_2">Raggruppamento societario</a></li>
						                    <li><a name="agenti4" id="tab_visibilita_agenti_3">Servizio</a></li>
					                    </ul>
				                    </div>
			   
			                        <div id="content_tab_visibilita_agenti_0" class="content_tab_sottosteps first"> 
			                     	  	 <s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniAmbito" name="provincia_agente_options" id="provincia_agente_options"
			   						 	 doubleList="opzioniScelteAmbito" doubleId="provincia_agente_selected" doubleName="documento.filtroAmbito.valori"></s:doubleselect>
			                       	</div>
			                       
			                       	<div id="content_tab_visibilita_agenti_1" class="content_tab_sottosteps">
										 <s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniRegione" name="regione_agente_options" id="regione_agente_options"			                          
									     doubleList="opzioniScelteRegioneAgente" doubleId="regione_agente_selected" doubleName="documento.filtroRegioneAgente.valori"></s:doubleselect>
			   						</div>
			   						
			   						<div id="content_tab_visibilita_agenti_2" class="content_tab_sottosteps">
			   							<s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniRaggruppamentoSocietario" 
			   							name="societa_options" id="societa_options" doubleList="opzioniScelteRaggruppamentoSocietario"
			   							 doubleId="societa_selected" doubleName="documento.filtroSocieta.valori"></s:doubleselect>
			   						</div>
			   						 
			   						<div id="content_tab_visibilita_agenti_3" class="content_tab_sottosteps"> 
			   						  <s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniServizioAgente" name="servizio_agente_options" 
			   						  doubleList="opzioniScelteServizioAgente" doubleId="servizio_agente_selected" doubleName="documento.filtroServizioAgente.valori"></s:doubleselect>
			                        </div>
			                       
			                   </section>
			
								<h3 style="display:none;">File</h3>
			                    <section style="display:none;">
	<%
		/**
			Logica dietro la sezione degli allegati:
				- se ho un allegato caricato, mostro l'allegato corrente
				- se non ho allegato caricato e non sono in creazione, mostro un messaggio di nessun allegato
				- se sono in creazione o in modifica inserisco il form per caricare nuovi allegati. Il form
				 in caso di allegato preesistente nella modifica e` inizialmente nascosto.
		
		
		*/

	%>						
										
									<s:if test="%{documento.blob!=null}">					
										<div id="bloccoAllegatoCaricato">
										
											<s:set name="styleBoxCaricaAllegato" value="%{'display:none;'}"/>
											
											<s:hidden id="mantieniDocumento" name="mantieniDocumento" value="true"></s:hidden>
										
											<p><br/>Esiste un file associato a questo documento:</p>
											<div class="clearfix">
												<s:url var="urlAllegato" value="/documento!download.action?">
			    									<s:param name="id" value="%{documento.id}"/>
												</s:url>
												
													<label class="label">Documento:</label>
														<s:if test="documento.valida == 1">
															<s:a value="%{urlAllegato}">
																<img style="vertical-align:middle" src="<s:url value="/img/save.png" />" border="none"/>
																<span style="color:#4c4c4c">
																<em><s:property value="documento.nomeFile"/></em>
															</s:a>
														</s:if>
														<s:else>
															<span style="color:#4c4c4c"><s:property value="documento.nomeFile"/></span>
														</s:else>
											</div>
											<!--<div class="clearfix">
											<label class="label">Nome file:</label>
											<span style="color:#4c4c4c"><s:property value="documento.nomeFile"/></span>
											</div>-->
											<br/>
									
													
											<s:if test="isModalitaModifica()">									
												<a class="storico" href="#" onclick="return cancellaDocumento();">Elimina file</a>
											</s:if>
										</div>
										
									</s:if>										
									<s:elseif test="!isModalitaCreazione()">
										<p>Nessun file &egrave; associato a questo documento.</p>
									</s:elseif>
									
									
														
									<s:if test="!isModalitaVisualizza()">																
										<div id="formCaricaAllegato" style="<s:property value="#styleBoxCaricaAllegato"/>">	
								
											<% /* L'iterator e` una predisposizione che avevamo fatto a un numero di allegati > 1 */ %>									
						                    <s:iterator value="numeroFileDocumento" status="i">
						                    	<div class="boxformup separatore-form"></div>
											    <s:file name="documento_%{#i.index}" label="File documento*" size="40" />
											</s:iterator>
		
										</div>
										
									<div class="clearfix">
				                        <p>* = Campo obbligatorio</p>
				                        <p></p>
			                        </div>
			                        
									</s:if>									

			                        
				                	
				
			                    </section>
			                 
			                </div>
			                
						</s:form>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>

 <script type="text/javascript"><!--		  
$(function () {

	/* Wizard alto livello libreria jquery-steps */
			  
	var labels_ita = {
	    current: "current step:",
	    pagination: "Pagination",
	    finish: "Salva",
	    next: "Successivo",
	    previous: "Precedente",
	    loading: "Loading ..."
	};
  
      $("#wizard-n").steps({
         headerTag: "h3",
         bodyTag: "section",
         transitionEffect: "slideLeft",
         enablePagination: true,         
         enableAllSteps: true,
         autoFocus: false,
         showFinishButtonAlways: <s:property value="!isModalitaVisualizza()" />,
         enableFinishButton: <s:property value="!isModalitaVisualizza()" />,
         labels: labels_ita,
         onFinished: function (event, currentIndex)
         {
    	<% 	/**
    		Il codice Javascript qua sotto serve a 
    		selezionare prima dell'invio gli elementi spostati
    		nella colonna di destra (selezionati)
    		altrimenti non vengono inviati tramite form html    		
    		**/ %>    		
    	
			$("#tipo_ente_selected > option").each(function() {
			    this.setAttribute("selected","selected");
			});

			$("#provincia_ente_selected > option").each(function() {
			    this.setAttribute("selected","selected");
			});

			$("#regione_ente_selected > option").each(function() {
			    this.setAttribute("selected","selected");
			});

			$("#servizio_ente_selected > option").each(function() {
			    this.setAttribute("selected","selected");
			});

			$("#provincia_agente_selected > option").each(function() {
			    this.setAttribute("selected","selected");
			});

			$("#regione_agente_selected > option").each(function() {
			    this.setAttribute("selected","selected");
			});

			$("#servizio_agente_selected > option").each(function() {
			    this.setAttribute("selected","selected");
			});
			
			$("#societa_selected > option").each(function() {
			    this.setAttribute("selected","selected");
			});


			
            $("#form-documento").submit();
         }

     });



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
    	
   	creaSottoWizard("enti");
   	creaSottoWizard("agenti");
        
        function eliminaBottoni() {
       	
       	$("a[href*=#next]").remove( );	
       	$("a[href*=#prev]").remove( );	

  	}
  	eliminaBottoni();
    	


});
--></script>

<jsp:include page="/jsp/tiles/menu.jsp"></jsp:include>