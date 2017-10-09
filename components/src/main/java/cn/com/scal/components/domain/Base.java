package cn.com.scal.components.domain;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by com.vslimit on 15/2/2.
 */
public class Base<T> {

    public T transDomain(String ret) {
        return JSON.parseObject(ret, obtainClazz());
    }

    public List<T> transListDomain(String ret) {
        return JSON.parseArray(ret, obtainClazz());
    }

    @SuppressWarnings("unchecked")
    public Class<T> obtainClazz() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }


}
