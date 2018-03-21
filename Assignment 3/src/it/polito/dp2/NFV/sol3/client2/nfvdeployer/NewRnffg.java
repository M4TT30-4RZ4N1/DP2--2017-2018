
package it.polito.dp2.NFV.sol3.client2.nfvdeployer;

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
 *       &lt;sequence>
 *         &lt;element name="RNodes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RNode" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="RVnft" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *                             &lt;element name="ownerHost" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="RLinks">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RLink" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="srcNode" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *                             &lt;element name="dstNode" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *                           &lt;attribute name="maxL" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                           &lt;attribute name="minT" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rNodes",
    "rLinks"
})
@XmlRootElement(name = "newRnffg")
public class NewRnffg {

    @XmlElement(name = "RNodes", required = true)
    protected NewRnffg.RNodes rNodes;
    @XmlElement(name = "RLinks", required = true)
    protected NewRnffg.RLinks rLinks;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Recupera il valore della proprietà rNodes.
     * 
     * @return
     *     possible object is
     *     {@link NewRnffg.RNodes }
     *     
     */
    public NewRnffg.RNodes getRNodes() {
        return rNodes;
    }

    /**
     * Imposta il valore della proprietà rNodes.
     * 
     * @param value
     *     allowed object is
     *     {@link NewRnffg.RNodes }
     *     
     */
    public void setRNodes(NewRnffg.RNodes value) {
        this.rNodes = value;
    }

    /**
     * Recupera il valore della proprietà rLinks.
     * 
     * @return
     *     possible object is
     *     {@link NewRnffg.RLinks }
     *     
     */
    public NewRnffg.RLinks getRLinks() {
        return rLinks;
    }

    /**
     * Imposta il valore della proprietà rLinks.
     * 
     * @param value
     *     allowed object is
     *     {@link NewRnffg.RLinks }
     *     
     */
    public void setRLinks(NewRnffg.RLinks value) {
        this.rLinks = value;
    }

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     *         &lt;element name="RLink" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="srcNode" type="{http://www.w3.org/2001/XMLSchema}token"/>
     *                   &lt;element name="dstNode" type="{http://www.w3.org/2001/XMLSchema}token"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
     *                 &lt;attribute name="maxL" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
     *                 &lt;attribute name="minT" type="{http://www.w3.org/2001/XMLSchema}float" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "rLink"
    })
    public static class RLinks {

        @XmlElement(name = "RLink")
        protected List<NewRnffg.RLinks.RLink> rLink;

        /**
         * Gets the value of the rLink property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rLink property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRLink().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link NewRnffg.RLinks.RLink }
         * 
         * 
         */
        public List<NewRnffg.RLinks.RLink> getRLink() {
            if (rLink == null) {
                rLink = new ArrayList<NewRnffg.RLinks.RLink>();
            }
            return this.rLink;
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
         *         &lt;element name="srcNode" type="{http://www.w3.org/2001/XMLSchema}token"/>
         *         &lt;element name="dstNode" type="{http://www.w3.org/2001/XMLSchema}token"/>
         *       &lt;/sequence>
         *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
         *       &lt;attribute name="maxL" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
         *       &lt;attribute name="minT" type="{http://www.w3.org/2001/XMLSchema}float" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "srcNode",
            "dstNode"
        })
        public static class RLink {

            @XmlElement(required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String srcNode;
            @XmlElement(required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String dstNode;
            @XmlAttribute(name = "name", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String name;
            @XmlAttribute(name = "maxL")
            @XmlSchemaType(name = "nonNegativeInteger")
            protected BigInteger maxL;
            @XmlAttribute(name = "minT")
            protected Float minT;

            /**
             * Recupera il valore della proprietà srcNode.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSrcNode() {
                return srcNode;
            }

            /**
             * Imposta il valore della proprietà srcNode.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSrcNode(String value) {
                this.srcNode = value;
            }

            /**
             * Recupera il valore della proprietà dstNode.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDstNode() {
                return dstNode;
            }

            /**
             * Imposta il valore della proprietà dstNode.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDstNode(String value) {
                this.dstNode = value;
            }

            /**
             * Recupera il valore della proprietà name.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Imposta il valore della proprietà name.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

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
     *         &lt;element name="RNode" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="RVnft" type="{http://www.w3.org/2001/XMLSchema}token"/>
     *                   &lt;element name="ownerHost" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "rNode"
    })
    public static class RNodes {

        @XmlElement(name = "RNode")
        protected List<NewRnffg.RNodes.RNode> rNode;

        /**
         * Gets the value of the rNode property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rNode property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRNode().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link NewRnffg.RNodes.RNode }
         * 
         * 
         */
        public List<NewRnffg.RNodes.RNode> getRNode() {
            if (rNode == null) {
                rNode = new ArrayList<NewRnffg.RNodes.RNode>();
            }
            return this.rNode;
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
         *         &lt;element name="RVnft" type="{http://www.w3.org/2001/XMLSchema}token"/>
         *         &lt;element name="ownerHost" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "rVnft",
            "ownerHost"
        })
        public static class RNode {

            @XmlElement(name = "RVnft", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String rVnft;
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String ownerHost;
            @XmlAttribute(name = "name", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String name;

            /**
             * Recupera il valore della proprietà rVnft.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRVnft() {
                return rVnft;
            }

            /**
             * Imposta il valore della proprietà rVnft.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRVnft(String value) {
                this.rVnft = value;
            }

            /**
             * Recupera il valore della proprietà ownerHost.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOwnerHost() {
                return ownerHost;
            }

            /**
             * Imposta il valore della proprietà ownerHost.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOwnerHost(String value) {
                this.ownerHost = value;
            }

            /**
             * Recupera il valore della proprietà name.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Imposta il valore della proprietà name.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

        }

    }

}
