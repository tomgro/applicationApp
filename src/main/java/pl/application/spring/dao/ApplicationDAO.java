package pl.application.spring.dao;

/**
 *
 * @author tomek
 */
import java.util.List;
import org.springframework.stereotype.Repository;
import pl.application.spring.model.Application;
 
@Repository
public interface ApplicationDAO {
 
    Integer save(Application application);
     
    List<Application> list();
    
    Application update(Application application);
}