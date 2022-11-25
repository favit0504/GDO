
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
     * Recupera il valore della proprietà agente.
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
     * Imposta il valore della proprietà agente.
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
     * Recupera il valore della proprietà allegato.
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
     * Imposta il valore della proprietà allegato.
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
     * Recupera il valore della proprietà altriUtenti.
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
     * Imposta il valore della proprietà altriUtenti.
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
     * Recupera il valore della proprietà utenteEsterno.
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
     * Imposta il valore della proprietà utenteEsterno.
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
     * Recupera il valore della proprietà dataFinePubblicazione.
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
     * Imposta il valore della proprietà dataFinePubblicazione.
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
     * Recupera il valore della proprietà dataInizioPubblicazione.
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
     * Imposta il valore della proprietà dataInizioPubblicazione.
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
     * Recupera il valore della proprietà descrizioneStato.
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
     * Imposta il valore della proprietà descrizioneStato.
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
     * Recupera il valore della proprietà descrizioneTipoUtente.
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
     * Imposta il valore della proprietà descrizioneTipoUtente.
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
     * Recupera il valore della proprietà ente.
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
     * Imposta il valore della proprietà ente.
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
     * Recupera il valore della proprietà filtroAmbito.
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
     * Imposta il valore della proprietà filtroAmbito.
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
     * Recupera il valore della proprietà filtroEnte.
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
     * Imposta il valore della proprietà filtroEnte.
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
     * Recupera il valore della proprietà filtroProvinciaEnte.
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
     * Imposta il valore della proprietà filtroProvinciaEnte.
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
     * Recupera il valore della proprietà filtroRegioneAgente.
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
     * Imposta il valore della proprietà filtroRegioneAgente.
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
     * Recupera il valore della proprietà filtroRegioneEnte.
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
     * Imposta il valore della proprietà filtroRegioneEnte.
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
     * Recupera il valore della proprietà filtroServizioAgente.
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
     * Imposta il valore della proprietà filtroServizioAgente.
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
     * Recupera il valore della proprietà filtroServizioAltriUtenti.
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
     * Imposta il valore della proprietà filtroServizioAltriUtenti.
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
     * Recupera il valore della proprietà filtroServizioUtenteEsterno.
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
     * Imposta il valore della proprietà filtroServizioUtenteEsterno.
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
     * Recupera il valore della proprietà filtroServizioEnte.
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
     * Imposta il valore della proprietà filtroServizioEnte.
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
     * Recupera il valore della proprietà filtroSocieta.
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
     * Imposta il valore della proprietà filtroSocieta.
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
     * Recupera il valore della proprietà filtroTipologiaEnte.
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
     * Imposta il valore della proprietà filtroTipologiaEnte.
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
     * Recupera il valore della proprietà stato.
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
     * Imposta il valore della proprietà stato.
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
     * Recupera il valore della proprietà svecchiata.
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
     * Imposta il valore della proprietà svecchiata.
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
     * Recupera il valore della proprietà testo.
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
     * Imposta il valore della proprietà testo.
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
     * Recupera il valore della proprietà titolo.
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
     * Imposta il valore della proprietà titolo.
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
     * Recupera il valore della proprietà visualizzaPopUp.
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
     * Imposta il valore della proprietà visualizzaPopUp.
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
     * Recupera il valore della proprietà visualizzaPopUpDescr.
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
     * Imposta il valore della proprietà visualizzaPopUpDescr.
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
