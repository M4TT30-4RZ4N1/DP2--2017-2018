//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.11.29 alle 12:04:51 PM CET 
//


package it.polito.dp2.NFV.sol1.jaxb;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attGroup ref="{http://www.example.com/nfvInfo}avglatencyGroup"/>
 *       &lt;attribute name="h1" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="h2" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="throughput">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *             &lt;minInclusive value="0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "connection")
public class Connection {

    @XmlAttribute(name = "h1", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String h1;
    @XmlAttribute(name = "h2", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String h2;
    @XmlAttribute(name = "throughput")
    protected Double throughput;
    @XmlAttribute(name = "latency", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger latency;

    /**
     * Recupera il valore della proprietà h1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getH1() {
        return h1;
    }

    /**
     * Imposta il valore della proprietà h1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setH1(String value) {
        this.h1 = value;
    }

    /**
     * Recupera il valore della proprietà h2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getH2() {
        return h2;
    }

    /**
     * Imposta il valore della proprietà h2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setH2(String value) {
        this.h2 = value;
    }

    /**
     * Recupera il valore della proprietà throughput.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getThroughput() {
        return throughput;
    }

    /**
     * Imposta il valore della proprietà throughput.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setThroughput(Double value) {
        this.throughput = value;
    }

    /**
     * Recupera il valore della proprietà latency.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLatency() {
        return latency;
    }

    /**
     * Imposta il valore della proprietà latency.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLatency(BigInteger value) {
        this.latency = value;
    }

}
