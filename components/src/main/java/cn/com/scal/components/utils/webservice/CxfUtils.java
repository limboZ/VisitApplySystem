package cn.com.scal.components.utils.webservice;

import cn.com.scal.components.webservice.OaWfWebServiceSoap;
import cn.com.scal.components.webservice.WFInterfaceEntity;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.util.Properties;

/**
 * 使用CXF调用WebService的工具类.
 *
 * @author 李程鹏
 */
public class CxfUtils {
    /**
     * <strong>Description:</strong>
     * <pre>
     * 创建访问的接口对象.
     * </pre>
     *
     * @param address WebService的地址
     * @param clazz   接口的类型
     * @return {@code java.lang.Object} - 接口对象
     */
    public static Object createInterface(String address, Class clazz) {
        // 解决一下依赖冲突
        Properties props = System.getProperties();
        props.setProperty("org.apache.cxf.stax.allowInsecureParser", "1");
        props.setProperty("UseSunHttpHandler", "true");
        // 创建代理工厂
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        // 设置接口的类型
        jaxWsProxyFactoryBean.setServiceClass(clazz);
        // 设置WebService的地址
        jaxWsProxyFactoryBean.setAddress(address);
        // 返回接口对象
        return jaxWsProxyFactoryBean.create();
    }

    public static void main(String[] args) {
        String address = "http://172.31.48.32/wfi/OaWfWebService.asmx?wsdl";
        OaWfWebServiceSoap oaWfWebServiceSoap = (OaWfWebServiceSoap) CxfUtils.createInterface(address, OaWfWebServiceSoap.class);
        WFInterfaceEntity entity = new WFInterfaceEntity();
//        entity.setInstanceID("vas120171018abcd");
        entity.setInstanceID("vas12345");
        entity.setStepID("vas12345");
        entity.setProjectCode("VAS");
        entity.setProjectName("出访申请审批系统");
        entity.setInstanceTitle("测试OA接口");
        entity.setFormURL("http://www.baidu.com");
        entity.setStepName("审批");
        entity.setFlowType("NG");
        entity.setImportLevel("0");
        entity.setUserID("015076");
        entity.setUserName("杨振兵");
        entity.setUserDeptName("信息服务部");
        entity.setCreatID("015073");
        entity.setCreatName("李程鹏");
        entity.setCreatDeptName("信息服务部");
//        entity.setCreatPhone("15680069632");
        entity.setCreatTime("2017-10-17 15:12:20");
        String s = oaWfWebServiceSoap.insertNewWFInstanceByEntity("VAS", "VAS", "1234", entity);
        System.out.println(s);
    }
}