
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
 *         &lt;element name="UpdateWFInstanceStatus3Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "updateWFInstanceStatus3Result"
})
@XmlRootElement(name = "UpdateWFInstanceStatus3Response")
public class UpdateWFInstanceStatus3Response {

    @XmlElement(name = "UpdateWFInstanceStatus3Result")
    protected String updateWFInstanceStatus3Result;

    /**
     * ��ȡupdateWFInstanceStatus3Result���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateWFInstanceStatus3Result() {
        return updateWFInstanceStatus3Result;
    }

    /**
     * ����updateWFInstanceStatus3Result���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateWFInstanceStatus3Result(String value) {
        this.updateWFInstanceStatus3Result = value;
    }

}
