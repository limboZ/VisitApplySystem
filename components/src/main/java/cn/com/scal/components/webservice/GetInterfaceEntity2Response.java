
package cn.com.scal.components.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GetInterfaceEntity2Result" type="{http://tempuri.org/}WFInterfaceEntity" minOccurs="0"/&gt;
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
    "getInterfaceEntity2Result"
})
@XmlRootElement(name = "GetInterfaceEntity2Response")
public class GetInterfaceEntity2Response {

    @XmlElement(name = "GetInterfaceEntity2Result")
    protected WFInterfaceEntity getInterfaceEntity2Result;

    /**
     * 获取getInterfaceEntity2Result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WFInterfaceEntity }
     *     
     */
    public WFInterfaceEntity getGetInterfaceEntity2Result() {
        return getInterfaceEntity2Result;
    }

    /**
     * 设置getInterfaceEntity2Result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WFInterfaceEntity }
     *     
     */
    public void setGetInterfaceEntity2Result(WFInterfaceEntity value) {
        this.getInterfaceEntity2Result = value;
    }

}
