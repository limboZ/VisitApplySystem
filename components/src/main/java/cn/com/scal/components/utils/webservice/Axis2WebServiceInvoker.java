package cn.com.scal.components.utils.webservice;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import java.io.IOException;
import java.util.Iterator;

public class Axis2WebServiceInvoker extends WebServiceInvoker {


    /**
     * RPC调用AXIS2 webservice
     *
     * @param endpoint     服务地址 如：http://192.168.0.1:2597/aixs2/services/jqservice?wsdl
     * @param localPart    方法名 如<xs:element name="Receive">
     * @param opArgs       方法参数 如Object[] opArgs = new Object[] { param };
     * @param namespaceURI 命名空间 如 ：targetNamespace="http://server.test.com.cn">
     */
    public String axis2RPCInvoke(String endpoint, String localPart,
                                 Object[] opArgs, String namespaceURI, String parentNode) throws IOException {
        String url = endpoint;

        Options options = new Options();
        // 指定调用WebService的URL
        EndpointReference targetEPR = new EndpointReference(url);
        options.setTo(targetEPR);

        //options.setAction("http://tempuri.org/ValidateUserRight");
        // options.setAction("urn:getPrice");

        ServiceClient sender = new ServiceClient();
        sender.setOptions(options);


        OMFactory fac = OMAbstractFactory.getOMFactory();
        String tns = namespaceURI;
        // 命名空间，有时命名空间不增加没事，不过最好加上，因为有时有事，你懂的
        OMNamespace omNs = fac.createOMNamespace(tns, "");

        OMElement method = fac.createOMElement(localPart, omNs);

        for (Object arr : opArgs) {
            String[] args = (String[]) arr;
            OMElement symbol = fac.createOMElement(args[0], omNs);
            symbol.addChild(fac.createOMText(symbol, args[1]));
            method.addChild(symbol);
        }

        method.build();

        OMElement result = sender.sendReceive(method);

        return getNodeList(result, parentNode);

    }

    /**
     * RPC调用AXIS2 webservice
     *
     * @param endpoint     服务地址 如：http://192.168.0.1:2597/aixs2/services/jqservice?wsdl
     * @param localPart    方法名 如<xs:element name="Receive">
     * @param opArgs       方法参数 如Object[] opArgs = new Object[] { param };
     * @param namespaceURI 命名空间 如 ：targetNamespace="http://server.test.com.cn">
     */
    public String axis2RPCInvokeWithActionUrl(String endpoint, String localPart,
                                              Object[] opArgs, String namespaceURI, String parentNode) throws IOException {
        String url = endpoint;

        Options options = new Options();
        // 指定调用WebService的URL
        EndpointReference targetEPR = new EndpointReference(url);
        options.setTo(targetEPR);

        options.setAction(namespaceURI + localPart);
        // options.setAction("urn:getPrice");

        ServiceClient sender = new ServiceClient();
        sender.setOptions(options);


        OMFactory fac = OMAbstractFactory.getOMFactory();
        String tns = namespaceURI;
        // 命名空间，有时命名空间不增加没事，不过最好加上，因为有时有事，你懂的
        OMNamespace omNs = fac.createOMNamespace(tns, "");

        OMElement method = fac.createOMElement(localPart, omNs);

        for (Object arr : opArgs) {
            String[] args = (String[]) arr;
            OMElement symbol = fac.createOMElement(args[0], omNs);
            symbol.addChild(fac.createOMText(symbol, args[1]));
            method.addChild(symbol);
        }

        method.build();

        OMElement result = sender.sendReceive(method);

        return getNodeList(result, parentNode);

    }

    /**
     * 遍历当前父节点下的所有子节点
     *
     * @param element    OMElement 对象
     * @param parentNode 父节点
     * @return List
     */
    public static String getNodeList(OMElement element, String parentNode) throws IOException {
        if (element == null || parentNode == null) {
            return null;
        }

        Iterator<OMElement> iter = element.getChildElements();
        while (iter.hasNext()) {
            OMElement node = iter.next();
            if (node.getLocalName().equals(parentNode)) {
                return node.getText();
            }
        }

        return "";
    }

    public static void main(String[] args) throws IOException {
        Axis2WebServiceInvoker invoker = new Axis2WebServiceInvoker();
        String endpoint = "http://172.18.8.168:10000/SINGPOINT/services/ValidEffectUser?wsdl";
        String localPart = "validatorLegalUser";
        Object[] opArgs = new Object[]{};
        String namespaceURI = "http://172.18.8.168:10000/SINGPOINT/services/ValidEffectUser";
//
        Object re = invoker.axis2RPCInvoke(endpoint, localPart, opArgs,
                namespaceURI, "validatorLegalUserReturn");
        System.out.print(re);

//        Axis2WebServiceInvoker invoker = new Axis2WebServiceInvoker();
//        String endpoint = "http://192.1.1.249/Scal.SMS2009.WebService/SMS2009.asmx";
//        String localPart = "ExecuteCommand";
//
//        String template = "<Root><Command>SendSingle</Command><LoginName>%s</LoginName><LoginPassword>%s</LoginPassword><TaskName></TaskName><TaskCategory>%s</TaskCategory><Tel>%s</Tel><SentTime>%s</SentTime><Interval>%d</Interval><Content><![CDATA[%s]]></Content><ReturnURL></ReturnURL><IsAntiDisturb>%d</IsAntiDisturb></Root>";
//        String msg = String.format(template, "xxcc", "xxcc", "内部运行通知", "13438454331", DTFormatUtil.SDF_ALL.format(new Date()), 30, "测试短信", -1);
//        System.out.print(msg);
//        Object[] opArgs = new Object[]{new String[]{"xmlCommand", msg}};
//        String namespaceURI = "http://tempuri.org/";
//        Object re = invoker.axis2RPCInvoke(endpoint, localPart, opArgs,
//                namespaceURI);
//        System.out.print(re);

//        Axis2WebServiceInvoker invoker = new Axis2WebServiceInvoker();
//        String endpoint = "http://192.1.1.244/Scal.Brms.WebService/Brms.asmx?wsdl";
//        String localPart = "ValidateUserRight";
//
//
//        String template = "<Root><CName>%s</CName><Cmd>ValidateUserRight</Cmd><LoginName>%s</LoginName>%s</Root>";
//        String rightsTemplate = "<Group Name=\"航班动态查询\"><Right Name=\"航班动态查询\" Values=\"编辑\" /></Group>";
//        String msg = String.format(template, "scal.outsideSchedule", "007931", rightsTemplate);
//        System.out.print(msg);
//        Object[] opArgs = new Object[]{new String[]{"command", msg}};
//        String namespaceURI = "http://tempuri.org/";
//        Object re = invoker.axis2RPCInvokeWithActionUrl(endpoint, localPart, opArgs,
//                namespaceURI, "ValidateUserRightResult");
//        System.out.print(re);

//        Axis2WebServiceInvoker invoker = new Axis2WebServiceInvoker();
//        String endpoint = "http://192.1.1.244/Scal.HR2009.WebService/HR2009.asmx?wsdl";
//        String localPart = "ScalGetStaffInfo";


//        String template = "<Root><CName>ERVLEIKU</CName><Cmd>ScalGetStaffInfo</Cmd><EmpNo>%s</EmpNo></Root>";
//        String msg = String.format(template, "007129");
//        System.out.print(msg);
//        Object[] opArgs = new Object[]{new String[]{"command", msg}};
//        String namespaceURI = "http://tempuri.org/";
//        Object re = invoker.axis2RPCInvoke(endpoint, localPart, opArgs,
//                namespaceURI, "ScalGetStaffInfoResult");
//        System.out.print(re);
//        Axis2WebServiceInvoker invoker = new Axis2WebServiceInvoker();
//        String endpoint = "http://192.1.1.244/Scal.HR2009.WebService/HR2009.asmx?wsdl";
//        String localPart = "ScalGetOrgInfo";
//
//
//        String template = "<Root>\n" +
//                "<CName>ERVLEIKU</CName>\n" +
//                "<Cmd>ScalGetOrgInfo</Cmd>\n" +
//                "<Type>0</Type>\n" +
//                "</Root>\n";
//        String msg = String.format(template);
//        System.out.print(msg);
//        Object[] opArgs = new Object[]{new String[]{"command", msg}};
//        String namespaceURI = "http://tempuri.org/";
//        Object re = invoker.axis2RPCInvoke(endpoint, localPart, opArgs,
//                namespaceURI, "ScalGetOrgInfoResult");
//        System.out.print(re);
//
//        String a = "abcdefg";
//        System.out.print(a.substring(0,4));

    }


}
