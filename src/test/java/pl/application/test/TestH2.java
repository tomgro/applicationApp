/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.application.test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.application.spring.dao.PersonDAO;
import pl.application.spring.model.Person;

/**
 *
 * @author tomek
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class TestH2 implements ApplicationContextAware {
    
    @Autowired
ApplicationContext context;
//     @Test
//    public void test2(){
//
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("appPersistenceUnit");
//        EntityManager theManager = factory.createEntityManager();
//        assertNotNull(theManager);
//
//        theManager.getTransaction().begin();
//        Person person = new Person();
//        person.setFirstName("ana");
//        theManager.persist(person);
//        theManager.getTransaction().commit();
//        
//        System.out.println(person.getId());
//
//        Person p = (Person)theManager.find(Person.class, 1);
//        System.out.println(person.getId());
//
//        assertNotNull(p);
//    }
   // @Test
    public void test3() {
 
 //       ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
         
        PersonDAO personDAO = context.getBean(PersonDAO.class);
         
        Person person = new Person();
        person.setFirstName("Pankaj"); 
         
        personDAO.save(person);
         
        System.out.println("Person::"+person);
         
        List<Person> list = personDAO.list();
         
        for(Person p : list){
            System.out.println("Person List::"+p);
        }
        //close resources
       //context.close();    
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.context = ac;
    }
}
