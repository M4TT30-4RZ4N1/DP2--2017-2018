
package it.polito.dp2.NFV.sol3.client2.nfvdeployer;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;/sequence>
 *       &lt;attribute name="vnftype" use="required" type="{http://www.example.com/nfvdeployer}RVnftType" />
 *       &lt;attribute name="memory" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="storage" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "RVnft")
public class RVnft
    extends ResourceName
{

    @XmlAttribute(name = "vnftype", required = true)
    protected RVnftType vnftype;
    @XmlAttribute(name = "memory", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger memory;
    @XmlAttribute(name = "storage", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger storage;

    /**
     * Recupera il valore della proprietà vnftype.
     * 
     * @return
     *     possible object is
     *     {@link RVnftType }
     *     
     */
    public RVnftType getVnftype() {
        return vnftype;
    }

    /**
     * Imposta il valore della proprietà vnftype.
     * 
     * @param value
     *     allowed object is
     *     {@link RVnftType }
     *     
     */
    public void setVnftype(RVnftType value) {
        this.vnftype = value;
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

}
