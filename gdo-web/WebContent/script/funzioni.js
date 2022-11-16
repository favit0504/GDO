
function navbar(navservice) {
	highline(navservice);
	document.forms[0].service.value = "NAVBAR";
	document.forms[0].submit();
}

function highline(navservice) {
	pattern = "(" + navservice + "\/)";
	reHighline = new RegExp(pattern);
	reClear = /#/;
	document.forms[0].history.value = document.forms[0].history.value.replace(reClear, "");
	document.forms[0].history.value = document.forms[0].history.value.replace(reHighline, "#$1");
}

// Funzione per evitare il doppio click verso una action
var isWaiting = false;
function callAction(action) {
	if (isWaiting) {
		return;
	}
	isWaiting = true;
	document.location  = action;
}


/* Fine funzioni navigazione, inizio funzioni di utilita` */
function ordinaAlfabeticamenteSelect(idSelect) {
	
	var options = $('#' + idSelect + ' option');
	var arr = options.map(function(_, o) { return { t: $(o).text(), v: o.value }; }).get();
	arr.sort(function(o1, o2) { return o1.t > o2.t ? 1 : o1.t < o2.t ? -1 : 0; });
	options.each(function(i, o) {
	  o.value = arr[i].v;
	  $(o).text(arr[i].t);
	});
}

function cancellaAllegato() {
	
	$("#mantieniAllegato").val("false");
	$("#formCaricaAllegato").css("display", "block");
	$("#bloccoAllegatoCaricato").css("display", "none");
	return false;
	
}

function cancellaDocumento() {
	
	$("#mantieniDocumento").val("false");
	$("#formCaricaAllegato").css("display", "block");
	$("#bloccoAllegatoCaricato").css("display", "none");
	return false;
	
}