package cn.com.scal.components.utils.webservice;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.util.List;

/**
 * Created by sking0089 on 15/2/2.
 */
public class PortalLoginUtil {

    private static final String endPoint = "http://172.18.8.168:10000/SINGPOINT/services/ValidEffectUser?wsdl";

    private static final String localPart = "validatorLegalUser";

    private static final String namespaceURI = "http://172.18.8.168:10000/SINGPOINT/services/ValidEffectUser";

    private static final String parentNode = "validatorLegalUserReturn";


    public Boolean validLogin(String empNo, String password) throws DocumentException, IOException {
        Axis2WebServiceInvoker invoker = new Axis2WebServiceInvoker();

        Object[] opArgs = new Object[]{new String[]{"empNo", empNo}, new String[]{"password", password}};
        String re = invoker.axis2RPCInvoke(endPoint, localPart, opArgs,
                namespaceURI, parentNode);
        Document doc = DocumentHelper.parseText(re);
        List<Element> eles = doc.selectNodes("root/code");
        if (eles.size() > 0) {
            Element ele = eles.get(0);
            if (ele.getText().equals("0")) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException, DocumentException {
        PortalLoginUtil util  = new PortalLoginUtil();
        boolean re = util.validLogin("012285","111");
        System.out.println(re);

    }
}
