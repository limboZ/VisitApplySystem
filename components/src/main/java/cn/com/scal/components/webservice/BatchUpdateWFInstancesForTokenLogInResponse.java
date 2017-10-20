
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
 *         &lt;element name="BatchUpdateWFInstancesForTokenLogInResult" type="{http://tempuri.org/}ArrayOfString" minOccurs="0"/&gt;
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
    "batchUpdateWFInstancesForTokenLogInResult"
})
@XmlRootElement(name = "BatchUpdateWFInstancesForTokenLogInResponse")
public class BatchUpdateWFInstancesForTokenLogInResponse {

    @XmlElement(name = "BatchUpdateWFInstancesForTokenLogInResult")
    protected ArrayOfString batchUpdateWFInstancesForTokenLogInResult;

    /**
     * 获取batchUpdateWFInstancesForTokenLogInResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getBatchUpdateWFInstancesForTokenLogInResult() {
        return batchUpdateWFInstancesForTokenLogInResult;
    }

    /**
     * 设置batchUpdateWFInstancesForTokenLogInResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setBatchUpdateWFInstancesForTokenLogInResult(ArrayOfString value) {
        this.batchUpdateWFInstancesForTokenLogInResult = value;
    }

}
