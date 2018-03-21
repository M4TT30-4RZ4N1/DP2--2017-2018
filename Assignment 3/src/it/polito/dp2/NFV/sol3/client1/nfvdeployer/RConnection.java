
package it.polito.dp2.NFV.sol3.client1.nfvdeployer;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per RConnection complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="RConnection">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.com/nfvdeployer}RLinkage">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="maxL" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="minT" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RConnection")
public class RConnection
    extends RLinkage
{

    @XmlAttribute(name = "maxL", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxL;
    @XmlAttribute(name = "minT", required = true)
    protected float minT;

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
     */
    public float getMinT() {
        return minT;
    }

    /**
     * Imposta il valore della proprietà minT.
     * 
     */
    public void setMinT(float value) {
        this.minT = value;
    }

}
