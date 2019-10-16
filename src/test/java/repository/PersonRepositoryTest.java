package repository;

import domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void testSave() {
        //given
        Person person1 = new Person("Michal", "Nowak", LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska", LocalDate.of(1988, 2, 2), "545655443");

        //int a = personRepository.findAll().size();
        //System.out.println(a);
        //when

        //then

    }

    @Test
    public void testFindAll() {
    }

    @Test
    public void testFindById() {
    }
}