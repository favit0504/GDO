<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

 <div id="content">
	<div id="innerContent1">
		<div class="inner">
			<h3 class="pagetitle"><s:property value="documento.titolo" /></h3>
			<div id="main">
			
			
				<div class="boxformup"></div>
				<div class="boxform">
					<div class="boxformricerca">
							
						<s:fielderror fieldName="errori-form" />        				   
        				 <p> ${documento.descrizione} </p>
        				  <s:if test="%{documento.blob!=null}">
	        				  <div class="clearfix"></div>
							  <s:url var="urlFile" value="download-documento!frontend.action">
				    				<s:param name="idDocumento" value="%{documento.id}"/>
							  </s:url>
							<label class="label">File:</label>
								<s:a value="%{urlFile}">
									<img style="vertical-align:middle" src="<s:url value="/img/save.png" />" border="none"/>
									<span style="color:#4c4c4c"></span>
									<em><s:property value="documento.nomeFile"/></em>
								</s:a>
        				  </s:if>
        				  
																														
					</div>
					<td>
						<input type="button" class="submit" onclick="history.go(-1);" title="Indietro" value="Indietro" alt="Indietro">
					</td>
				</div>
				
				
			</div>
		</div>
	</div>
</div>  