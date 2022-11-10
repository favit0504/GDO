<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<div id="content">
		<div id="innerContent">
			<div class="inner">
				<h3 class="pagetitle">Ricerca Sezione</h3>
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
        					<s:fielderror escape="false" fieldName="descrizione" />
        					<s:fielderror escape="false" fieldName="stato" />
        					
        					<s:form action="sezione!ricerca.action" theme="gdo_ext" id="ricerca-sezione" name="ricercasezione" enctype="multipart/form-data" method="POST">
        						<div id="wizard-n">    						        						
				                    <section>
									<div class="content clearfix">										
				
				                        <s:textfield name="sezione.titolo" label="Titolo della sezione" cssClass="input" size="50" />
				                        
				                        <s:textarea name="sezione.descrizione" label="Descrizione della sezione" cols="20" rows="3" wrap="true"></s:textarea>
				                        
				                     	<s:checkboxlist value="defaultValue" name="statoSezioneCheck" cssClass="radio-form" label="Stato" list="opzioniStato" ></s:checkboxlist> </br>
				    
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

