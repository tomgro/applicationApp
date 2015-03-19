package pl.application.core;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import pl.application.enums.ApplicationState;
import pl.application.spring.model.AppHistory;
import pl.application.spring.model.AppStates;
import pl.application.spring.model.Application;
import pl.application.spring.service.ApplicationService;

@ManagedBean(name = "app")
@ViewScoped
public class ApplicationBean {

    @ManagedProperty(value = "#{ApplicationService}")
    ApplicationService applicationService;

    private String name;
    private String content;
    private String state;
    private String reason;

    AppHistory selectedApp;

    boolean newApp = true;

    private List<AppHistory> appHistory;
    
    // table
    List<AppHistory> applications;

    private List<AppHistory> filteredApps;

    @PostConstruct
    public void init() {
        applications = applicationService.getAppHistoryDao().getLastModified();
    }

    public void save() {
        if (newApp) {
            Application application = new Application();
            application.setName(name);
            application.setContent(content);
            applicationService.addApplication(application);
        } else {
            Application selApp = new Application();
            selApp.setId(selectedApp.getApplicationId().getId());
            selApp.setName(selectedApp.getApplicationId().getName());
            selApp.setContent(content);
            applicationService.updateApplication(selApp, reason, content, ApplicationState.valueOf(state));
        }
        newApplication();
        this.init();
    }

    public void newApplication() {
        newApp = true;
        setName("");
        setContent("");
        setReason("");
        setState(ApplicationState.CREATED.name());
        setAppHistory(new ArrayList<AppHistory>());
    }

    public void onRowSelect(SelectEvent event) {
        this.newApp = false;
        setName(selectedApp.getApplicationId().getName());
        setContent(selectedApp.getApplicationId().getContent());
        setState(selectedApp.getStateId().getStateName());
        setReason(selectedApp.getReason());
        setAppHistory(applicationService.getAppHistoryDao().getApplicationHistory(selectedApp.getApplicationId().getId()));
    }

    public List<String> getStates() {
        List<String> states = new ArrayList<String>();
        List<AppStates> appStates = applicationService.getAppStatesDao().list();
        for (AppStates state : appStates) {
            states.add(state.getStateName());
        }
        return states;
    }

    public List<String> getPossibleStates() {
        List<String> states = new ArrayList<String>();
        if (this.newApp) {
            states.add(ApplicationState.CREATED.name());
        } else {
            String currState = selectedApp.getStateId().getStateName();
            if (currState.equals(ApplicationState.CREATED.name())) {
                states.add(ApplicationState.VERIFIED.name());
                states.add(ApplicationState.DELETED.name());
            }

            if (currState.equals(ApplicationState.VERIFIED.name())) {
                states.add(ApplicationState.ACCEPTED.name());
                states.add(ApplicationState.REJECTED.name());
            }

            if (currState.equals(ApplicationState.ACCEPTED.name())) {
                states.add(ApplicationState.PUBLISHED.name());
                states.add(ApplicationState.REJECTED.name());
            }
            if (currState.equals(ApplicationState.PUBLISHED.name())) {
                states.add(ApplicationState.PUBLISHED.name());
            }

            if (currState.equals(ApplicationState.DELETED.name())) {
                states.add(ApplicationState.DELETED.name());
            }

            if (currState.equals(ApplicationState.REJECTED.name())) {
                states.add(ApplicationState.REJECTED.name());
            }
        }
        return states;
    }

    public AppHistory getSelectedApp() {
        return selectedApp;
    }

    public void setSelectedApp(AppHistory selectedApp) {
        this.selectedApp = selectedApp;
    }

    public List<AppHistory> getAppHistory() {
        return appHistory;
    }

    public void setAppHistory(List<AppHistory> appHistory) {
        this.appHistory = appHistory;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isNewApp() {
        return newApp;
    }

    public void setNewApp(boolean newApp) {
        this.newApp = newApp;
    }

    public List<AppHistory> getApplications() {
        return applications;
    }

    public List<AppHistory> getFilteredApps() {
        return filteredApps;
    }

    public void setFilteredApps(List<AppHistory> filteredApps) {
        this.filteredApps = filteredApps;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ApplicationService getApplicationService() {
        return applicationService;
    }

    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

}
