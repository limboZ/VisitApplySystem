
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
     * ��ȡupdateWFInstanceStatusByInterfaceIDResult���Ե�ֵ��
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
     * ����updateWFInstanceStatusByInterfaceIDResult���Ե�ֵ��
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
