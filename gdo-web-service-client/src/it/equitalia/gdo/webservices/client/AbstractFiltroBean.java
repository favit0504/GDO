
package it.equitalia.gdo.webservices.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per abstractFiltroBean complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="abstractFiltroBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idFiltro" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "abstractFiltroBean", namespace = "http://webservices.gdo.equitalia.it/", propOrder = {
    "idFiltro"
})
@XmlSeeAlso({
    FiltroAmbitoBean.class,
    FiltroEnteBean.class,
    FiltroServizioBean.class,
    FiltroRegioneBean.class,
    FiltroSocietaBean.class,
    FiltroTipologiaEnteBean.class,
    FiltroProvinciaBean.class
})
public abstract class AbstractFiltroBean {

    protected Integer idFiltro;

    /**
     * Recupera il valore della proprietà idFiltro.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdFiltro() {
        return idFiltro;
    }

    /**
     * Imposta il valore della proprietà idFiltro.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdFiltro(Integer value) {
        this.idFiltro = value;
    }

}
