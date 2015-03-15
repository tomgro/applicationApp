/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.application.spring.dao;

/**
 *
 * @author tomek
 */
import java.util.List;
import pl.application.spring.model.Person;
 
public interface PersonDAO {
 
    public void save(Person p);
     
    public List<Person> list();
     
}