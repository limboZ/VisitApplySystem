
package cn.com.scal.components.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡinsertNewWFInstanceByEntity2Result���Ե�ֵ��
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
     * ����insertNewWFInstanceByEntity2Result���Ե�ֵ��
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
