
<#include "/${parameters.templateDir}/${parameters.expandTheme}/primatag.ftl" />

<input type="file"<#rt/>
 name="${parameters.name?default("")?html}"<#rt/>
<#if parameters.get("size")??>
 size="${parameters.get("size")?html}"<#rt/>
</#if>
 value=""<#rt/>
<#if parameters.disabled?default(false)>
 disabled="disabled"<#rt/>
</#if>
<#if parameters.accept??>
 accept="${parameters.accept?html}"<#rt/>
</#if>
<#if parameters.tabindex??>
 tabindex="${parameters.tabindex?html}"<#rt/>
</#if>
<#if parameters.id??>
 id="${parameters.id?html}"<#rt/>
</#if>
<#include "/${parameters.templateDir}/${parameters.expandTheme}/css.ftl" />
<#if parameters.title??>
 title="${parameters.title?html}"<#rt/>
</#if>
<#include "/${parameters.templateDir}/${parameters.expandTheme}/scripting-events.ftl" />
<#include "/${parameters.templateDir}/${parameters.expandTheme}/common-attributes.ftl" />
<#include "/${parameters.templateDir}/${parameters.expandTheme}/dynamic-attributes.ftl" />
/>

<#include "/${parameters.templateDir}/${parameters.expandTheme}/dopotag.ftl" />
