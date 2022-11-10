<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<div id="content">
		<div id="innerContent">
			<div class="inner">
				<h3 class="pagetitle">Ricerca News</h3>
				<div style="visibility:hidden" id="message">
							<p align="center">Caricamento in corso...</p>
						</div>
						<div id="errorMessage">
						</div>
						
					<div id="main">		
						<!-- TODO: questo va fatto con tiles? Mi da problema -->
						<jsp:include page="../tiles/messages.jsp"></jsp:include> 		
						
						<div class="boxformup"></div>
						<div class="boxform">
							<div class="boxformricerca">
							 
							<s:fielderror escape="false" fieldName="errori-form" />
        					<s:fielderror escape="false" fieldName="titolo" />
        					<s:fielderror escape="false" fieldName="testo" />
        					<s:fielderror escape="false" fieldName="owner" />
        					<s:fielderror escape="false" fieldName="stato" />
        					<s:fielderror escape="false" fieldName="dataInizioPubblicazione" />
        					<s:fielderror escape="false" fieldName="dataFinePubblicazione" />
        					
        					<s:form action="news!ricerca.action" theme="gdo_ext" id="ricerca-news" name="ricercanews"  enctype="multipart/form-data" method="POST">
        						<div id="wizard-n">    						        						
				                    <section>
									<div class="content clearfix">										
				
				                        <s:textfield name="news.titolo" label="Titolo della news" cssClass="input" size="50" />
				                        
				                        <s:textarea name="news.testo" label="Testo della news" cols="20" rows="3" wrap="true"></s:textarea>
				                        
				                        <s:textfield name="news.owner" label="Autore della news" cssClass="input" size="50" />
				                        
				                     	<s:checkboxlist value="defaultValue" name="statoNewsCheck" cssClass="radio-form" label="Stato" list="opzioniStato"></s:checkboxlist> </br>
										
										<div class="clearfix">
								            <label class="label">Data Inizio Pubblicazione:</label>
								            <input type="text" id="dataInizioPubblicazione" name="news.dataInizioPubblicazione" maxlength="10" size="10" value='<s:property value="news.dataInizioPubblicazione" />' />                
								            <input id="buttonCalendario" type="button" onclick="return showCalendar('dataInizioPubblicazione', '%d/%m/%Y',this);" class="calendario" />&nbsp;<s:label> gg/mm/aaaa </s:label></br>
										</div>
								
										<div class="clearfix">
								            <label class="label">Data Fine Pubblicazione:</label>
								            <input type="text" id="dataFinePubblicazione" name="news.dataFinePubblicazione" maxlength="10" size="10" value='<s:property value="news.dataFinePubblicazione" />' />                
								            <input id="buttonCalendario" type="button" onclick="return showCalendar('dataFinePubblicazione', '%d/%m/%Y',this);" class="calendario" />&nbsp;<s:label> gg/mm/aaaa </s:label>
										</div>
				    
				                       <s:checkboxlist name="tipologiaUtenteCheck" cssClass="radio-form" label="Destinatari della news" list="opzioniTipologiaUtente"></s:checkboxlist>
				                       
				                       <br/>
				                       <div class="clearfix">
							            <label class="label">News visualizzata come pop up:</label>
							            <s:checkbox id="checkPopUp" name="news.visualizzaPopUp" cssClass="radio-form" fieldValue="true" onclick="javascript:mostraWarningVisualizzaPopUp();" disabled="%{isModalitaVisualizza()}"></s:checkbox>							            							            										
			    						</div>
				                        
				                        <div class="clearfix">
				                        <p></p>
				                        <p></p>
				                        </div>
										</div>
										<div class="action clearfix">										
					                	<input type="submit" class="submit" value="Ricerca"/>
                						 </div>           						     
					               </section>
				            	</div> <!-- wizard-n -->
        					</s:form>							
							</div> <!-- boxformricerca -->
						</div> <!-- boxform -->
						
							
				
	
					</div>	<!-- #main -->
			</div> <!-- inner -->
				
		</div> <!-- innerContent -->
	
	</div><!-- content -->


<jsp:include page="/jsp/tiles/menu.jsp"></jsp:include>

