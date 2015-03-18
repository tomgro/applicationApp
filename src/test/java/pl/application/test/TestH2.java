/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.application.test;

import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import pl.application.spring.dao.ApplicationDAO;
import pl.application.spring.model.Application;

/**
 *
 * @author tomek
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class TestH2 implements ApplicationContextAware {
    
    @Autowired
ApplicationContext context;

   // @Test
    public void test3() {
 
        ApplicationDAO
                applicationDAO = context.getBean(ApplicationDAO.class);
         
        Application application = new Application();
        application.setName("name");
        application.setContent("content");
         
        applicationDAO.save(application);
         
        List<Application> list = applicationDAO.list();
         
        for(Application a : list){
            System.out.println("Application List::"+ a);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.context = ac;
    }
}
