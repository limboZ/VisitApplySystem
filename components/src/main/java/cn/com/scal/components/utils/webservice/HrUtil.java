package cn.com.scal.components.utils.webservice;


import cn.com.scal.components.domain.CurrentUser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.util.List;

/**
 * Created by sking0089 on 15/2/2.
 */
public class HrUtil {


    private static final String endPoint = "http://192.1.1.244/Scal.HR2009.WebService/HR2009.asmx?wsdl";


    private static final String localPart = "ScalGetStaffInfo";

    private static final String namespaceURI = "http://tempuri.org/";

    private static final String parentNode = "ScalGetStaffInfoResult";


    public CurrentUser getStaffInfo(String empNo) throws DocumentException, IOException {
        Axis2WebServiceInvoker invoker = new Axis2WebServiceInvoker();

        String template = "<Root><CName>ERVLEIKU</CName><Cmd>ScalGetStaffInfo</Cmd><EmpNo>%s</EmpNo></Root>";
        String command = String.format(template, empNo);
        Object[] opArgs = new Object[]{new String[]{"command", command}};
        String re = invoker.axis2RPCInvoke(endPoint, localPart, opArgs,
                namespaceURI, parentNode);
        return analysisResult(re);

    }

    private CurrentUser analysisResult(String result) throws DocumentException {
        CurrentUser re = new CurrentUser();
        Document doc = DocumentHelper.parseText(result);
        List<Element> errEles = doc.selectNodes("Root/Error/Code");
        if (errEles.size() > 0) {
            Element errEle = errEles.get(0);
            if (errEle.getText().equals("0")) {


                List<Element> eles = doc.selectNodes("Root/Return/Result/StaffInfo");
                if (eles.size() > 0) {
                    Element ele = eles.get(0);

                    re.setEmpNo(((Element) ele.selectNodes("EmpNo").get(0)).getText());
                    re.setUserName(((Element) ele.selectNodes("Name").get(0)).getText());
                    re.setOrgNo(((Element) ele.selectNodes("OrgNo").get(0)).getText());

                }
            }

        }
        return re;
    }

    public static void main(String[] args) throws IOException, DocumentException {
        HrUtil util = new HrUtil();
        CurrentUser user = util.getStaffInfo("006365");

    }
}
