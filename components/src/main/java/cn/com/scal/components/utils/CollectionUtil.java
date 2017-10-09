package cn.com.scal.components.utils;

import java.util.List;

/**
 * Created by vslimit on 15/9/17.
 */
public class CollectionUtil {

    public static boolean blankList(List list) {
       return null == list || list.size() <= 0;
    }

}
