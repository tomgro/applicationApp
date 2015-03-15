package pl.application.spring.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import pl.application.comm.ApplicationState;
import pl.application.spring.model.AppStates;
import pl.application.spring.model.Application;

public class AppStatesDAOImpl implements AppStatesDAO, Serializable {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(AppStates appStates) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(appStates);
        tx.commit();
        session.close();
    }

    @Override
    public AppStates getAppStateByName(ApplicationState applicationState) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.getNamedQuery("AppStates.findByStateName")
                .setString("stateName", applicationState.getName());
        List<AppStates> appStates = query.list();
        tx.commit();
        session.close();
        if (appStates.size() == 1) {
            return appStates.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<AppStates> list() {
        Session session = this.sessionFactory.openSession();
        List<AppStates> appStates = session.createQuery("from AppStates").list();
        session.close();
        return appStates;
    }

}
