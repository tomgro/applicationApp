package pl.application.spring.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import pl.application.spring.model.AppHistory;
import pl.application.spring.model.Application;

public class AppHistoryDAOImpl implements AppHistoryDAO, Serializable {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(AppHistory appHistory) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(appHistory);
        session.flush();
        tx.commit();
        session.close();
    }

    @Override
    public List<AppHistory> list() {
        Session session = this.sessionFactory.openSession();
        List<AppHistory> appHistory = session.createQuery("from AppHistory").list();
        session.close();
        return appHistory;
    }

    @Override
    public List<AppHistory> getApplicationHistory(Application application) {
        Session session = this.sessionFactory.openSession();
        List<AppHistory> appHistory = session.createQuery("from AppHistory ah where ah.applicationId = :applicationId order by mod_date desc").
                setParameter("applicationId", application).list();
        session.close();
        return appHistory;
    }

    @Override
    public List<AppHistory> getApplicationHistory(Integer applicationId) {
        Session session = this.sessionFactory.openSession();
        String query = "select * from APP_HISTORY ah \n"
                + " where ah.application_id = :appId\n"
                + " order by mod_date desc";
        List<AppHistory> appHistory = session.createSQLQuery(query).addEntity(AppHistory.class).setParameter("appId", applicationId).list();
        System.out.println("size: " + appHistory.size());
        session.close();
        return appHistory;
    }

    @Override
    public List<AppHistory> getLastModified() {
        String query = "select * from APP_HISTORY ah \n"
                + " where mod_date = (select max(mod_date) from APP_HISTORY c where c.application_id = ah.application_id)\n"
                + " order by mod_date desc";

        Session session = this.sessionFactory.openSession();
        List<AppHistory> appHistory = session.createSQLQuery(query).addEntity(AppHistory.class).list();
        session.close();

        return appHistory;
    }

}
