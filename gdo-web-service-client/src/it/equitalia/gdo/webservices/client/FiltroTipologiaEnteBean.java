
package it.equitalia.gdo.webservices.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per filtroTipologiaEnteBean complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="filtroTipologiaEnteBean">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.gdo.equitalia.it/}abstractFiltroBean">
 *       &lt;sequence>
 *         &lt;element name="valori" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filtroTipologiaEnteBean", namespace = "http://webservices.gdo.equitalia.it/", propOrder = {
    "valori"
})
public class FiltroTipologiaEnteBean
    extends AbstractFiltroBean
{

    @XmlElement(nillable = true)
    protected List<String> valori;

    /**
     * Gets the value of the valori property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valori property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValori().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getValori() {
        if (valori == null) {
            valori = new ArrayList<String>();
        }
        return this.valori;
    }

}
