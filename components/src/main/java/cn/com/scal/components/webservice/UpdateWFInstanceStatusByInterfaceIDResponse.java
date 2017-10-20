
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
 *         &lt;element name="UpdateWFInstanceStatusByInterfaceIDResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "updateWFInstanceStatusByInterfaceIDResult"
})
@XmlRootElement(name = "UpdateWFInstanceStatusByInterfaceIDResponse")
public class UpdateWFInstanceStatusByInterfaceIDResponse {

    @XmlElement(name = "UpdateWFInstanceStatusByInterfaceIDResult")
    protected String updateWFInstanceStatusByInterfaceIDResult;

    /**
     * 获取updateWFInstanceStatusByInterfaceIDResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateWFInstanceStatusByInterfaceIDResult() {
        return updateWFInstanceStatusByInterfaceIDResult;
    }

    /**
     * 设置updateWFInstanceStatusByInterfaceIDResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateWFInstanceStatusByInterfaceIDResult(String value) {
        this.updateWFInstanceStatusByInterfaceIDResult = value;
    }

}
