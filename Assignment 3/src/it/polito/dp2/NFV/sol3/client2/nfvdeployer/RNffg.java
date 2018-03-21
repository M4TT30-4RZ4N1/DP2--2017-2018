
package it.polito.dp2.NFV.sol3.client2.nfvdeployer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="deployTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="RNodes" type="{http://www.example.com/nfvdeployer}Reference"/>
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
    "deployTime",
    "rNodes",
    "rLinks"
})
@XmlRootElement(name = "RNffg")
public class RNffg
    extends ResourceName
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deployTime;
    @XmlElement(name = "RNodes", required = true)
    protected Reference rNodes;
    @XmlElement(name = "RLinks", required = true)
    protected Reference rLinks;

    /**
     * Recupera il valore della proprietà deployTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeployTime() {
        return deployTime;
    }

    /**
     * Imposta il valore della proprietà deployTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeployTime(XMLGregorianCalendar value) {
        this.deployTime = value;
    }

    /**
     * Recupera il valore della proprietà rNodes.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getRNodes() {
        return rNodes;
    }

    /**
     * Imposta il valore della proprietà rNodes.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setRNodes(Reference value) {
        this.rNodes = value;
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
