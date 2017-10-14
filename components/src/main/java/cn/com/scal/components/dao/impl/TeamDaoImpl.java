package cn.com.scal.components.dao.impl;

import cn.com.scal.components.dao.ITeamDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("teamDao")
public class TeamDaoImpl implements ITeamDao {
    private SessionFactory sessionFactory;

    @Autowired
    public TeamDaoImpl(@Qualifier("sessionFactory") SessionFactory factory) {
        this.sessionFactory = factory;
    }

    @Override
    public void delete(String sql) {
        Query query = getCurrentSession().createSQLQuery(sql);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
