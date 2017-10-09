package cn.com.scal.components.service;

import cn.com.scal.components.command.BaseCommand;
import cn.com.scal.components.domain.Base;
import cn.com.scal.components.dto.BaseDTO;
import cn.com.scal.components.exception.OtherException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by com.vslimit on 15-1-18.
 */
public interface ICommonService<T extends Base<T>,K extends BaseDTO<K,T>,PK extends Serializable> {
    T load(Class<T> clazz, PK id);

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    List<T> all(Class<T> clazz);

    PK create(T persistentObject) throws OtherException;

    void update(T persistentObject);

    void createOrUpdate(T persistentObject);

    void delete(T persistentObject);

    public List<T> query(BaseCommand<T,K> command) throws OtherException;

    long count(BaseCommand<T,K> command);

    public List<T> queryByHql(String hql, BaseCommand<T,K> command) throws OtherException;

    public long countByHql(String hql, BaseCommand<T,K> command) throws OtherException;

    public List<K> queryDTO(String sql, BaseCommand<T,K> command, Class<K> clazz) throws OtherException;

    public K loadDTO(String sql, BaseCommand<T,K> command, Class<K> clazz) throws OtherException;
}