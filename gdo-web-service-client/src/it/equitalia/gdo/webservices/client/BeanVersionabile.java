
package it.equitalia.gdo.webservices.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per beanVersionabile complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="beanVersionabile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="dataCreazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataModifica" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="descrizioneValida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="owner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ownerModifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valida" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "beanVersionabile", namespace = "http://webservices.gdo.equitalia.it/", propOrder = {
    "codice",
    "dataCreazione",
    "dataModifica",
    "descrizioneValida",
    "id",
    "owner",
    "ownerModifica",
    "valida"
})
@XmlSeeAlso({
    NewsBean.class
})
public class BeanVersionabile {

    protected Integer codice;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataCreazione;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataModifica;
    protected String descrizioneValida;
    protected Integer id;
    protected String owner;
    protected String ownerModifica;
    protected Boolean valida;

    /**
     * Recupera il valore della proprietà codice.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprietà codice.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodice(Integer value) {
        this.codice = value;
    }

    /**
     * Recupera il valore della proprietà dataCreazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataCreazione() {
        return dataCreazione;
    }

    /**
     * Imposta il valore della proprietà dataCreazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataCreazione(XMLGregorianCalendar value) {
        this.dataCreazione = value;
    }

    /**
     * Recupera il valore della proprietà dataModifica.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataModifica() {
        return dataModifica;
    }

    /**
     * Imposta il valore della proprietà dataModifica.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataModifica(XMLGregorianCalendar value) {
        this.dataModifica = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneValida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneValida() {
        return descrizioneValida;
    }

    /**
     * Imposta il valore della proprietà descrizioneValida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneValida(String value) {
        this.descrizioneValida = value;
    }

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietà owner.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Imposta il valore della proprietà owner.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

    /**
     * Recupera il valore della proprietà ownerModifica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerModifica() {
        return ownerModifica;
    }

    /**
     * Imposta il valore della proprietà ownerModifica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerModifica(String value) {
        this.ownerModifica = value;
    }

    /**
     * Recupera il valore della proprietà valida.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isValida() {
        return valida;
    }

    /**
     * Imposta il valore della proprietà valida.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setValida(Boolean value) {
        this.valida = value;
    }

}
