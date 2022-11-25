
package it.equitalia.gdo.webservices.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per recuperaNewsAttivePerAltriUtentiResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="recuperaNewsAttivePerAltriUtentiResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://webservices.gdo.equitalia.it/}listaNews" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recuperaNewsAttivePerAltriUtentiResponse", namespace = "http://webservices.gdo.equitalia.it/", propOrder = {
    "_return"
})
public class RecuperaNewsAttivePerAltriUtentiResponse {

    @XmlElement(name = "return")
    protected ListaNews _return;

    /**
     * Recupera il valore della proprietà return.
     * 
     * @return
     *     possible object is
     *     {@link ListaNews }
     *     
     */
    public ListaNews getReturn() {
        return _return;
    }

    /**
     * Imposta il valore della proprietà return.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaNews }
     *     
     */
    public void setReturn(ListaNews value) {
        this._return = value;
    }

}
