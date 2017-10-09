package cn.com.scal.components.utils.webservice;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sking0089 on 15/2/2.
 */
public class AuthSysUtil {


    private static final String endPoint = "http://192.1.1.244/Scal.Brms.WebService/Brms.asmx?wsdl";

    private static final String cname = "scal.outsideSchedule";

    private static final String localPart = "ValidateUserRight";

    private static final String template = "<Root><CName>%s</CName><Cmd>ValidateUserRight</Cmd><LoginName>%s</LoginName>%s</Root>";

    private static final String rightsTemplate = "<Group Name=\"%s\"><Right Name=\"%s\" Values=\"%s\" /></Group>";

    private static final String namespaceURI = "http://tempuri.org/";

    private static final String parentNode = "ValidateUserRightResult";


    public Boolean validHasAuth(String empNo) throws DocumentException, IOException {
        try {


            Axis2WebServiceInvoker invoker = new Axis2WebServiceInvoker();

            String localPart = "GetUserInfo";
            String checkAuthTemplate = "<Root><CName>%s</CName><Cmd>GetUserInfo</Cmd><LoginName>%s</LoginName></Root>";

            String parentNode = "GetUserInfoResult";

            String command = String.format(checkAuthTemplate, cname, empNo);
            Object[] opArgs = new Object[]{new String[]{"command", command}};
            String re = invoker.axis2RPCInvokeWithActionUrl(endPoint, localPart, opArgs,
                    namespaceURI, parentNode);

            List<Boolean> status = analysisResult(re);

            if (status.size() == 0) {
                return false;
            }

            if (status.get(0)) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable ex) {
            return false;
        }

    }

    public Boolean validFlightSchedule(String empNo) throws DocumentException, IOException {


        try {

            Axis2WebServiceInvoker invoker = new Axis2WebServiceInvoker();

            String right = String.format(rightsTemplate, "航班动态查询", "航班动态查询", "编辑");
            String command = String.format(template, cname, empNo, right);
            Object[] opArgs = new Object[]{new String[]{"command", command}};
            String re = invoker.axis2RPCInvokeWithActionUrl(endPoint, localPart, opArgs,
                    namespaceURI, parentNode);

            List<Boolean> status = analysisResult(re);

            if (status.size() == 0) {
                return false;
            }

            if (status.get(0)) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable ex) {
            return false;
        }
    }

    public Boolean validAdmin(String empNo) throws DocumentException, IOException {
        try {
            Axis2WebServiceInvoker invoker = new Axis2WebServiceInvoker();

            String right = String.format(rightsTemplate, "航班动态查询", "航班动态查询", "全部");
            String command = String.format(template, cname, empNo, right);
            Object[] opArgs = new Object[]{new String[]{"command", command}};
            String re = invoker.axis2RPCInvokeWithActionUrl(endPoint, localPart, opArgs,
                    namespaceURI, parentNode);

            List<Boolean> status = analysisResult(re);

            if (status.size() == 0) {
                return false;
            }
            if (status.get(0)) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable ex) {
            return false;
        }
    }

    public Boolean validPersonPlan(String empNo) throws DocumentException, IOException {
        try {
            Axis2WebServiceInvoker invoker = new Axis2WebServiceInvoker();

            String right = String.format(rightsTemplate, "人员计划列表", "人员计划列表", "编辑");
            String command = String.format(template, cname, empNo, right);
            Object[] opArgs = new Object[]{new String[]{"command", command}};
            String re = invoker.axis2RPCInvokeWithActionUrl(endPoint, localPart, opArgs,
                    namespaceURI, parentNode);

            List<Boolean> status = analysisResult(re);

            if (status.size() == 0) {
                return false;
            }

            if (status.get(0)) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable ex) {
            return false;
        }
    }


    public Boolean validStationStatistics(String empNo) throws DocumentException, IOException {
        try {
            Axis2WebServiceInvoker invoker = new Axis2WebServiceInvoker();

            String right = String.format(rightsTemplate, "驻站统计表", "驻站统计表", "编辑");
            String command = String.format(template, cname, empNo, right);
            Object[] opArgs = new Object[]{new String[]{"command", command}};
            String re = invoker.axis2RPCInvokeWithActionUrl(endPoint, localPart, opArgs,
                    namespaceURI, parentNode);

            List<Boolean> status = analysisResult(re);

            if (status.size() == 0) {
                return false;
            }

            if (status.get(0)) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable ex) {
            return false;
        }
    }


    List<Boolean> analysisResult(String result) throws DocumentException {
        List<Boolean> re = new ArrayList<Boolean>();
        Document doc = DocumentHelper.parseText(result);
        List<Element> errEles = doc.selectNodes("Root/Error/Code");
        if (errEles.size() > 0) {
            Element errEle = errEles.get(0);
            if (errEle.getText().equals("0")) {
                List<Element> eles = doc.selectNodes("Root/Return/Group");
                if (eles.size() > 0) {
                    Element ele = eles.get(0);

                    String statusStr = ((DefaultAttribute) ele.selectNodes("Right/@Status").get(0)).getStringValue();

                    String[] statusArr = statusStr.split(",");

                    for (String st : statusArr) {
                        re.add(new Boolean(st));
                    }


                }
            }

        }
        return re;
    }

    public static void main(String[] args) throws IOException, DocumentException {

        AuthSysUtil util = new AuthSysUtil();
        boolean re = util.validFlightSchedule("006365");

    }
}
