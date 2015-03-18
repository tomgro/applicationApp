package pl.application.spring.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import pl.application.spring.model.Application;

public class ApplicationDAOImpl implements ApplicationDAO, Serializable {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer save(Application application) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(application);
        tx.commit();
        Integer id = application.getId();
        session.close();
        return id;
    }

    @Override
    public Application update(Application application) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(application);
        tx.commit();
        session.close();
        return null;
    }

    @Override
    public List<Application> list() {
        Session session = this.sessionFactory.openSession();
        List<Application> applications = session.createQuery("from Application").list();
        session.close();
        return applications;
    }
}
