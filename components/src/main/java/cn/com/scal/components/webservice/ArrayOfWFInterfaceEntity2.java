
package cn.com.scal.components.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfWFInterfaceEntity2 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWFInterfaceEntity2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WFInterfaceEntity2" type="{http://tempuri.org/}WFInterfaceEntity2" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWFInterfaceEntity2", propOrder = {
    "wfInterfaceEntity2"
})
public class ArrayOfWFInterfaceEntity2 {

    @XmlElement(name = "WFInterfaceEntity2", nillable = true)
    protected List<WFInterfaceEntity2> wfInterfaceEntity2;

    /**
     * Gets the value of the wfInterfaceEntity2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wfInterfaceEntity2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWFInterfaceEntity2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WFInterfaceEntity2 }
     * 
     * 
     */
    public List<WFInterfaceEntity2> getWFInterfaceEntity2() {
        if (wfInterfaceEntity2 == null) {
            wfInterfaceEntity2 = new ArrayList<WFInterfaceEntity2>();
        }
        return this.wfInterfaceEntity2;
    }

}
