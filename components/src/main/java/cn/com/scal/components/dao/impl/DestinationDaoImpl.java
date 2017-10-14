package cn.com.scal.components.dao.impl;

import cn.com.scal.components.dao.IDestinationDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("destinationDao")
public class DestinationDaoImpl implements IDestinationDao {
    private SessionFactory sessionFactory;

    @Autowired
    public DestinationDaoImpl(@Qualifier("sessionFactory") SessionFactory factory) {
        this.sessionFactory = factory;
    }

    @Override
    public void delete(String sql) {
        Query query = getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
