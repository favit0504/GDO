<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="content">
	<div id="innerContent">
		<div class="inner">
			<h3 class="pagetitle"><s:property value="titoloPagina" /></h3>
			<div id="main">
			
			
				<div class="boxformup"></div>
				<div class="boxform">
					<div class="boxformricerca">
							
						<s:fielderror fieldName="errori-form" />        				   
        				 <p> ${news.testo} </p>
        				  <s:if test="%{news.allegato!=null}">
	        				  <div class="clearfix">
							  <s:url var="urlAllegato" value="/download!frontend.action?">
				    				<s:param name="id" value="%{news.id}"/>
							  </s:url>
							<label class="label">Allegato:</label>
								<s:a value="%{urlAllegato}">
									<img style="vertical-align:middle" src="<s:url value="/img/save.png" />" border="none"/>
									<span style="color:#4c4c4c">
									<em><s:property value="news.allegato.nomeFile"/></em>
								</s:a>
        				  </s:if>
						
					
																														
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>