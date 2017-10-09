package cn.com.scal.components.dto;

import cn.com.scal.components.domain.Base;
import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;

/**
 * Created by com.vslimit on 15/3/13.
 */
@SuppressWarnings("unchecked")
public class BaseDTO<T extends BaseDTO, K extends Base> {

    private Class<T> clazz;
    private Class<K> clz;

    private String objectId;
    private String objectName;

    public BaseDTO() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        this.clz = (Class<K>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T toDTO(K k) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(k, clazz);
    }

    public K toDomain(T t) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(t, clz);
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

}
