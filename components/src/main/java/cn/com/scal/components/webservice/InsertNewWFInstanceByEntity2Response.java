
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
 *         &lt;element name="InsertNewWFInstanceByEntity2Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "insertNewWFInstanceByEntity2Result"
})
@XmlRootElement(name = "InsertNewWFInstanceByEntity2Response")
public class InsertNewWFInstanceByEntity2Response {

    @XmlElement(name = "InsertNewWFInstanceByEntity2Result")
    protected String insertNewWFInstanceByEntity2Result;

    /**
     * 获取insertNewWFInstanceByEntity2Result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsertNewWFInstanceByEntity2Result() {
        return insertNewWFInstanceByEntity2Result;
    }

    /**
     * 设置insertNewWFInstanceByEntity2Result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsertNewWFInstanceByEntity2Result(String value) {
        this.insertNewWFInstanceByEntity2Result = value;
    }

}
