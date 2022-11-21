<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<script>
function mostraWarningVisualizzaPopUp(){
    var testo = "Questa operazione comporta la modifica della news \""+ document.getElementById("titoloNewsPopUp").value +"\"  che non verrà più visualizzata tramite pop-up in fase di accesso al portale";
	if(document.getElementById("checkPopUp").checked){
		if(document.getElementById("titoloNewsPopUp") != null){
			var titoloNewsPopUp = document.getElementById("titoloNewsPopUp").value;			
			var r = $( "#dialog" ).dialog({
					    width: 600,
						modal: true,					
						buttons: {
							OK: function() {
						  		$( this ).dialog( "close" );
								document.getElementById("checkPopUp").checked = true;
							},
							Annulla: function() {
								$( this ).dialog( "close" );
							}
						}
						
						
				});
				$( "#dialog" ).text(testo);
				if (r == true) {
			    	document.getElementById("checkPopUp").checked = true;
				} else {
			    	document.getElementById("checkPopUp").checked = false;
				}

		}
	}
	}

</script>

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
        				<s:fielderror fieldName="testo" />
        				<s:fielderror fieldName="stato" />
        				<s:fielderror fieldName="dataInizioPubblicazione" />
        				<s:fielderror fieldName="dataFinePubblicazione" />
        				
						<s:form action="%{formAction}" theme="gdo_ext" id="form-news"  enctype="multipart/form-data" method="POST">
				
							<s:hidden id="modalita_sola_lettura" value="%{isModalitaVisualizza()}" />
		
			                <div id="wizard-n">
			                    <h3 style="display:none;">Generale</h3>
			                    <section style="display:none;">
								
									<s:hidden name="news.id"/>
									<s:hidden name="news.codice"/>								
									<s:hidden name="news.owner"/>
									<s:hidden name="news.dataCreazione"/>
									<s:hidden name="news.svecchiata"/>
									
			
			                        <s:textfield cssStyle="width:75%;" maxlength="255" readonly="%{isModalitaVisualizza()}" name="news.titolo" label="Titolo della news*" cssClass="input" size="50" />
			                        
			                        <s:textarea cssClass="reset-this" cssStyle="width:75%;" readonly="%{isModalitaVisualizza()}" name="news.testo" label="Testo della news*" cols="20" rows="20" wrap="true"></s:textarea>
			 			                                            
			                        <s:radio disabled="%{isModalitaVisualizza()}" name="news.stato" cssClass="radio-form" label="Stato" list="opzioniStato" />  </br>
					        
					        		<div class="clearfix">
							            <label class="label">Data Inizio Pubblicazione*:</label>
							            <input type="text" id="dataInizioPubblicazione" name="news.dataInizioPubblicazione" maxlength="10" size="10" value='<s:property value="news.dataInizioPubblicazione" />' />                
							            <input id="buttonCalendario" type="button" onclick="return showCalendar('dataInizioPubblicazione', '%d/%m/%Y',this);" class="calendario" />&nbsp;<s:label> gg/mm/aaaa </s:label><br/>																
									</div>
									
									<div class="clearfix">
							            <label class="label">Data Fine Pubblicazione:</label>
							            <input type="text" id="dataFinePubblicazione" name="news.dataFinePubblicazione" maxlength="10" size="10" value='<s:property value="news.dataFinePubblicazione" />' />                
							            <input id="buttonCalendario" type="button" onclick="return showCalendar('dataFinePubblicazione', '%d/%m/%Y',this);" class="calendario" />&nbsp;<s:label> gg/mm/aaaa </s:label>										
			    					</div>
			    
			                        <s:checkboxlist disabled="%{isModalitaVisualizza()}" name="tipologiaUtenteCheck" cssClass="radio-form" label="Destinatari della News*" list="opzioniTipologiaUtente"></s:checkboxlist>
			                        
			                        <br/>
			                        <div class="clearfix">
							            <label class="label">Visualizza come pop up:</label>
							            <s:checkbox id="checkPopUp" name="news.visualizzaPopUp" cssClass="radio-form" fieldValue="true" onclick="javascript:mostraWarningVisualizzaPopUp();" disabled="%{isModalitaVisualizza()}"></s:checkbox>							            							            										
			    					</div>
			    					<div id="warningPopUp">
							            	<s:if test="%{newsPopUp!=null}">
							            		<input type="hidden" value="<s:property value='%{newsPopUp.titolo}'/>" name="newsPopUp.titolo"  id="titoloNewsPopUp"/>
							            	</s:if>
							            </div>
			                        
			                        <div class="clearfix">
			                        	<p>* = Campo obbligatorio</p>
			                        	<p></p>
			                        </div>
			                        
			                        <br/>
			                        
			                        <s:if test="!isModalitaCreazione()">	
			                        	<a href="<s:url action="news!storico.action"><s:param name="news.codice" value="news.codice" /></s:url>" class="storico">Mostra storico</a> 
			                        </s:if>
				                	        
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
				                 
				                     	  <s:textfield readonly="%{isModalitaVisualizza()}"  name="news.filtroEnte.codiceEnte" label="Codice ente" cssClass="input" maxlength="5" size="5" />
				                     	  <s:textfield readonly="%{isModalitaVisualizza()}"  name="news.filtroEnte.tipoUfficioEnte" label="Tipo Ufficio" cssClass="input" maxlength="1" size="1" />
				                     	  <s:textfield readonly="%{isModalitaVisualizza()}"  name="news.filtroEnte.codiceUfficioEnte" label="Codice Ufficio" cssClass="input" maxlength="6" size="6" />
				                     
			   						 </div>
			                          
			                        <div id="content_tab_visibilita_enti_1" class="content_tab_sottosteps">                          
				                     	<s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniTipoEnte" name="tipo_ente_options" id="tipo_ente_options"
				   						 doubleList="opzioniScelteTipoEnte" doubleId="tipo_ente_selected" doubleName="news.filtroTipologiaEnte.valori"></s:doubleselect>
			   						</div>
			   					
			                          
									<div id="content_tab_visibilita_enti_2"  class="content_tab_sottosteps"> 							
				                    	<s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniProvincia" name="provincia_ente_options" id="provincia_ente_options"
				   						 doubleList="opzioniScelteProvinciaEnte" doubleId="provincia_ente_selected" doubleName="news.filtroProvinciaEnte.valori"></s:doubleselect>
			   						</div> 
			                          
			                        <div id="content_tab_visibilita_enti_3"  class="content_tab_sottosteps">
				                         <s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniRegione" name="regione_ente_options" id="regione_ente_options"
				   						 doubleList="opzioniScelteRegioneEnte" doubleId="regione_ente_selected" doubleName="news.filtroRegioneEnte.valori"></s:doubleselect>
			   						</div>
			                        
			                        <div id="content_tab_visibilita_enti_4"  class="content_tab_sottosteps">
				                         <s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniServizioEnte" name="servizio_ente_options" id="servizio_ente_options"
				   						 doubleList="opzioniScelteServizioEnte" doubleId="servizio_ente_selected" doubleName="news.filtroServizioEnte.valori"></s:doubleselect>
			                        </div>
			                        
			                    </section>
			                    
			                    <h3 style="display:none;">Agente</h3>
			                    <section style="display:none;">
			                    
			                    	<div class="sottosteps agenti clearfix">
					                	<ul>
						                	<li class="current"><a name="agenti1"  id="tab_visibilita_agenti_0">Ambito</a></li>
						                    <li><a name="agenti2" id="tab_visibilita_agenti_1">Regione</a></li>
						                    <li><a name="agenti3" id="tab_visibilita_agenti_2">Raggruppamento societario</a></li>
						                    <li><a name="agenti4" id="tab_visibilita_agenti_3">Servizio</a></li>
					                    </ul>
				                    </div>
			   
			                        <div id="content_tab_visibilita_agenti_0" class="content_tab_sottosteps first"> 
			                     	 	 <s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniAmbito" name="provincia_agente_options" id="provincia_agente_options"
			   						 	 doubleList="opzioniScelteAmbito" doubleId="provincia_agente_selected" doubleName="news.filtroAmbito.valori"></s:doubleselect>			                    
			                       	</div>
			                       
			                       	<div id="content_tab_visibilita_agenti_1" class="content_tab_sottosteps">
			                          <s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniRegione" 
			                          name="regione_agente_options" id="regione_agente_options"			                          
			   						 doubleList="opzioniScelteRegioneAgente" doubleId="regione_agente_selected"
			   						  doubleName="news.filtroRegioneAgente.valori"></s:doubleselect>
			   						</div>
			   						 
									<div id="content_tab_visibilita_agenti_2" class="content_tab_sottosteps">
			   							<s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniRaggruppamentoSocietario" 
			   							name="societa_options" id="societa_options" doubleList="opzioniScelteRaggruppamentoSocietario"
			   							 doubleId="societa_selected" doubleName="news.filtroSocieta.valori"></s:doubleselect>
			   						</div>
			   						
			   						<div id="content_tab_visibilita_agenti_3" class="content_tab_sottosteps"> 
			   						  <s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniServizioAgente" name="servizio_agente_options" 
			   						 doubleList="opzioniScelteServizioAgente" doubleId="servizio_agente_selected"
			   						  doubleName="news.filtroServizioAgente.valori"></s:doubleselect>
			                        </div>
			                       
			                   </section>
			                   
			                     <h3 style="display:none;">Utenti ICT</h3>
			                    <section style="display:none;">
			                     	<div class="sottosteps altriUtenti clearfix">
					                	<ul>
						                	<li class="current"><a name="altriUtenti1"  id="tab_visibilita_altriUtenti_0">Servizio</a></li>
					                    </ul>
				                    </div>
			                       <div id="content_tab_visibilita_altriUtenti_0"  class="content_tab_sottosteps first">
				                         <s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniServizioAltriUtenti" name="servizio_altriUtenti_options" id="servizio_altriUtenti_options"
				   						 doubleList="opzioniScelteServizioAltriUtenti" doubleId="servizio_altriUtenti_selected" 
				   						 doubleName="news.filtroServizioAltriUtenti.valori"></s:doubleselect>
			                        </div>
			                    </section>

			                     <h3 style="display:none;">Utenti esterni</h3>
			                    <section style="display:none;">
			                     	<div class="sottosteps utenteEsterno clearfix">
					                	<ul>
						                	<li class="current"><a name="utenteEsterno"  id="tab_visibilita_utenteEsterno_0">Servizio</a></li>
					                    </ul>
				                    </div>
			                       <div id="content_tab_visibilita_utenteEsterno_0"  class="content_tab_sottosteps first">
				                         <s:doubleselect disabled="%{isModalitaVisualizza()}" list="opzioniServizioUtenteEsterno" name="servizio_utenteEsterno_options" id="servizio_utenteEsterno_options"
				   						 doubleList="opzioniScelteServizioUtenteEsterno" doubleId="servizio_utenteEsterno_selected"
				   						 doubleName="news.filtroServizioUtenteEsterno.valori"></s:doubleselect> 
			                        </div>
			                    </section>
			
			
								<h3 style="display:none;">Allegato</h3>
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
										
									<s:if test="%{news.allegato!=null}">					
										<div id="bloccoAllegatoCaricato">
										
											<s:set name="styleBoxCaricaAllegato" value="%{'display:none;'}"/>
											
											<s:hidden id="mantieniAllegato" name="mantieniAllegato" value="true"></s:hidden>
										
											<p><br/>Esiste un allegato associato a questa versione della news:</p>
											<div class="clearfix">
												<s:url var="urlAllegato" value="/allegato!download.action?">
			    									<s:param name="id" value="%{news.allegato.id}"/>
												</s:url>
																							
													<label class="label">Allegato:</label>
														<s:if test="news.valida == 1">
															<s:a value="%{urlAllegato}">
																<img style="vertical-align:middle" src="<s:url value="/img/save.png" />" border="none"/>
																<span style="color:#4c4c4c">
																<em><s:property value="news.allegato.nomeFile"/></em>
															</s:a>
														</s:if>
														<s:else>
															<span style="color:#4c4c4c"><s:property value="news.allegato.nomeFile"/></span>
														</s:else>
											</div>
											<div class="clearfix">
											<label class="label">Titolo allegato:</label>
											<span style="color:#4c4c4c"><s:property value="news.allegato.titolo"/></span>
											</div>
											<br/>
									
													
											<s:if test="isModalitaModifica()">									
												<a class="storico" href="#" onclick="return cancellaAllegato();">Elimina allegato</a>
											</s:if>
										</div>
										
									</s:if>										
									<s:elseif test="!isModalitaCreazione()">
										<p>Nessun allegato &egrave; associato a questa versione della news.</p>
									</s:elseif>
									
									
														
									<s:if test="!isModalitaVisualizza()">																
										<div id="formCaricaAllegato" style="<s:property value="#styleBoxCaricaAllegato"/>">	
								
											<% /* L'iterator e` una predisposizione che avevamo fatto a un numero di allegati > 1 */ %>									
						                    <s:iterator value="numeroAllegati" status="i">
						                    	<div class="boxformup separatore-form"></div>
											    <s:file name="allegato_%{#i.index}" label="Allegato" size="40" />
						                        <s:textfield name="titolo_allegato_%{#i.index}" label="Titolo allegato " cssClass="input" maxlength="255" size="50" />
											</s:iterator>
		
										</div>
									</s:if>									

			                        <div class="clearfix">
				                        <p></p>
				                        <p></p>
			                        </div>
				
			                    </section>
			                 
			                </div>
						</s:form>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>


<div id="dialog_news_anteprima" class="dialog_news">
</div>
<div id="dialog" title="Informativa">
</div>

 <script type="text/javascript">
     function anteprimaNewsTesto() {
        		 
        		 
        $("#dialog_news_anteprima").attr("title",$("#form-news_news_titolo").val() );   <% //titolo per prima anteprima %>	    
	    $("span.ui-dialog-title").html($("#form-news_news_titolo").val());	<%	//titolo per anteprime successive alla prima %>
	    $("#dialog_news_anteprima").html($(".jqte_editor").first().html());		
		$("#dialog_news_anteprima").dialog({
			autoOpen: true,
			width: 600,
			close: function(event, ui) {
        		$(this).empty().dialog('destroy');
    		}
		});
		    
    }		  
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
         enableKeyNavigation:false,
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
			
			$("#servizio_altriUtenti_selected > option").each(function() {
			    this.setAttribute("selected","selected");
			});		
			
			$("#servizio_utenteEsterno_selected > option").each(function() {
			    this.setAttribute("selected","selected");
			});		
			
            $("#form-news").submit();
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
   	creaSottoWizard("altriUtenti");
   	
   	function eliminaBottoni() {       	
       	$("a[href*=#next]").remove( );	
       	$("a[href*=#prev]").remove( );	
  	}
  	eliminaBottoni();
        
    var paramsEditor = {
    					format: false,
    					fsize: false,
    					color:false,
    					right:false,
    					sub:false,
    					sup:false,
    					strike:false,
    					link:false,
    					unlink:false,
    					ol:false,
    					rule:false, 
    					source:false,
    					remove:false,
    					outdent:false,
    					indent:false
    					};
    $("#form-news_news_testo").jqte(paramsEditor);
        
	$(".jqte_toolbar").append('<div class="anteprima_button" onclick="javascript:anteprimaNewsTesto();">Anteprima</div>');
	$(".jqte_toolbar").append('<div class="tooltip_toolbar"><img src="img/ico_information.gif" id="tooltip_testo_news" title="<s:text name="tooltipNews"></s:text>"/></div>').tooltip();


	$(".jqte_editor").on('paste',function(e) {
		e.preventDefault();
	
		/* Non IE */
		if (e.clipboardData || e.originalEvent.clipboardData) {
			var text = (e.originalEvent || e).clipboardData.getData('text/plain') || prompt('Paste something..');
			window.document.execCommand('insertText', false, text);
		}
		else { /* IE */
		
			if (window.clipboardData) {
				var text = window.clipboardData.getData('Text');
				text = text.replace(/(\r\n|\n|\r)/g,"<br />"); //fix a capo su IE				
								
				var sel, range;
				if (window.getSelection) {
					//IE >= 9
				    sel = window.getSelection();
				    if (sel.getRangeAt && sel.rangeCount) {
				        range = sel.getRangeAt(0);
				        range.deleteContents();	
				        var el = document.createElement("div");
				        el.innerHTML = text;
				        var frag = document.createDocumentFragment(), node, lastNode;
				        while ( (node = el.firstChild) ) {
				            lastNode = frag.appendChild(node);
				        }
				        range.insertNode(frag);
				        
				        //mantiene la selezione corrente
				        if (lastNode) {
				            range = range.cloneRange();
				            range.setStartAfter(lastNode);
				            range.collapse(true);
				            sel.removeAllRanges();
				            sel.addRange(range);
				        }
				    }
				} else if (document.selection && document.selection.type != "Control") {
				    // IE < 9
				    document.selection.createRange().pasteHTML(text);
				}
								
			}
			/* Fine Workaround IE */
		}
	});
	
	

	<s:if test="isModalitaVisualizza()">	
		$(".jqte_editor").attr("contenteditable","false");
		$(".jqte_toolbar").hide();
	</s:if>

});
</script>


<% /*
// A volte il testo veniva troncato, sia nella textarea che anche stampandolo così. 
//Riavviando il server poi non l'ha più fatto (e sono comparsi anche questi messaggi di log:
//(TextProviderH W com.opensymphony.xwork2.util.logging.commons.CommonsLogger warn 
//The first TextProvider in the ValueStack (it.equitalia.gdo.web.actions.news.ModificaNewsAction)
 //could not locate the message resource with key 'news.testo')
 
 //Se il problema (del testo troncato) si ripresentasse, non so al momento come risolverlo
 //ma si tratta di un problema lato server e non legato all'editor JQTE (su database è salvato
 //correttamente ma viene caricato quando vado in modifica solo in parte).
  <div style="clear:both"><h1>VERIFICA TESTO CARICATO:</h1><s:text name="news.testo"></s:text></div>
*/
 %>
<jsp:include page="/jsp/tiles/menu.jsp"></jsp:include>