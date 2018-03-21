
package it.polito.dp2.NFV.sol3.client1.nfvdeployer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="deployedNodes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RNodes" type="{http://www.example.com/nfvdeployer}ResourceName" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="RConnections" type="{http://www.example.com/nfvdeployer}Reference"/>
 *       &lt;/sequence>
 *       &lt;attribute name="memory" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="storage" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="maxRvnfts" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "deployedNodes",
    "rConnections"
})
@XmlRootElement(name = "RHost")
public class RHost
    extends ResourceName
{

    @XmlElement(required = true)
    protected RHost.DeployedNodes deployedNodes;
    @XmlElement(name = "RConnections", required = true)
    protected Reference rConnections;
    @XmlAttribute(name = "memory", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger memory;
    @XmlAttribute(name = "storage", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger storage;
    @XmlAttribute(name = "maxRvnfts", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxRvnfts;

    /**
     * Recupera il valore della proprietà deployedNodes.
     * 
     * @return
     *     possible object is
     *     {@link RHost.DeployedNodes }
     *     
     */
    public RHost.DeployedNodes getDeployedNodes() {
        return deployedNodes;
    }

    /**
     * Imposta il valore della proprietà deployedNodes.
     * 
     * @param value
     *     allowed object is
     *     {@link RHost.DeployedNodes }
     *     
     */
    public void setDeployedNodes(RHost.DeployedNodes value) {
        this.deployedNodes = value;
    }

    /**
     * Recupera il valore della proprietà rConnections.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getRConnections() {
        return rConnections;
    }

    /**
     * Imposta il valore della proprietà rConnections.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setRConnections(Reference value) {
        this.rConnections = value;
    }

    /**
     * Recupera il valore della proprietà memory.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMemory() {
        return memory;
    }

    /**
     * Imposta il valore della proprietà memory.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMemory(BigInteger value) {
        this.memory = value;
    }

    /**
     * Recupera il valore della proprietà storage.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStorage() {
        return storage;
    }

    /**
     * Imposta il valore della proprietà storage.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStorage(BigInteger value) {
        this.storage = value;
    }

    /**
     * Recupera il valore della proprietà maxRvnfts.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxRvnfts() {
        return maxRvnfts;
    }

    /**
     * Imposta il valore della proprietà maxRvnfts.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxRvnfts(BigInteger value) {
        this.maxRvnfts = value;
    }


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
     *         &lt;element name="RNodes" type="{http://www.example.com/nfvdeployer}ResourceName" maxOccurs="unbounded" minOccurs="0"/>
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
        "rNodes"
    })
    public static class DeployedNodes {

        @XmlElement(name = "RNodes")
        protected List<ResourceName> rNodes;

        /**
         * Gets the value of the rNodes property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rNodes property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRNodes().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ResourceName }
         * 
         * 
         */
        public List<ResourceName> getRNodes() {
            if (rNodes == null) {
                rNodes = new ArrayList<ResourceName>();
            }
            return this.rNodes;
        }

    }

}
