
package it.polito.dp2.NFV.sol3.client1.nfvdeployer;

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
 *     &lt;extension base="{http://www.example.com/nfvdeployer}ResourceName">
 *       &lt;sequence>
 *         &lt;element name="RHost" type="{http://www.example.com/nfvdeployer}ResourceName" minOccurs="0"/>
 *         &lt;element name="RVnft" type="{http://www.example.com/nfvdeployer}ResourceName"/>
 *         &lt;element name="RNffg" type="{http://www.example.com/nfvdeployer}ResourceName"/>
 *         &lt;element name="reachableHosts" type="{http://www.example.com/nfvdeployer}Reference"/>
 *         &lt;element name="RLinks" type="{http://www.example.com/nfvdeployer}Reference"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rHost",
    "rVnft",
    "rNffg",
    "reachableHosts",
    "rLinks"
})
@XmlRootElement(name = "RNode")
public class RNode
    extends ResourceName
{

    @XmlElement(name = "RHost")
    protected ResourceName rHost;
    @XmlElement(name = "RVnft", required = true)
    protected ResourceName rVnft;
    @XmlElement(name = "RNffg", required = true)
    protected ResourceName rNffg;
    @XmlElement(required = true)
    protected Reference reachableHosts;
    @XmlElement(name = "RLinks", required = true)
    protected Reference rLinks;

    /**
     * Recupera il valore della proprietà rHost.
     * 
     * @return
     *     possible object is
     *     {@link ResourceName }
     *     
     */
    public ResourceName getRHost() {
        return rHost;
    }

    /**
     * Imposta il valore della proprietà rHost.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceName }
     *     
     */
    public void setRHost(ResourceName value) {
        this.rHost = value;
    }

    /**
     * Recupera il valore della proprietà rVnft.
     * 
     * @return
     *     possible object is
     *     {@link ResourceName }
     *     
     */
    public ResourceName getRVnft() {
        return rVnft;
    }

    /**
     * Imposta il valore della proprietà rVnft.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceName }
     *     
     */
    public void setRVnft(ResourceName value) {
        this.rVnft = value;
    }

    /**
     * Recupera il valore della proprietà rNffg.
     * 
     * @return
     *     possible object is
     *     {@link ResourceName }
     *     
     */
    public ResourceName getRNffg() {
        return rNffg;
    }

    /**
     * Imposta il valore della proprietà rNffg.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceName }
     *     
     */
    public void setRNffg(ResourceName value) {
        this.rNffg = value;
    }

    /**
     * Recupera il valore della proprietà reachableHosts.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getReachableHosts() {
        return reachableHosts;
    }

    /**
     * Imposta il valore della proprietà reachableHosts.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setReachableHosts(Reference value) {
        this.reachableHosts = value;
    }

    /**
     * Recupera il valore della proprietà rLinks.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getRLinks() {
        return rLinks;
    }

    /**
     * Imposta il valore della proprietà rLinks.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setRLinks(Reference value) {
        this.rLinks = value;
    }

}
