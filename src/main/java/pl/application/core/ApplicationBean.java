package pl.application.core;

import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import pl.application.spring.dao.ApplicationDAO;
import pl.application.spring.model.AppHistory;
import pl.application.spring.model.Application;
import pl.application.spring.service.ApplicationService;

@ManagedBean(name = "app")
@SessionScoped
public class ApplicationBean {

    @ManagedProperty(value="#{ApplicationService}")
    ApplicationService applicationService;
    
    private String name;
    private String content;
    
    AppHistory selectedApp;
    
    private List<AppHistory> appHistory;

    
    
    public void save() {
        Application application = new Application();
        application.setName(name);
        application.setContent(content);
        applicationService.addApplication(application);
        setName("");
        setContent("");
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

    public void onRowSelect(SelectEvent event) {
        AppHistory selected = (AppHistory) event.getObject();
        FacesMessage msg = new FacesMessage("Application selected", selected.getApplicationId().getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        setName(selected.getApplicationId().getName());
        setContent(selected.getApplicationId().getContent());
        //setAppHistory(applicationService.getAppHistoryDao().getApplicationHistory(selected.getApplicationId()));
    }
 
    
    
    public void onRowUnselect(UnselectEvent event) {
        AppHistory selected = (AppHistory) event.getObject();
        FacesMessage msg = new FacesMessage("Application unselected", selected.getApplicationId().getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void history() {
        System.out.println("history");
        Map<String,String> params = 
		FacesContext.getCurrentInstance().
                        getExternalContext().getRequestParameterMap();
 
	String histId = params.get("selectedRow");
        setAppHistory(applicationService.getAppHistoryDao().getApplicationHistory(10));
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
}