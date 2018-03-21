
package it.polito.dp2.NFV.sol3.client1.nfvdeployer;

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
 *         &lt;element ref="{http://www.example.com/nfvdeployer}RVnft" maxOccurs="unbounded" minOccurs="0"/>
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
    "rVnft"
})
@XmlRootElement(name = "RVnfts")
public class RVnfts {

    @XmlElement(name = "RVnft", namespace = "http://www.example.com/nfvdeployer")
    protected List<RVnft> rVnft;

    /**
     * Gets the value of the rVnft property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rVnft property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRVnft().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RVnft }
     * 
     * 
     */
    public List<RVnft> getRVnft() {
        if (rVnft == null) {
            rVnft = new ArrayList<RVnft>();
        }
        return this.rVnft;
    }

}
