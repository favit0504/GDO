<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="hasActionMessages()">
	<div class="messaggistica">
		<ul>
			<s:iterator value="actionMessages">
				<li class="informazione">
					<s:property escape="false" />
				</li>
			</s:iterator>
		</ul>
	</div>
</s:if>		

<s:if test="hasActionErrors()">
	<div class="messaggistica">
		<ul>
			<s:iterator value="actionErrors">
				<li class="errore">
					<s:property escape="false" />
				</li>
			</s:iterator>
		</ul>
	</div>
</s:if>		