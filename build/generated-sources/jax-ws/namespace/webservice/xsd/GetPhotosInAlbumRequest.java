
package namespace.webservice.xsd;

import java.math.BigInteger;
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
 *         &lt;element name="albumID" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
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
    "albumID"
})
@XmlRootElement(name = "GetPhotosInAlbumRequest")
public class GetPhotosInAlbumRequest {

    @XmlElement(name = "API_TOKEN", required = true)
    protected String apitoken;
    @XmlElement(name = "REQUEST_ID", required = true)
    protected String requestid;
    @XmlElement(required = true)
    protected BigInteger albumID;

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
     * Gets the value of the albumID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAlbumID() {
        return albumID;
    }

    /**
     * Sets the value of the albumID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAlbumID(BigInteger value) {
        this.albumID = value;
    }

}
