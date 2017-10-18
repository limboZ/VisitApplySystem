package cn.com.scal.components.dao.impl;

import cn.com.scal.components.command.BaseCommand;
import cn.com.scal.components.dao.ICommonDao;
import cn.com.scal.components.domain.Base;
import cn.com.scal.components.dto.BaseDTO;
import cn.com.scal.components.exception.DAOException;
import cn.com.scal.components.utils.StringUtil;
import org.hibernate.*;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by com.vslimit on 15-1-15.
 */
@SuppressWarnings("unchecked")
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class CommonDaoImpl<T extends Base<T>, K extends BaseDTO<K, T>, PK extends Serializable> implements ICommonDao<T, K, PK> {

    private SessionFactory sessionFactory;

    @Autowired
    public CommonDaoImpl(@Qualifier("sessionFactory") SessionFactory factory) {
        this.sessionFactory = factory;
    }

    @Override
    public PK create(T o) throws DAOException {
        return (PK) getCurrentSession().save(o);
    }

    @Override
    public T load(Class<T> clazz, PK id) throws DAOException {
        T value = (T) getCurrentSession().get(clazz, id);
        if (null == value) {
            return null;
        }
        if (value instanceof HibernateProxy) {
            Hibernate.initialize(value);
            value = (T) ((HibernateProxy) value).getHibernateLazyInitializer().getImplementation();
        }
        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> all(Class<T> clazz) throws DAOException {
        Criteria crit = getCurrentSession().createCriteria(clazz);
        return crit.list();
    }

    @Override
    public void createOrUpdate(T o) throws DAOException {
        getCurrentSession().saveOrUpdate(o);
    }


    @Override
    public void update(T o) throws DAOException {
        getCurrentSession().update(o);
    }

    @Override
    public void delete(T o) throws DAOException {
        getCurrentSession().delete(o);
    }

    protected String from(String tableName) {
        return "from " + tableName;
    }

    protected String count(String tableName) {
        return "select count(*) " + from(tableName);
    }

    @Override
    public List<T> query(BaseCommand<T,K> command) throws DAOException {
        Query query = queryByCommand(from(command.getClazzName()), command);
        return query.list();
    }

    private Query queryByCommand(String hql, BaseCommand<T,K> command) {
        StringBuilder hqlBuilder = new StringBuilder(hql);
        String conditions = command.conditions();
        String orders = command.getOrderBy();
        Map<String, Object> map = command.getQueryMap();
        int page = command.getPage();
        int pageSize = command.getPageSize();
        if (!StringUtil.isEmpty(conditions)) {
            hqlBuilder.append(" where 1 = 1 ").append(conditions);
        }
        if (!StringUtil.isEmpty(orders)) {
            hqlBuilder.append(" order by ").append(orders);
        }
        Query query = getCurrentSession().createQuery(hqlBuilder.toString());
        for (String key : map.keySet()) {
            query.setParameter(key, map.get(key));
        }
        if (page > 0 && pageSize > 0) {
            int firstResult = (page - 1) * pageSize;
            query.setFirstResult(firstResult);
            query.setMaxResults(pageSize);
        }
        return query;
    }



    @Override
    public long count(BaseCommand<T,K> command) {
        return countByHql(count(command.getClazzName()), command);
    }


    @Override
    public List<T> queryByHql(String hql, BaseCommand<T,K> command) throws DAOException {
        Query query = queryByCommand(hql, command);
        return query.list();
    }

    @Override
    public long countByHql(String hql, BaseCommand<T,K> command) throws DAOException {
        Query query = getCurrentSession().createQuery(String.format("%s where 1=1 %s", hql, command.conditions()));
        Map<String, Object> map = command.getQueryMap();
        for (String key : map.keySet()) {
            query.setParameter(key, map.get(key));
        }
        return (Long) query.uniqueResult();
    }

    @Override
    public List<K> queryDTO(String sql, BaseCommand<T,K> command, Class<K> clazz) throws DAOException {
        String joinSql = sql.toLowerCase().contains("where") ? "%s and 1=1 %s":"%s where 1=1 %s";
        Query query = getCurrentSession().createSQLQuery(String.format(joinSql, sql, command.conditions())).setResultTransformer(Transformers.aliasToBean(clazz));
        Map<String, Object> map = command.getQueryMap();
        for (String key : map.keySet()) {
            query.setParameter(key, map.get(key));
        }
        int page = command.getPage();
        int pageSize = command.getPageSize();

        if (page > 0 && pageSize > 0) {
            int firstResult = (page - 1) * pageSize;
            query.setFirstResult(firstResult);
            query.setMaxResults(pageSize);
        }
        return query.list();
    }

    @Override
    public List<T> queryBySql(String sql, BaseCommand<T,K> command) throws DAOException {
        String joinSql = sql.contains("where") ? "%s and 1=1 %s":"%s where 1=1 %s";
        Query query = getCurrentSession().createSQLQuery(String.format(joinSql, sql, command.conditions()));
        Map<String, Object> map = command.getQueryMap();
        for (String key : map.keySet()) {
            query.setParameter(key, map.get(key));
        }
        return query.list();
    }


    @Override
    public K loadDTO(String sql, BaseCommand<T,K> command, Class<K> clazz) throws DAOException {
        String joinSql = sql.contains("where") ? "%s and 1=1 %s":"%s where 1=1 %s";
        Query query = getCurrentSession().createSQLQuery(String.format(joinSql, sql, command.conditions())).setResultTransformer(Transformers.aliasToBean(clazz));
        Map<String, Object> map = command.getQueryMap();
        for (String key : map.keySet()) {
            query.setParameter(key, map.get(key));
        }
        return (K) query.uniqueResult();
    }

    @Override
    public List<T> findFlexible(String conditions, String orders, int page, int pageSize, Class<T> clazz) throws DAOException {
        StringBuilder hqlBuilder = new StringBuilder(from(clazz.getName()));
        if (conditions != null && !conditions.isEmpty()) {
            hqlBuilder.append(" where 1 = 1 ").append(conditions);
        }
        if (orders != null && !orders.isEmpty()) {
            hqlBuilder.append(" order by ").append(orders);
        }
        Query query = getCurrentSession().createQuery(hqlBuilder.toString());
        if (page > 0 && pageSize > 0) {
            int firstResult = (page - 1) * pageSize;
            query.setFirstResult(firstResult);
            query.setMaxResults(pageSize);
        }
        return query.list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


}
