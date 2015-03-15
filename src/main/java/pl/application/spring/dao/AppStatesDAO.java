package pl.application.spring.dao;

/**
 *
 * @author tomek
 */
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.application.comm.ApplicationState;
import pl.application.spring.model.AppStates;
import pl.application.spring.model.Application;
 
@Repository
public interface AppStatesDAO {
 
    public void save(AppStates appStates);
    
    public AppStates getAppStateByName(ApplicationState applicationState);
     
    public List<AppStates> list();
     
}