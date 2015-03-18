package pl.application.spring.dao;

/**
 *
 * @author tomek
 */
import java.util.List;
import org.springframework.stereotype.Repository;
import pl.application.enums.ApplicationState;
import pl.application.spring.model.AppStates;

@Repository
public interface AppStatesDAO {

    void save(AppStates appStates);

    AppStates getAppStateByName(ApplicationState applicationState);

    List<AppStates> list();
}
