
package namespace.webservice.xsd;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for extensionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="extensionType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="jpg"/&gt;
 *     &lt;enumeration value="jpeg"/&gt;
 *     &lt;enumeration value="png"/&gt;
 *     &lt;enumeration value="gif"/&gt;
 *     &lt;enumeration value="svg"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "extensionType")
@XmlEnum
public enum ExtensionType {

    jpg,
    jpeg,
    png,
    gif,
    svg;

    public String value() {
        return name();
    }

    public static ExtensionType fromValue(String v) {
        return valueOf(v);
    }

}
