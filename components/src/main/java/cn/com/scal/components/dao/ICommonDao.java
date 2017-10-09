package cn.com.scal.components.dao;

import cn.com.scal.components.command.BaseCommand;
import cn.com.scal.components.domain.Base;
import cn.com.scal.components.dto.BaseDTO;
import cn.com.scal.components.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by com.vslimit on 15-1-15.
 */
@Repository
public interface ICommonDao<T extends Base<T>,K extends BaseDTO<K,T>, PK extends Serializable> {

    PK create(T o) throws DAOException;

    T load(Class<T> clazz, PK id) throws DAOException;

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    List<T> all(Class<T> clazz) throws DAOException;

    void createOrUpdate(T o) throws DAOException;

    void update(T o) throws DAOException;

    void delete(T o) throws DAOException;

    List<T> query(BaseCommand<T,K> command) throws DAOException;

    long count(BaseCommand<T,K> command);

    public List<T> queryByHql(String hql, BaseCommand<T,K> command) throws DAOException;

    public long countByHql(String hql, BaseCommand<T,K> command) throws DAOException;

    public List<K> queryDTO(String sql, BaseCommand<T,K> command, Class<K> clazz) throws DAOException;

    public List<T> queryBySql(String sql, BaseCommand<T,K> command) throws DAOException;

    public K loadDTO(String sql, BaseCommand<T,K> command, Class<K> clazz) throws DAOException;
}
