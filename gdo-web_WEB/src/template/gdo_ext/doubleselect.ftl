<div class="clearfix">
<#if parameters.label??>
    <p>${parameters.label?html}</p><#t/>
</#if>

<table class="multipleSelect" align="center">
<tbody>
	<tr>
		<td width="33%">
		<!-- PRIMA SELECT -->
		
<select multiple="multiple" size="10"
 name="${parameters.name?default("")?html}"<#rt/>

<#if parameters.id??>
 id="${parameters.id?html}"<#rt/>
</#if>
<#include "/${parameters.templateDir}/${parameters.expandTheme}/css.ftl" />
<#if parameters.title??>
 title="${parameters.title?html}"<#rt/>
</#if>

>
<#if parameters.headerKey?? && parameters.headerValue??>
    <option value="${parameters.headerKey?html}"
    <#if tag.contains(parameters.nameValue, parameters.headerKey) == true>
    selected="selected"
    </#if>
    >${parameters.headerValue?html}</option>
</#if>
<#if parameters.emptyOption?default(false)>
    <option value=""></option>
</#if>
<@s.iterator value="parameters.list">
        <#if parameters.listKey??>
            <#if stack.findValue(parameters.listKey)??>
              <#assign itemKey = stack.findValue(parameters.listKey)/>
              <#assign itemKeyStr = stack.findString(parameters.listKey)/>
            <#else>
              <#assign itemKey = ''/>
              <#assign itemKeyStr = ''/>
            </#if>
        <#else>
            <#assign itemKey = stack.findValue('top')/>
            <#assign itemKeyStr = stack.findString('top')>
        </#if>
        <#if parameters.listValue??>
            <#if stack.findString(parameters.listValue)??>
              <#assign itemValue = stack.findString(parameters.listValue)/>
            <#else>
              <#assign itemValue = ''/>
            </#if>
        <#else>
            <#assign itemValue = stack.findString('top')/>
        </#if>
        <#if parameters.listCssClass??>
            <#if stack.findString(parameters.listCssClass)??>
              <#assign itemCssClass= stack.findString(parameters.listCssClass)/>
            <#else>
              <#assign itemCssClass = ''/>
            </#if>
        </#if>
        <#if parameters.listCssStyle??>
            <#if stack.findString(parameters.listCssStyle)??>
              <#assign itemCssStyle= stack.findString(parameters.listCssStyle)/>
            <#else>
              <#assign itemCssStyle = ''/>
            </#if>
        </#if>
        <#if parameters.listTitle??>
            <#if stack.findString(parameters.listTitle)??>
              <#assign itemTitle= stack.findString(parameters.listTitle)/>
            <#else>
              <#assign itemTitle = ''/>
            </#if>
        </#if>
    <option value="${itemKeyStr?html}"<#rt/>
        <#if tag.contains(parameters.nameValue, itemKey) == true>
 selected="selected"<#rt/>
        </#if>
        <#if itemCssClass?if_exists != "">
 class="${itemCssClass?html}"<#rt/>
        </#if>
        <#if itemCssStyle?if_exists != "">
 style="${itemCssStyle?html}"<#rt/>
        </#if>
        <#if itemTitle?if_exists != "">
 title="${itemTitle?html}"<#rt/>
        </#if>
    >${itemValue?html}</option><#lt/>
</@s.iterator>



</select>
</td>
<td  width="33%" align="center">
	<input type="button" name="addAssociations" value="&gt;" id="addAssociations_${parameters.id?html}" class="submit" title="Aggiungi" alt="Aggiungi">
	<br>
	<input type="button" name="addAllAssociations" value="&gt;&gt;" id="addAllAssociations_${parameters.id?html}" class="submit" title="Aggiungi Tutto" alt="Aggiungi Tutto">
	<br>
	<input type="button" name="removeAssociations" value="&lt;" id="removeAssociations_${parameters.id?html}" class="submit" title="Rimuovi" alt="Rimuovi">
	<br>
	<input type="button" name="removeAllAssociations" value="&lt;&lt;" id="removeAllAssociations_${parameters.id?html}" class="submit" title="Rimuovi Tutto" alt="Rimuovi Tutto">
</td>
<td  width="33%">
				

									
<select multiple="multiple" size="10"<#rt/>
        name="${parameters.doubleName?default("")?html}"<#rt/>
<#if parameters.disabled?default(false)>
        disabled="disabled"<#rt/>
</#if>
<#if parameters.doubleTabindex??>
        tabindex="${parameters.doubleTabindex?html}"<#rt/>
</#if>
<#if parameters.doubleId??>
        id="${parameters.doubleId?html}"<#rt/>
</#if>
<#if parameters.doubleCss??>
        class="${parameters.doubleCss?html}"<#rt/>
</#if>
<#if parameters.doubleStyle??>
        style="${parameters.doubleStyle?html}"<#rt/>
</#if>
<#if parameters.title??>
        title="${parameters.title?html}"<#rt/>
</#if>
<#if parameters.multiple?default(false)>
        multiple="multiple"<#rt/>
</#if>
<#if parameters.get("doubleSize")??>
        size="${parameters.get("doubleSize")?html}"<#rt/>
</#if>
<#if parameters.doubleMultiple?default(false)>
        multiple="multiple"<#rt/>
</#if>
<#if parameters.doubleDisabled?default(false)>
        disabled="disabled"<#rt/>
</#if>
        >

<@s.iterator value="${parameters.doubleList}">
        <#if parameters.listKey??>
            <#if stack.findValue(parameters.listKey)??>
              <#assign itemKey = stack.findValue(parameters.listKey)/>
              <#assign itemKeyStr = stack.findString(parameters.listKey)/>
            <#else>
              <#assign itemKey = ''/>
              <#assign itemKeyStr = ''/>
            </#if>
        <#else>
            <#assign itemKey = stack.findValue('top')/>
            <#assign itemKeyStr = stack.findString('top')>
        </#if>
        <#if parameters.listValue??>
            <#if stack.findString(parameters.listValue)??>
              <#assign itemValue = stack.findString(parameters.listValue)/>
            <#else>
              <#assign itemValue = ''/>
            </#if>
        <#else>
            <#assign itemValue = stack.findString('top')/>
        </#if>
        <#if parameters.listCssClass??>
            <#if stack.findString(parameters.listCssClass)??>
              <#assign itemCssClass= stack.findString(parameters.listCssClass)/>
            <#else>
              <#assign itemCssClass = ''/>
            </#if>
        </#if>
        <#if parameters.listCssStyle??>
            <#if stack.findString(parameters.listCssStyle)??>
              <#assign itemCssStyle= stack.findString(parameters.listCssStyle)/>
            <#else>
              <#assign itemCssStyle = ''/>
            </#if>
        </#if>
        <#if parameters.listTitle??>
            <#if stack.findString(parameters.listTitle)??>
              <#assign itemTitle= stack.findString(parameters.listTitle)/>
            <#else>
              <#assign itemTitle = ''/>
            </#if>
        </#if>
    <option value="${itemKeyStr?html}"<#rt/>
        <#if tag.contains(parameters.nameValue, itemKey) == true>
 selected="selected"<#rt/>
        </#if>
        <#if itemCssClass?if_exists != "">
 class="${itemCssClass?html}"<#rt/>
        </#if>
        <#if itemCssStyle?if_exists != "">
 style="${itemCssStyle?html}"<#rt/>
        </#if>
        <#if itemTitle?if_exists != "">
 title="${itemTitle?html}"<#rt/>
        </#if>
    >${itemValue?html}</option><#lt/>
</@s.iterator>        
        
</select>
								
		</td>
	</tr>
</tbody></table>
</div>

<#if !parameters.disabled?default(false)>
      
      
	<script type="text/javascript">
	
	 $(function () {
	  	
	 	<#-- Salvo su un array i valori preselezionati (lista a destra) --> 	
	 	var valoriPreselezionati = [];
	 	 $("#${parameters.doubleId?html} option").each( function()	{
	 	 	valoriPreselezionati.push($(this).val());
	 	 }); 	 
	 	 <#-- Tolgo dalla lista di sinistra i valori preselezionati -->
	 	 $("#${parameters.id?html} option").each( function()	{ 	 
	 	 	if ($.inArray($(this).val(), valoriPreselezionati) != -1)
	 	 		$(this).remove();
	 	 });
	 	 
	 	<#-- Evento click aggiungi singolo elemento -->
		 $("#addAssociations_${parameters.id?html}").unbind('click').click( function() { 
		 
			 $("#${parameters.id?html} option:selected").each( function()	{		 
				
				if ( $(this).val() != null && $("#${parameters.doubleId?html} option[value='" + $(this).val() + "']").length == 0 )
			 	{
			 		$("#${parameters.doubleId?html}").append( '<option value="' + $(this).val() + '">' + $(this).text() + '</option>');
			 		$(this).remove(); 
			 	}	
		  	});
	 	});
	 
	 	<#-- Evento click aggiungi tutti gli elementi-->
	 	 $("#addAllAssociations_${parameters.id?html}").unbind('click').click( function() { 
		 
			   $("#${parameters.id?html} option").each( function()	
			   {
				 	$("#${parameters.doubleId?html}").append( '<option value="' + $(this).val() + '">' + $(this).text() + '</option>');		 
					$(this).remove();	
		  	});
	 	});
	 	
	 	<#-- Evento click rimuovi un elemento -->
	 	 $("#removeAssociations_${parameters.id?html}").unbind('click').click( function() { 
		 
			$("#${parameters.doubleId?html} option:selected").each( function()	{
			 	$("#${parameters.id?html}").append( '<option value="' + $(this).val() + '">' + $(this).text() + '</option>');
			 	ordinaAlfabeticamenteSelect("${parameters.id?html}");		 
				$(this).remove();	
		  	});
	 	});
	 	
	 	<#-- Evento click rimuovi tutti gli elementi -->
	 	 $("#removeAllAssociations_${parameters.id?html}").unbind('click').click( function() { 
		 
			  $("#${parameters.doubleId?html} option").each( function()	{		 
			 	$("#${parameters.id?html}").append( '<option value="' + $(this).val() + '">' + $(this).text() + '</option>');
			 	ordinaAlfabeticamenteSelect("${parameters.id?html}");	 
				$(this).remove();	
		  	});
			 
	 	});
	 	
	 });
	 
	 
	</script>
</#if>