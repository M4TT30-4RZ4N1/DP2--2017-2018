//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.01.19 alle 11:49:35 AM CET 
//


package it.polito.dp2.NFV.sol3.service.nfvdeployer;

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
 *       &lt;attribute name="maxL" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="minT" use="required" type="{http://www.example.com/nfvdeployer}nonNegativeFloat" />
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
