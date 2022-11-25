
package it.equitalia.gdo.webservices.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per filtroEnteBean complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="filtroEnteBean">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.gdo.equitalia.it/}abstractFiltroBean">
 *       &lt;sequence>
 *         &lt;element name="codiceEnte" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="codiceUfficioEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoUfficioEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filtroEnteBean", namespace = "http://webservices.gdo.equitalia.it/", propOrder = {
    "codiceEnte",
    "codiceUfficioEnte",
    "tipoUfficioEnte"
})
public class FiltroEnteBean
    extends AbstractFiltroBean
{

    protected Integer codiceEnte;
    protected String codiceUfficioEnte;
    protected String tipoUfficioEnte;

    /**
     * Recupera il valore della proprietà codiceEnte.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodiceEnte() {
        return codiceEnte;
    }

    /**
     * Imposta il valore della proprietà codiceEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodiceEnte(Integer value) {
        this.codiceEnte = value;
    }

    /**
     * Recupera il valore della proprietà codiceUfficioEnte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceUfficioEnte() {
        return codiceUfficioEnte;
    }

    /**
     * Imposta il valore della proprietà codiceUfficioEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceUfficioEnte(String value) {
        this.codiceUfficioEnte = value;
    }

    /**
     * Recupera il valore della proprietà tipoUfficioEnte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoUfficioEnte() {
        return tipoUfficioEnte;
    }

    /**
     * Imposta il valore della proprietà tipoUfficioEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoUfficioEnte(String value) {
        this.tipoUfficioEnte = value;
    }

}
