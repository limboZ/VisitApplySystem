package cn.com.scal.components.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private static Logger logger = LoggerFactory.getLogger(StringUtil.class.getName());

    public static boolean isEmpty(String val) {
        return (null == val) || ("".equals(val)) || ("null".equals(val))
                || "".equals(val.trim());
    }

    public static boolean isEmptyOrZero(String val) {
        return (null == val) || ("".equals(val)) || ("null".equals(val) || "0".equals(val) || "0-0".equals(val))
                || "".equals(val.trim());
    }

    public static String fillStr(int length, char defaultChar) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(defaultChar);
        }
        return sb.toString();
    }

    public static String replaceString(String target, int index, char re) {
        StringBuilder sb = new StringBuilder();
        if (isEmpty(target)) {
            for (int i = 0; i < 4; i++) {
                if (i == index) {
                    sb.append(re);
                } else {
                    sb.append("0");
                }
            }
        } else {
            char[] charArr = target.toCharArray();
            for (int i = 0; i < charArr.length; i++) {
                if (i == index) {
                    sb.append(re);
                } else {
                    sb.append(charArr[i]);
                }
            }
        }
        return sb.toString();
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String arrayToString(String[] array, String chr) {
        if (null == array || array.length <= 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (String s : array) {
            sb.append(s);
            sb.append(chr);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public static String sortString(String str) {
        if (isEmpty(str)) {
            return "";
        }
        str = str.toLowerCase();
        StringBuffer buff = new StringBuffer(str);
        char[] arr = str.toCharArray();
        Arrays.sort(arr);

        for (int i = 0, j = 0; i < arr.length; i++) {
            if (String.valueOf(arr[i]).matches("[a-zA-Z]")) {
                while (true) {
                    if (String.valueOf(buff.charAt(j)).matches("[a-zA-Z]")) {
                        buff.setCharAt(j, arr[i]);
                        j++;
                        break;
                    }
                    j++;
                }
            }
        }
        return buff.toString();
    }

    public static String trans(String value, String from, String to) {
        if (!isEmpty(value)) {
            try {
                value = new String(value.getBytes(from), to);
            } catch (UnsupportedEncodingException e) {
                logger.error(e.toString());
            }
        }
        return value;

    }

    public static Map queryStringToMap(String queryString) {
        if (isEmpty(queryString)) {
            return null;
        }

        Map map = new HashMap();
        String[] array = queryString.split("&");
        for (String s : array) {
            if (s.contains("=")) {
                map.put(s.split("=")[0], s.split("=").length >= 2 ? s.split("=")[1] : "");
            }
        }
        return map;
    }

    public static String decoderUrl(String value) {
        if (!isEmpty(value)) {
            try {
                value = URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error(e.toString());
            }
        }
        return value;
    }

    public static String urlDecoder(String u) {
        try {
            u = (URLDecoder.decode(u, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.toString());
        }
        return u;
    }

    public static String transactSQLInjection(String sql) {
        return sql.replaceAll(".*([';]+|(--)+).*", " ");
    }

    public static String md5(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.toString());
        }
        StringBuilder sb = new StringBuilder();

        if (md != null) {
            md.update(str.getBytes());
            byte[] digest = md.digest();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchAlgorithmException {
//        int x = 182;
//        int a,c;
//        c = x / 100;
//        a = x;
//        System.out.println(a);
//        System.out.println(c);
//        System.out.println(DigestUtils.md5Digest("012200".getBytes()));
//        ;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update("123456a".getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        System.out.println("original:" + "123456a");
        System.out.println("digested(hex):" + sb.toString());


    }
}
