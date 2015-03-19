package pl.application.spring.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.application.spring.model.Application;

public class ApplicationDAOImpl implements ApplicationDAO, Serializable {

    Logger log = LoggerFactory.getLogger(ApplicationDAOImpl.class); ;
    
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
        session.flush();
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
        session.flush();
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
    
    @Override
    public Application getById(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<Application> applications = session.getNamedQuery("Application.findById")
                .setInteger("id", id).list();
        session.close();
        if(applications.size() == 1) {
            log.debug("Application id = ", applications.get(0).getId());
            return applications.get(0);
        } else { 
            log.debug("Application not found");
            return null;
        }
    }
}
