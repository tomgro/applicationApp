
package pl.application.test;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.application.core.ApplicationBean;
import pl.application.enums.ApplicationState;
import pl.application.spring.dao.AppStatesDAO;
import pl.application.spring.model.AppHistory;
import pl.application.spring.model.AppStates;
import pl.application.spring.service.ApplicationService;

/**
 *
 * @author tomek
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class ApplicationTest {
    
    private ApplicationBean applicationBean = new ApplicationBean();
    
    ApplicationService applicationService;
    
    @Autowired
    AppStatesDAO appStatesDao;
    
    AppHistory selectedApp;
    AppStates appStates = new AppStates(2, "CREATED");
    
    @Before
    public void setup() {
        applicationService = mock(ApplicationService.class);
        selectedApp = mock(AppHistory.class);
        applicationBean.setApplicationService(applicationService);
        applicationBean.setSelectedApp(selectedApp);
        selectedApp.setStateId(appStates);
    }
    
    @Test
    public void shouldCountStates() {
        // given
        
        when(applicationService.getAppStatesDao()).thenReturn(appStatesDao);
        // when
        List<String> states = applicationBean.getStates();
        // then
        Assert.assertEquals(6, states.size());
    }
    
    @Test
    public void shouldReturnPossibleStates() {
        // given
        when(selectedApp.getStateId()).thenReturn(appStates);
        applicationBean.setNewApp(false);
        // when
        List<String> states = applicationBean.getPossibleStates();
        // then
        Assert.assertEquals(2, states.size());
        Assert.assertEquals(ApplicationState.VERIFIED.name(), states.get(0));
    }
}
