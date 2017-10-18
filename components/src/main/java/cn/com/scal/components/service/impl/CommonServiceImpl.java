package cn.com.scal.components.service.impl;

import cn.com.scal.components.command.BaseCommand;
import cn.com.scal.components.dao.ICommonDao;
import cn.com.scal.components.domain.Base;
import cn.com.scal.components.dto.BaseDTO;
import cn.com.scal.components.exception.DAOException;
import cn.com.scal.components.exception.OtherException;
import cn.com.scal.components.service.ICommonService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by com.vslimit on 15-1-18.
 */
@Transactional
@Service("commonServiceImpl")
public class CommonServiceImpl<T extends Base<T>, K extends BaseDTO<K , T>, PK extends Serializable> implements ICommonService<T , K , PK> {

    @Qualifier("commonDaoImpl")
    @Resource
    protected ICommonDao<T ,K , PK> dao;

    @Override
    public T load(Class<T> clazz, PK id) {
        return dao.load(clazz, id);
    }


    @Override
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> all(Class<T> clazz) {
        return dao.all(clazz);
    }

    @Override
    public PK create(T persistentObject) throws OtherException {
        return dao.create(persistentObject);
    }

    @Override
    public void update(T persistentObject) {
        dao.update(persistentObject);
    }

    @Override
    public void createOrUpdate(T persistentObject) {
        dao.createOrUpdate(persistentObject);
    }

    @Override
    public void delete(T persistentObject) {
        dao.delete(persistentObject);
    }

    @Override
    public List<T> query(BaseCommand<T,K> command) throws OtherException {
        return dao.query(command);
    }

    @Override
    public long count(BaseCommand<T,K> command) {
        return dao.count(command);
    }

    @Override
    public List<T> queryByHql(String hql,BaseCommand<T,K> command) throws OtherException{
        return dao.queryByHql(hql, command);
    }

    @Override
    public long countByHql(String hql,BaseCommand<T,K> command) throws OtherException{
        return dao.countByHql(hql, command);
    }

    @Override
    public List<K> queryDTO(String sql,BaseCommand<T,K> command,Class<K> clazz) throws OtherException{
        return dao.queryDTO(sql, command, clazz);
    }

    @Override
    public K loadDTO(String sql,BaseCommand<T,K> command,Class<K> clazz) throws OtherException{
        return dao.loadDTO(sql, command, clazz);
    }

    @Override
    public List<T> findFlexible(String conditions, String orders, int page, int pageSize, Class<T> clazz) throws DAOException {
        return dao.findFlexible(conditions,orders,page,pageSize,clazz);
    }
}
