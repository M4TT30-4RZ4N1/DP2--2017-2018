//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.11.29 alle 12:04:51 PM CET 
//


package it.polito.dp2.NFV.sol1.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.example.com/nfvInfo}catalog"/>
 *         &lt;element ref="{http://www.example.com/nfvInfo}nffg" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.example.com/nfvInfo}in"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "catalog",
    "nffg",
    "in"
})
@XmlRootElement(name = "virtualnetworkmanager")
public class Virtualnetworkmanager {

    @XmlElement(required = true)
    protected Catalog catalog;
    protected List<Nffg> nffg;
    @XmlElement(required = true)
    protected In in;

    /**
     * Recupera il valore della proprietà catalog.
     * 
     * @return
     *     possible object is
     *     {@link Catalog }
     *     
     */
    public Catalog getCatalog() {
        return catalog;
    }

    /**
     * Imposta il valore della proprietà catalog.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalog }
     *     
     */
    public void setCatalog(Catalog value) {
        this.catalog = value;
    }

    /**
     * Gets the value of the nffg property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nffg property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNffg().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Nffg }
     * 
     * 
     */
    public List<Nffg> getNffg() {
        if (nffg == null) {
            nffg = new ArrayList<Nffg>();
        }
        return this.nffg;
    }

    /**
     * Recupera il valore della proprietà in.
     * 
     * @return
     *     possible object is
     *     {@link In }
     *     
     */
    public In getIn() {
        return in;
    }

    /**
     * Imposta il valore della proprietà in.
     * 
     * @param value
     *     allowed object is
     *     {@link In }
     *     
     */
    public void setIn(In value) {
        this.in = value;
    }

}
