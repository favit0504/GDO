
package it.equitalia.gdo.webservices.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per newsBean complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="newsBean">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.gdo.equitalia.it/}beanVersionabile">
 *       &lt;sequence>
 *         &lt;element name="agente" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="allegato" type="{http://webservices.gdo.equitalia.it/}allegatoBean" minOccurs="0"/>
 *         &lt;element name="altriUtenti" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="utenteEsterno" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="dataFinePubblicazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataInizioPubblicazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneStato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneTipoUtente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ente" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="filtroAmbito" type="{http://webservices.gdo.equitalia.it/}filtroAmbitoBean" minOccurs="0"/>
 *         &lt;element name="filtroEnte" type="{http://webservices.gdo.equitalia.it/}filtroEnteBean" minOccurs="0"/>
 *         &lt;element name="filtroProvinciaEnte" type="{http://webservices.gdo.equitalia.it/}filtroProvinciaEnteBean" minOccurs="0"/>
 *         &lt;element name="filtroRegioneAgente" type="{http://webservices.gdo.equitalia.it/}filtroRegioneAgenteBean" minOccurs="0"/>
 *         &lt;element name="filtroRegioneEnte" type="{http://webservices.gdo.equitalia.it/}filtroRegioneEnteBean" minOccurs="0"/>
 *         &lt;element name="filtroServizioAgente" type="{http://webservices.gdo.equitalia.it/}filtroServizioAgenteBean" minOccurs="0"/>
 *         &lt;element name="filtroServizioAltriUtenti" type="{http://webservices.gdo.equitalia.it/}filtroServizioAltriUtentiBean" minOccurs="0"/>
 *         &lt;element name="filtroServizioUtenteEsterno" type="{http://webservices.gdo.equitalia.it/}filtroServizioUtenteEsternoBean" minOccurs="0"/>
 *         &lt;element name="filtroServizioEnte" type="{http://webservices.gdo.equitalia.it/}filtroServizioEnteBean" minOccurs="0"/>
 *         &lt;element name="filtroSocieta" type="{http://webservices.gdo.equitalia.it/}filtroSocietaBean" minOccurs="0"/>
 *         &lt;element name="filtroTipologiaEnte" type="{http://webservices.gdo.equitalia.it/}filtroTipologiaEnteBean" minOccurs="0"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="svecchiata" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="testo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="visualizzaPopUp" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="visualizzaPopUpDescr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "newsBean", namespace = "http://webservices.gdo.equitalia.it/", propOrder = {
    "agente",
    "allegato",
    "altriUtenti",
    "utenteEsterno",
    "dataFinePubblicazione",
    "dataInizioPubblicazione",
    "descrizioneStato",
    "descrizioneTipoUtente",
    "ente",
    "filtroAmbito",
    "filtroEnte",
    "filtroProvinciaEnte",
    "filtroRegioneAgente",
    "filtroRegioneEnte",
    "filtroServizioAgente",
    "filtroServizioAltriUtenti",
    "filtroServizioUtenteEsterno",
    "filtroServizioEnte",
    "filtroSocieta",
    "filtroTipologiaEnte",
    "stato",
    "svecchiata",
    "testo",
    "titolo",
    "visualizzaPopUp",
    "visualizzaPopUpDescr"
})
public class NewsBean
    extends BeanVersionabile
{

    protected Boolean agente;
    protected AllegatoBean allegato;
    protected Boolean altriUtenti;
    protected Boolean utenteEsterno;
    protected String dataFinePubblicazione;
    protected String dataInizioPubblicazione;
    protected String descrizioneStato;
    protected String descrizioneTipoUtente;
    protected Boolean ente;
    protected FiltroAmbitoBean filtroAmbito;
    protected FiltroEnteBean filtroEnte;
    protected FiltroProvinciaEnteBean filtroProvinciaEnte;
    protected FiltroRegioneAgenteBean filtroRegioneAgente;
    protected FiltroRegioneEnteBean filtroRegioneEnte;
    protected FiltroServizioAgenteBean filtroServizioAgente;
    protected FiltroServizioAltriUtentiBean filtroServizioAltriUtenti;
    protected FiltroServizioUtenteEsternoBean filtroServizioUtenteEsterno;
    protected FiltroServizioEnteBean filtroServizioEnte;
    protected FiltroSocietaBean filtroSocieta;
    protected FiltroTipologiaEnteBean filtroTipologiaEnte;
    protected Integer stato;
    protected Boolean svecchiata;
    protected String testo;
    protected String titolo;
    protected Boolean visualizzaPopUp;
    protected String visualizzaPopUpDescr;

    /**
     * Recupera il valore della propriet?? agente.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAgente() {
        return agente;
    }

    /**
     * Imposta il valore della propriet?? agente.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAgente(Boolean value) {
        this.agente = value;
    }

    /**
     * Recupera il valore della propriet?? allegato.
     * 
     * @return
     *     possible object is
     *     {@link AllegatoBean }
     *     
     */
    public AllegatoBean getAllegato() {
        return allegato;
    }

    /**
     * Imposta il valore della propriet?? allegato.
     * 
     * @param value
     *     allowed object is
     *     {@link AllegatoBean }
     *     
     */
    public void setAllegato(AllegatoBean value) {
        this.allegato = value;
    }

    /**
     * Recupera il valore della propriet?? altriUtenti.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAltriUtenti() {
        return altriUtenti;
    }

    /**
     * Imposta il valore della propriet?? altriUtenti.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAltriUtenti(Boolean value) {
        this.altriUtenti = value;
    }

    /**
     * Recupera il valore della propriet?? utenteEsterno.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUtenteEsterno() {
        return utenteEsterno;
    }

    /**
     * Imposta il valore della propriet?? utenteEsterno.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUtenteEsterno(Boolean value) {
        this.utenteEsterno = value;
    }

    /**
     * Recupera il valore della propriet?? dataFinePubblicazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataFinePubblicazione() {
        return dataFinePubblicazione;
    }

    /**
     * Imposta il valore della propriet?? dataFinePubblicazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFinePubblicazione(String value) {
        this.dataFinePubblicazione = value;
    }

    /**
     * Recupera il valore della propriet?? dataInizioPubblicazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataInizioPubblicazione() {
        return dataInizioPubblicazione;
    }

    /**
     * Imposta il valore della propriet?? dataInizioPubblicazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataInizioPubblicazione(String value) {
        this.dataInizioPubblicazione = value;
    }

    /**
     * Recupera il valore della propriet?? descrizioneStato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneStato() {
        return descrizioneStato;
    }

    /**
     * Imposta il valore della propriet?? descrizioneStato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneStato(String value) {
        this.descrizioneStato = value;
    }

    /**
     * Recupera il valore della propriet?? descrizioneTipoUtente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneTipoUtente() {
        return descrizioneTipoUtente;
    }

    /**
     * Imposta il valore della propriet?? descrizioneTipoUtente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneTipoUtente(String value) {
        this.descrizioneTipoUtente = value;
    }

    /**
     * Recupera il valore della propriet?? ente.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnte() {
        return ente;
    }

    /**
     * Imposta il valore della propriet?? ente.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnte(Boolean value) {
        this.ente = value;
    }

    /**
     * Recupera il valore della propriet?? filtroAmbito.
     * 
     * @return
     *     possible object is
     *     {@link FiltroAmbitoBean }
     *     
     */
    public FiltroAmbitoBean getFiltroAmbito() {
        return filtroAmbito;
    }

    /**
     * Imposta il valore della propriet?? filtroAmbito.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroAmbitoBean }
     *     
     */
    public void setFiltroAmbito(FiltroAmbitoBean value) {
        this.filtroAmbito = value;
    }

    /**
     * Recupera il valore della propriet?? filtroEnte.
     * 
     * @return
     *     possible object is
     *     {@link FiltroEnteBean }
     *     
     */
    public FiltroEnteBean getFiltroEnte() {
        return filtroEnte;
    }

    /**
     * Imposta il valore della propriet?? filtroEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroEnteBean }
     *     
     */
    public void setFiltroEnte(FiltroEnteBean value) {
        this.filtroEnte = value;
    }

    /**
     * Recupera il valore della propriet?? filtroProvinciaEnte.
     * 
     * @return
     *     possible object is
     *     {@link FiltroProvinciaEnteBean }
     *     
     */
    public FiltroProvinciaEnteBean getFiltroProvinciaEnte() {
        return filtroProvinciaEnte;
    }

    /**
     * Imposta il valore della propriet?? filtroProvinciaEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroProvinciaEnteBean }
     *     
     */
    public void setFiltroProvinciaEnte(FiltroProvinciaEnteBean value) {
        this.filtroProvinciaEnte = value;
    }

    /**
     * Recupera il valore della propriet?? filtroRegioneAgente.
     * 
     * @return
     *     possible object is
     *     {@link FiltroRegioneAgenteBean }
     *     
     */
    public FiltroRegioneAgenteBean getFiltroRegioneAgente() {
        return filtroRegioneAgente;
    }

    /**
     * Imposta il valore della propriet?? filtroRegioneAgente.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroRegioneAgenteBean }
     *     
     */
    public void setFiltroRegioneAgente(FiltroRegioneAgenteBean value) {
        this.filtroRegioneAgente = value;
    }

    /**
     * Recupera il valore della propriet?? filtroRegioneEnte.
     * 
     * @return
     *     possible object is
     *     {@link FiltroRegioneEnteBean }
     *     
     */
    public FiltroRegioneEnteBean getFiltroRegioneEnte() {
        return filtroRegioneEnte;
    }

    /**
     * Imposta il valore della propriet?? filtroRegioneEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroRegioneEnteBean }
     *     
     */
    public void setFiltroRegioneEnte(FiltroRegioneEnteBean value) {
        this.filtroRegioneEnte = value;
    }

    /**
     * Recupera il valore della propriet?? filtroServizioAgente.
     * 
     * @return
     *     possible object is
     *     {@link FiltroServizioAgenteBean }
     *     
     */
    public FiltroServizioAgenteBean getFiltroServizioAgente() {
        return filtroServizioAgente;
    }

    /**
     * Imposta il valore della propriet?? filtroServizioAgente.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroServizioAgenteBean }
     *     
     */
    public void setFiltroServizioAgente(FiltroServizioAgenteBean value) {
        this.filtroServizioAgente = value;
    }

    /**
     * Recupera il valore della propriet?? filtroServizioAltriUtenti.
     * 
     * @return
     *     possible object is
     *     {@link FiltroServizioAltriUtentiBean }
     *     
     */
    public FiltroServizioAltriUtentiBean getFiltroServizioAltriUtenti() {
        return filtroServizioAltriUtenti;
    }

    /**
     * Imposta il valore della propriet?? filtroServizioAltriUtenti.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroServizioAltriUtentiBean }
     *     
     */
    public void setFiltroServizioAltriUtenti(FiltroServizioAltriUtentiBean value) {
        this.filtroServizioAltriUtenti = value;
    }

    /**
     * Recupera il valore della propriet?? filtroServizioUtenteEsterno.
     * 
     * @return
     *     possible object is
     *     {@link FiltroServizioUtenteEsternoBean }
     *     
     */
    public FiltroServizioUtenteEsternoBean getFiltroServizioUtenteEsterno() {
        return filtroServizioUtenteEsterno;
    }

    /**
     * Imposta il valore della propriet?? filtroServizioUtenteEsterno.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroServizioUtenteEsternoBean }
     *     
     */
    public void setFiltroServizioUtenteEsterno(FiltroServizioUtenteEsternoBean value) {
        this.filtroServizioUtenteEsterno = value;
    }

    /**
     * Recupera il valore della propriet?? filtroServizioEnte.
     * 
     * @return
     *     possible object is
     *     {@link FiltroServizioEnteBean }
     *     
     */
    public FiltroServizioEnteBean getFiltroServizioEnte() {
        return filtroServizioEnte;
    }

    /**
     * Imposta il valore della propriet?? filtroServizioEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroServizioEnteBean }
     *     
     */
    public void setFiltroServizioEnte(FiltroServizioEnteBean value) {
        this.filtroServizioEnte = value;
    }

    /**
     * Recupera il valore della propriet?? filtroSocieta.
     * 
     * @return
     *     possible object is
     *     {@link FiltroSocietaBean }
     *     
     */
    public FiltroSocietaBean getFiltroSocieta() {
        return filtroSocieta;
    }

    /**
     * Imposta il valore della propriet?? filtroSocieta.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroSocietaBean }
     *     
     */
    public void setFiltroSocieta(FiltroSocietaBean value) {
        this.filtroSocieta = value;
    }

    /**
     * Recupera il valore della propriet?? filtroTipologiaEnte.
     * 
     * @return
     *     possible object is
     *     {@link FiltroTipologiaEnteBean }
     *     
     */
    public FiltroTipologiaEnteBean getFiltroTipologiaEnte() {
        return filtroTipologiaEnte;
    }

    /**
     * Imposta il valore della propriet?? filtroTipologiaEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroTipologiaEnteBean }
     *     
     */
    public void setFiltroTipologiaEnte(FiltroTipologiaEnteBean value) {
        this.filtroTipologiaEnte = value;
    }

    /**
     * Recupera il valore della propriet?? stato.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStato() {
        return stato;
    }

    /**
     * Imposta il valore della propriet?? stato.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStato(Integer value) {
        this.stato = value;
    }

    /**
     * Recupera il valore della propriet?? svecchiata.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSvecchiata() {
        return svecchiata;
    }

    /**
     * Imposta il valore della propriet?? svecchiata.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSvecchiata(Boolean value) {
        this.svecchiata = value;
    }

    /**
     * Recupera il valore della propriet?? testo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTesto() {
        return testo;
    }

    /**
     * Imposta il valore della propriet?? testo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTesto(String value) {
        this.testo = value;
    }

    /**
     * Recupera il valore della propriet?? titolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Imposta il valore della propriet?? titolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitolo(String value) {
        this.titolo = value;
    }

    /**
     * Recupera il valore della propriet?? visualizzaPopUp.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVisualizzaPopUp() {
        return visualizzaPopUp;
    }

    /**
     * Imposta il valore della propriet?? visualizzaPopUp.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVisualizzaPopUp(Boolean value) {
        this.visualizzaPopUp = value;
    }

    /**
     * Recupera il valore della propriet?? visualizzaPopUpDescr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisualizzaPopUpDescr() {
        return visualizzaPopUpDescr;
    }

    /**
     * Imposta il valore della propriet?? visualizzaPopUpDescr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisualizzaPopUpDescr(String value) {
        this.visualizzaPopUpDescr = value;
    }

}
