
package cn.com.scal.components.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WFInterfaceEntity2 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WFInterfaceEntity2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IdentityOfSourceSystem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdentityOfOAInterface" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Instance_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Step_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Project_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Project_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Instance_Title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Form_URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MobileForm_Url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="InternetForm_Url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Step_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Flow_Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Import_Level" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="User_ID_Consigned" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="User_Name_Consigned" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="User_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="User_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="User_DeptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Creat_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Creat_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Creat_Dept_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Creat_Phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Creat_Time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Receive_Time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Read_Time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Finish_Time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WFInterfaceEntity2", propOrder = {
    "identityOfSourceSystem",
    "identityOfOAInterface",
    "instanceID",
    "stepID",
    "projectCode",
    "projectName",
    "instanceTitle",
    "formURL",
    "mobileFormUrl",
    "internetFormUrl",
    "stepName",
    "flowType",
    "importLevel",
    "userIDConsigned",
    "userNameConsigned",
    "userID",
    "userName",
    "userDeptName",
    "creatID",
    "creatName",
    "creatDeptName",
    "creatPhone",
    "creatTime",
    "receiveTime",
    "readTime",
    "finishTime"
})
public class WFInterfaceEntity2 {

    @XmlElement(name = "IdentityOfSourceSystem")
    protected String identityOfSourceSystem;
    @XmlElement(name = "IdentityOfOAInterface")
    protected String identityOfOAInterface;
    @XmlElement(name = "Instance_ID")
    protected String instanceID;
    @XmlElement(name = "Step_ID")
    protected String stepID;
    @XmlElement(name = "Project_Code")
    protected String projectCode;
    @XmlElement(name = "Project_Name")
    protected String projectName;
    @XmlElement(name = "Instance_Title")
    protected String instanceTitle;
    @XmlElement(name = "Form_URL")
    protected String formURL;
    @XmlElement(name = "MobileForm_Url")
    protected String mobileFormUrl;
    @XmlElement(name = "InternetForm_Url")
    protected String internetFormUrl;
    @XmlElement(name = "Step_Name")
    protected String stepName;
    @XmlElement(name = "Flow_Type")
    protected String flowType;
    @XmlElement(name = "Import_Level")
    protected String importLevel;
    @XmlElement(name = "User_ID_Consigned")
    protected String userIDConsigned;
    @XmlElement(name = "User_Name_Consigned")
    protected String userNameConsigned;
    @XmlElement(name = "User_ID")
    protected String userID;
    @XmlElement(name = "User_Name")
    protected String userName;
    @XmlElement(name = "User_DeptName")
    protected String userDeptName;
    @XmlElement(name = "Creat_ID")
    protected String creatID;
    @XmlElement(name = "Creat_Name")
    protected String creatName;
    @XmlElement(name = "Creat_Dept_Name")
    protected String creatDeptName;
    @XmlElement(name = "Creat_Phone")
    protected String creatPhone;
    @XmlElement(name = "Creat_Time")
    protected String creatTime;
    @XmlElement(name = "Receive_Time")
    protected String receiveTime;
    @XmlElement(name = "Read_Time")
    protected String readTime;
    @XmlElement(name = "Finish_Time")
    protected String finishTime;

    /**
     * 获取identityOfSourceSystem属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentityOfSourceSystem() {
        return identityOfSourceSystem;
    }

    /**
     * 设置identityOfSourceSystem属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentityOfSourceSystem(String value) {
        this.identityOfSourceSystem = value;
    }

    /**
     * 获取identityOfOAInterface属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentityOfOAInterface() {
        return identityOfOAInterface;
    }

    /**
     * 设置identityOfOAInterface属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentityOfOAInterface(String value) {
        this.identityOfOAInterface = value;
    }

    /**
     * 获取instanceID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceID() {
        return instanceID;
    }

    /**
     * 设置instanceID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceID(String value) {
        this.instanceID = value;
    }

    /**
     * 获取stepID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStepID() {
        return stepID;
    }

    /**
     * 设置stepID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStepID(String value) {
        this.stepID = value;
    }

    /**
     * 获取projectCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * 设置projectCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectCode(String value) {
        this.projectCode = value;
    }

    /**
     * 获取projectName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 设置projectName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectName(String value) {
        this.projectName = value;
    }

    /**
     * 获取instanceTitle属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceTitle() {
        return instanceTitle;
    }

    /**
     * 设置instanceTitle属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceTitle(String value) {
        this.instanceTitle = value;
    }

    /**
     * 获取formURL属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormURL() {
        return formURL;
    }

    /**
     * 设置formURL属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormURL(String value) {
        this.formURL = value;
    }

    /**
     * 获取mobileFormUrl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileFormUrl() {
        return mobileFormUrl;
    }

    /**
     * 设置mobileFormUrl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileFormUrl(String value) {
        this.mobileFormUrl = value;
    }

    /**
     * 获取internetFormUrl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternetFormUrl() {
        return internetFormUrl;
    }

    /**
     * 设置internetFormUrl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternetFormUrl(String value) {
        this.internetFormUrl = value;
    }

    /**
     * 获取stepName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStepName() {
        return stepName;
    }

    /**
     * 设置stepName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStepName(String value) {
        this.stepName = value;
    }

    /**
     * 获取flowType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlowType() {
        return flowType;
    }

    /**
     * 设置flowType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlowType(String value) {
        this.flowType = value;
    }

    /**
     * 获取importLevel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImportLevel() {
        return importLevel;
    }

    /**
     * 设置importLevel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImportLevel(String value) {
        this.importLevel = value;
    }

    /**
     * 获取userIDConsigned属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserIDConsigned() {
        return userIDConsigned;
    }

    /**
     * 设置userIDConsigned属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserIDConsigned(String value) {
        this.userIDConsigned = value;
    }

    /**
     * 获取userNameConsigned属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserNameConsigned() {
        return userNameConsigned;
    }

    /**
     * 设置userNameConsigned属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserNameConsigned(String value) {
        this.userNameConsigned = value;
    }

    /**
     * 获取userID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * 设置userID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
    }

    /**
     * 获取userName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置userName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * 获取userDeptName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserDeptName() {
        return userDeptName;
    }

    /**
     * 设置userDeptName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserDeptName(String value) {
        this.userDeptName = value;
    }

    /**
     * 获取creatID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatID() {
        return creatID;
    }

    /**
     * 设置creatID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatID(String value) {
        this.creatID = value;
    }

    /**
     * 获取creatName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatName() {
        return creatName;
    }

    /**
     * 设置creatName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatName(String value) {
        this.creatName = value;
    }

    /**
     * 获取creatDeptName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatDeptName() {
        return creatDeptName;
    }

    /**
     * 设置creatDeptName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatDeptName(String value) {
        this.creatDeptName = value;
    }

    /**
     * 获取creatPhone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatPhone() {
        return creatPhone;
    }

    /**
     * 设置creatPhone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatPhone(String value) {
        this.creatPhone = value;
    }

    /**
     * 获取creatTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatTime() {
        return creatTime;
    }

    /**
     * 设置creatTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatTime(String value) {
        this.creatTime = value;
    }

    /**
     * 获取receiveTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiveTime() {
        return receiveTime;
    }

    /**
     * 设置receiveTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiveTime(String value) {
        this.receiveTime = value;
    }

    /**
     * 获取readTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReadTime() {
        return readTime;
    }

    /**
     * 设置readTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReadTime(String value) {
        this.readTime = value;
    }

    /**
     * 获取finishTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinishTime() {
        return finishTime;
    }

    /**
     * 设置finishTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinishTime(String value) {
        this.finishTime = value;
    }

}
