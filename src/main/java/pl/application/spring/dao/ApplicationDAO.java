package pl.application.spring.dao;

/**
 *
 * @author tomek
 */
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.application.spring.model.Application;
 
@Repository
public interface ApplicationDAO {
 
    public Integer save(Application application);
     
    public List<Application> list();
    
    public Application update(Application application);
     
}