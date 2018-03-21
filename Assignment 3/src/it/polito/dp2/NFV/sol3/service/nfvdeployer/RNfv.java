//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.01.19 alle 11:49:35 AM CET 
//


package it.polito.dp2.NFV.sol3.service.nfvdeployer;

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
 *       &lt;all>
 *         &lt;element name="RNffgs" type="{http://www.example.com/nfvdeployer}Reference"/>
 *         &lt;element name="RVnfts" type="{http://www.example.com/nfvdeployer}Reference"/>
 *         &lt;element name="RHosts" type="{http://www.example.com/nfvdeployer}Reference"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "RNfv")
public class RNfv {

    @XmlElement(name = "RNffgs", required = true)
    protected Reference rNffgs;
    @XmlElement(name = "RVnfts", required = true)
    protected Reference rVnfts;
    @XmlElement(name = "RHosts", required = true)
    protected Reference rHosts;

    /**
     * Recupera il valore della proprietà rNffgs.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getRNffgs() {
        return rNffgs;
    }

    /**
     * Imposta il valore della proprietà rNffgs.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setRNffgs(Reference value) {
        this.rNffgs = value;
    }

    /**
     * Recupera il valore della proprietà rVnfts.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getRVnfts() {
        return rVnfts;
    }

    /**
     * Imposta il valore della proprietà rVnfts.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setRVnfts(Reference value) {
        this.rVnfts = value;
    }

    /**
     * Recupera il valore della proprietà rHosts.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getRHosts() {
        return rHosts;
    }

    /**
     * Imposta il valore della proprietà rHosts.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setRHosts(Reference value) {
        this.rHosts = value;
    }

}
