
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
 *         &lt;element name="BatchInsertNewWFInstancesResult" type="{http://tempuri.org/}ArrayOfWFInterfaceEntity2" minOccurs="0"/&gt;
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
    "batchInsertNewWFInstancesResult"
})
@XmlRootElement(name = "BatchInsertNewWFInstancesResponse")
public class BatchInsertNewWFInstancesResponse {

    @XmlElement(name = "BatchInsertNewWFInstancesResult")
    protected ArrayOfWFInterfaceEntity2 batchInsertNewWFInstancesResult;

    /**
     * ��ȡbatchInsertNewWFInstancesResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWFInterfaceEntity2 }
     *     
     */
    public ArrayOfWFInterfaceEntity2 getBatchInsertNewWFInstancesResult() {
        return batchInsertNewWFInstancesResult;
    }

    /**
     * ����batchInsertNewWFInstancesResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWFInterfaceEntity2 }
     *     
     */
    public void setBatchInsertNewWFInstancesResult(ArrayOfWFInterfaceEntity2 value) {
        this.batchInsertNewWFInstancesResult = value;
    }

}
