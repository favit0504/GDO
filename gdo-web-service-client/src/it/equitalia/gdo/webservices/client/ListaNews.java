
package it.equitalia.gdo.webservices.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per listaNews complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="listaNews">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listaNews" type="{http://webservices.gdo.equitalia.it/}newsBean" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaNews", namespace = "http://webservices.gdo.equitalia.it/", propOrder = {
    "listaNews"
})
public class ListaNews {

    @XmlElement(nillable = true)
    protected List<NewsBean> listaNews;

    /**
     * Gets the value of the listaNews property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaNews property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaNews().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NewsBean }
     * 
     * 
     */
    public List<NewsBean> getListaNews() {
        if (listaNews == null) {
            listaNews = new ArrayList<NewsBean>();
        }
        return this.listaNews;
    }

}
