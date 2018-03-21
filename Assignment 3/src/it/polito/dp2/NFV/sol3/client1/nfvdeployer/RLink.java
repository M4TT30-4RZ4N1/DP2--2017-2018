
package it.polito.dp2.NFV.sol3.client1.nfvdeployer;

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
 *     &lt;extension base="{http://www.example.com/nfvdeployer}RLinkageName">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="maxL" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="minT" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "RLink")
public class RLink
    extends RLinkageName
{

    @XmlAttribute(name = "maxL")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxL;
    @XmlAttribute(name = "minT")
    protected Float minT;

    /**
     * Recupera il valore della proprietà maxL.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxL() {
        return maxL;
    }

    /**
     * Imposta il valore della proprietà maxL.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxL(BigInteger value) {
        this.maxL = value;
    }

    /**
     * Recupera il valore della proprietà minT.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getMinT() {
        return minT;
    }

    /**
     * Imposta il valore della proprietà minT.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setMinT(Float value) {
        this.minT = value;
    }

}
