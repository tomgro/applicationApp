package pl.application.spring.dao;

/**
 *
 * @author tomek
 */
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.application.spring.model.AppHistory;
import pl.application.spring.model.Application;
 
@Repository
public interface AppHistoryDAO {
 
    public void save(AppHistory appHistory);
     
    public List<AppHistory> list();
    
    public List<AppHistory> getLastModified();
    
    public List<AppHistory> getApplicationHistory(Application application);
    
    public List<AppHistory> getApplicationHistory(Integer applicationId);
     
}