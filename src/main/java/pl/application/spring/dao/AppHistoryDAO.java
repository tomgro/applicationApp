package pl.application.spring.dao;

/**
 *
 * @author tomek
 */
import java.util.List;
import org.springframework.stereotype.Repository;
import pl.application.spring.model.AppHistory;
import pl.application.spring.model.Application;
 
@Repository
public interface AppHistoryDAO {
 
    void save(AppHistory appHistory);
     
    List<AppHistory> list();
    
    List<AppHistory> getLastModified();
    
    List<AppHistory> getApplicationHistory(Application application);
    
    List<AppHistory> getApplicationHistory(Integer applicationId);
     
}