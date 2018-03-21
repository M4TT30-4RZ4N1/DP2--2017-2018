
package it.polito.dp2.NFV.sol3.client2.nfvdeployer;

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
 *         &lt;element name="RHosts" type="{http://www.example.com/nfvdeployer}ResourceName" maxOccurs="unbounded" minOccurs="0"/>
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
    "rHosts"
})
@XmlRootElement(name = "RHosts")
public class RHosts {

    @XmlElement(name = "RHosts")
    protected List<ResourceName> rHosts;

    /**
     * Gets the value of the rHosts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rHosts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRHosts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResourceName }
     * 
     * 
     */
    public List<ResourceName> getRHosts() {
        if (rHosts == null) {
            rHosts = new ArrayList<ResourceName>();
        }
        return this.rHosts;
    }

}
