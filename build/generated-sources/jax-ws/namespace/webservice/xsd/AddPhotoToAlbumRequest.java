
package namespace.webservice.xsd;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="API_TOKEN" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="REQUEST_ID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="photoID" type="{http://www.w3.org/2001/XMLSchema}integer" maxOccurs="unbounded"/&gt;
 *         &lt;element name="albumID" type="{http://www.w3.org/2001/XMLSchema}integer" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "apitoken",
    "requestid",
    "photoID",
    "albumID"
})
@XmlRootElement(name = "AddPhotoToAlbumRequest")
public class AddPhotoToAlbumRequest {

    @XmlElement(name = "API_TOKEN", required = true)
    protected String apitoken;
    @XmlElement(name = "REQUEST_ID", required = true)
    protected String requestid;
    @XmlElement(required = true)
    protected List<BigInteger> photoID;
    @XmlElement(required = true)
    protected List<BigInteger> albumID;

    /**
     * Gets the value of the apitoken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPITOKEN() {
        return apitoken;
    }

    /**
     * Sets the value of the apitoken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPITOKEN(String value) {
        this.apitoken = value;
    }

    /**
     * Gets the value of the requestid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREQUESTID() {
        return requestid;
    }

    /**
     * Sets the value of the requestid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREQUESTID(String value) {
        this.requestid = value;
    }

    /**
     * Gets the value of the photoID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the photoID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhotoID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getPhotoID() {
        if (photoID == null) {
            photoID = new ArrayList<BigInteger>();
        }
        return this.photoID;
    }

    /**
     * Gets the value of the albumID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the albumID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlbumID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getAlbumID() {
        if (albumID == null) {
            albumID = new ArrayList<BigInteger>();
        }
        return this.albumID;
    }

}
