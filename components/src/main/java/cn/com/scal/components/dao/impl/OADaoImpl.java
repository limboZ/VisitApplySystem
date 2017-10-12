package cn.com.scal.components.dao.impl;

import cn.com.scal.components.dao.IOADao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("OADao")
@Transactional(propagation = Propagation.MANDATORY)
public class OADaoImpl implements IOADao {
    private SessionFactory sessionFactory;

    @Autowired
    public OADaoImpl(@Qualifier("oaSessionFactory") SessionFactory factory) {
        this.sessionFactory = factory;
    }

    @Override
    public List<Object[]> queryBySql(String sql) {
        Query query = getCurrentSession().createSQLQuery(sql);
        return query.list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
