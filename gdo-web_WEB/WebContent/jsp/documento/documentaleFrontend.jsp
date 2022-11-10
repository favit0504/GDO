<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<div id="content">
	<div id="innerContent1">
		 <div class="inner">
			<!-- <h3 class="pagetitle"><s:property value="titoloDocumentale" /></h3> -->			
			
			<div id="main">	
				<div class="mainpart">	
				<div id="clsx">						
					<s:iterator value="sezioniSx" status="sez" var="sezione">
					<!--  <div id="sezione" style="width:40%; float:left; margin-right:20px">-->
						<div class="title"><h3><span> <s:property value="titolo" /> </span></h3></div>
							<s:iterator value="#sezione.documenti" status="doc" var="documento">
								<ul class="titoloDocumenti">	
									<!--<img src="img/icon_lente.gif" alt="Mostra documento" />-->
									<s:url var="urlDocumento" value="visualizza-documento!frontend.action?">
			    						<s:param name="idDocumento" value="id"/>
						  			</s:url>
									<s:a cssClass="linkUnderline" value="%{urlDocumento}"><s:property value="titolo" />
						  				
									</s:a>
								</ul>
							</s:iterator>							
							
							<!--  </div>-->
					</s:iterator>
				</div>	
				<div id="cldx">						
					<s:iterator value="sezioniDx" status="sez" var="sezione">
						<div class="title"><h3><span> <s:property value="titolo" /> </span></h3></div>
							<s:iterator value="#sezione.documenti" status="doc" var="documento">
								<ul class="titoloDocumenti">	
									<!--<img src="img/icon_lente.gif" alt="Mostra documento" />-->
									<s:url var="urlDocumento" value="visualizza-documento!frontend.action?">
			    						<s:param name="idDocumento" value="id"/>
						  			</s:url>
									<s:a cssClass="linkUnderline" value="%{urlDocumento}"><s:property value="titolo" />
						  				
									</s:a>
								</ul>
							</s:iterator>
					</s:iterator>
				</div>														
				
				</div>			
				
			</div>
		</div>
	
	</div>

</div>