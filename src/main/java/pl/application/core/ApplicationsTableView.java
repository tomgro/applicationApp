/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.application.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import pl.application.comm.ApplicationState;
import pl.application.spring.model.AppHistory;
import pl.application.spring.model.AppStates;
import pl.application.spring.model.Application;
import pl.application.spring.service.ApplicationService;
import pl.application.comm.ApplicationState;

/**
 *
 * @author tomek
 */
@ManagedBean(name = "appTable")
@ViewScoped
public class ApplicationsTableView implements Serializable{
    
    List<AppHistory> applications;
    
    private List<AppHistory> filteredApps;
    
    @ManagedProperty(value="#{ApplicationService}")
    ApplicationService applicationService;
     
    @PostConstruct
    public void init() {
        applications = applicationService.getAppHistoryDao().getLastModified();
                // applicationService.getAppHistoryDao().list();
    }

    public List<AppHistory> getApplications() {
        return applications;
    }
 
    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    public List<AppHistory> getFilteredApps() {
        return filteredApps;
    }
    
    

    public void setFilteredApps(List<AppHistory> filteredApps) {
        this.filteredApps = filteredApps;
    }
    
   
    
    
}
