/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.application.test;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.application.spring.dao.ApplicationDAO;
import pl.application.spring.dao.ApplicationDAOImpl;
import pl.application.spring.model.Application;

/**
 *
 * @author tomek
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:dao-test.xml"})
public class TestH2 {
    
    @Autowired
    ApplicationDAO applicationDao;

  //  @Test
    public void test3() {
 
//        ApplicationDAO
//                applicationDAO = context.getBean("applicationDao", ApplicationDAOImpl.class);
         
        Application application = new Application();
        application.setName("name");
        application.setContent("content");
         
        applicationDao.save(application);
         
        List<Application> list = applicationDao.list();
         
        for(Application a : list){
            System.out.println("Application List::"+ a);
        }
    }

    
    
//    @Override
//    public void setApplicationContext(ApplicationContext ac) throws BeansException {
//        this.context = ac;
//    }

    public void setApplicationDao(ApplicationDAO applicationDao) {
        this.applicationDao = applicationDao;
    }
}
