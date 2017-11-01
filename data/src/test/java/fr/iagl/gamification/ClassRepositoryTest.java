package fr.iagl.gamification;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import fr.iagl.gamification.entity.ClassEntity;
import fr.iagl.gamification.repository.ClassRepository;

//@RunWith(SpringRunner.class)
//@DataJpaTest
//@WebMvcTest(ClassRepository.class)
//@ContextConfiguration
public class ClassRepositoryTest{
  
    @Autowired
    private ClassRepository repository;
    
    @Autowired
    private TestEntityManager entityManager;
 
    @Test
    public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
//    	this.entityManager.persist(new ClassEntity("test"));
//        boolean found = repository.existsByName("test");
//  
//        assertTrue(found);
    }
}
