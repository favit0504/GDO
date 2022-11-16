
<input type="hidden" name="conta_user" value="<%= (String)request.getSession().getAttribute("userId") %>" />
	<div id="log">
		<h2 class="acc">Area riservata</h2>
	<!-- 	<ul>
			<li id="logout"><a href="../../pkmslogout">Logout</a></li>
			<li id="utente"><a href="#">Utente: <%=session.getAttribute("userId")%></a></li>
			<li id="cambio_pwd"><b style="color: #0059ab;">|&nbsp;&nbsp;&nbsp;&nbsp;</b><a href="../../pkmspasswd">Cambio Password</a></li>	
		</ul>
	 -->
	</div>
	
	<!-- 
	<div id="header">
		<a id="logo" href="javascript:navbar('EQS.EQSHOME');">Torna in Home Page</a>
		
	</div>
	 -->
<script type="text/javascript">
function toHelp() {
  	window.open("help.html", "", "toolbar=no,status=no,width=650,height=500,menubar=no,scrollbars=yes,resizable=yes");
}
</script>