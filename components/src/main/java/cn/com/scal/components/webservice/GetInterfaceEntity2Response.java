
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
     * ��ȡgetInterfaceEntity2Result���Ե�ֵ��
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
     * ����getInterfaceEntity2Result���Ե�ֵ��
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
