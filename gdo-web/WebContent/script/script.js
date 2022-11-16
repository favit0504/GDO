var oldLink = null;
function setActiveStyleSheet(link, title) {
  var i, a, main;
  for(i=0; (a = document.getElementsByTagName("link")[i]); i++) {
    if(a.getAttribute("rel").indexOf("style") != -1 && a.getAttribute("title")) {
      a.disabled = true;
      if(a.getAttribute("title") == title) a.disabled = false;
    }
  }
  if (oldLink) oldLink.style.fontWeight = 'normal';
  oldLink = link;
  link.style.fontWeight = 'bold';
  return false;
}

// This function gets called when the end-user clicks on some date.
function selected(cal, date) {
  cal.sel.value = date; // just update the date in the input field.
  if (cal.dateClicked && (cal.sel.id == "sel1" || cal.sel.id == "sel3"))
    cal.callCloseHandler();
}

function closeHandler(cal) {
  cal.hide();                        // hide the calendar
//  cal.destroy();

  // _dynarch_popupCalendar = null;
}

function showCalendar(id, format, show, showsTime, showsOtherMonths) {
	
	if ($("#modalita_sola_lettura").val() == "true")
		return false;
	
  var el = document.getElementById(id);
  if (_dynarch_popupCalendar != null) {
    // we already have some calendar created
    _dynarch_popupCalendar.hide();                 // so we hide it first.
  } else {
    // first-time call, create the calendar.
    var cal = new Calendar(1, null, selected, closeHandler);
    // uncomment the following line to hide the week numbers
    // cal.weekNumbers = false;
    if (typeof showsTime == "string") {
      cal.showsTime = true;
      cal.time24 = (showsTime == "24");
    }
    if (showsOtherMonths) {
      cal.showsOtherMonths = true;
    }
    _dynarch_popupCalendar = cal;                  // remember it in the global var
    cal.setRange(1900, 2070);        // min/max year allowed.
    cal.create();
  }
  _dynarch_popupCalendar.setDateFormat(format);    // set the specified date format
  _dynarch_popupCalendar.parseDate(el.value);      // try to parse the text in field
  _dynarch_popupCalendar.sel = el;                 // inform it what input field we use

  _dynarch_popupCalendar.showAtElement(show, "Br");        // show the calendar

  return false;
}

var MINUTE = 60 * 1000;
var HOUR = 60 * MINUTE;
var DAY = 24 * HOUR;
var WEEK = 7 * DAY;

function isDisabled(date) {
  var today = new Date();
  return (Math.abs(date.getTime() - today.getTime()) / DAY) > 10;
}

function flatSelected(cal, date) {
  var el = document.getElementById("preview");
  el.innerHTML = date;
}

function showFlatCalendar() {
	  var parent = document.getElementById("display");

	  var cal = new Calendar(0, null, flatSelected);

	  cal.weekNumbers = false;

	  cal.setDisabledHandler(isDisabled);
	  cal.setDateFormat("%A, %B %e");

	  cal.create(parent);

	  cal.show();
	}

function fullscreen(a){
	var url = 'visualizzaSchermoIntero?fullscreen='+a + "&dummy=" + new Date().getTime();
	var ajp = new AjaxPages;
	var responseText =  ajp.load (url);
	if(responseText==""){
		location.reload(true);
	}
}

function onlyNumbers(e)
{
	var keynum;
	var keychar;
	var numcheck;
	
	if(window.event) // IE8 and earlier
	{
		keynum = e.keyCode;
	}
	else if(e.which) // IE9/Firefox/Chrome/Opera/Safari
	{
		keynum = e.which;
	}

	switch( keynum )
	{
		case 8:
			return true;
	}

	keychar = String.fromCharCode(keynum);
	numcheck = /\d/;
	return numcheck.test(keychar);
}

function disabilita(elem){
	$(elem).clear();
	$(elem).disable();
}