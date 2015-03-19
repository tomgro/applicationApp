/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.application.test;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.application.spring.dao.ApplicationDAO;
import pl.application.spring.model.Application;

/**
 *
 * @author tomek
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class H2ConnectionTest {
    
    @Autowired
    ApplicationDAO applicationDao;

    @Test
    public void shouldReturnSavedApplication() {
        // given
        Application application = new Application();
        application.setName("name");
        application.setContent("content");
         // when
        Integer id = applicationDao.save(application);
        // then
        Application savedApp = applicationDao.getById(id);
        Assert.assertEquals(application, savedApp);
    }

}
