<%@ page import="it.equitalia.gdo.web.security.SecurityHelper" %>

<div id="menu">
	<div class="inner">
	
	<% /* Usato codice java perche' i tag davano problemi di lettura sessione al primo caricamento della pagina */	
	if (SecurityHelper.isAbilitatoGestioneNewsMenu(request)) { %>
			<div class="gruppo">
				<div class="tabelleriforma">Gestione news</div>
				<ul>
					<li><a title="Nuova news" href="news!nuova.action">Inserimento nuova news </a></li>
					<li><a title="Ricerca news" href="news!ricerca.action"> Ricerca news </a></li>
				</ul>
			</div>
			<br>
			<br>
		
	<% 
		}
		 if (SecurityHelper.isAbilitatoGestioneSezioniMenu(request)) { %>
			<div class="gruppo">
				<div class="tabelleriforma">Gestione sezioni</div>
				<ul>
					<li><a title="Nuova sezione" href="sezione!nuova.action">Inserimento nuova sezione </a></li>
					<li><a title="Ricerca sezione" href="sezione!ricerca.action"> Ricerca sezione </a></li>
					<li><a title="Ricerca documento" href="documento!ricerca.action"> Ricerca documento</a></li>
				</ul>
			</div>
			<br>
			<br>
		<% } %>
		
	</div>
	
	<div class="gruppo">
		<div class="tabelleriforma">Documentazione</div>
		<ul>
			<li><a title="Scarica" href="download!manuale.action">Manuale Utente</a></li>
		</ul>
	</div>
	
</div>
<!-- /menusezione -->


