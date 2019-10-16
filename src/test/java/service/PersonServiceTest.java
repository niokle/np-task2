package service;
import domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Test
    public void getPerson() {
        //given
        Person person1 = new Person("Michal", "Nowak", LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska", LocalDate.of(1988, 2, 2), "545655443");
        Long personId1 = personService.savePerson(person1);
        Long personId2 = personService.savePerson(person2);

        //when

        //then

    }

    @Test
    public void savePerson() {
    }

    @Test
    public void getAllPersons() {
    }
}