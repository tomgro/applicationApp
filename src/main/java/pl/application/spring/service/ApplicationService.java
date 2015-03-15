/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.application.spring.service;

import java.io.Serializable;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.application.comm.ApplicationState;
import pl.application.spring.dao.AppHistoryDAO;
import pl.application.spring.dao.AppStatesDAO;
import pl.application.spring.dao.ApplicationDAO;
import pl.application.spring.model.AppHistory;
import pl.application.spring.model.AppStates;
import pl.application.spring.model.Application;

/**
 *
 * @author tomek
 */
@Service("ApplicationService")
@Transactional
public class ApplicationService implements Serializable {
    
    @Autowired
    private ApplicationDAO applicationDao;
    
    @Autowired
    private AppHistoryDAO appHistoryDao;
    
    @Autowired
    private AppStatesDAO appStatesDao;

    @Transactional(readOnly = false)
    public void addApplication(Application application) {
        getApplicationDao().save(application);
        AppStates createdState = getAppStatesDao().getAppStateByName(ApplicationState.CREATED);
        AppHistory appHistory = new AppHistory();
        appHistory.setStateId(createdState);
        appHistory.setModDate(new Date());
        appHistory.setApplicationId(application);
        getAppHistoryDao().save(appHistory);
    }
    
    
    
    public ApplicationDAO getApplicationDao() {
        return applicationDao;
    }

    public void setApplicationDao(ApplicationDAO applicationDao) {
        this.applicationDao = applicationDao;
    }

    public AppHistoryDAO getAppHistoryDao() {
        return appHistoryDao;
    }

    public void setAppHistoryDao(AppHistoryDAO appHistoryDao) {
        this.appHistoryDao = appHistoryDao;
    }

    public AppStatesDAO getAppStatesDao() {
        return appStatesDao;
    }

    public void setAppStatesDao(AppStatesDAO appStatesDao) {
        this.appStatesDao = appStatesDao;
    }
    
    
}
