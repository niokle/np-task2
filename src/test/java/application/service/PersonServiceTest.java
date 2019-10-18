package application.service;

import application.domain.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        long personId1 = personService.savePerson(person1).getId();
        long personId2 = personService.savePerson(person2).getId();

        //when
        Optional<Person> resultPerson = personService.getPerson(personId1);

        //then
        Assert.assertEquals("Michal", resultPerson.get().getFirstName());
        Assert.assertEquals("Nowak", resultPerson.get().getLastName());
        Assert.assertEquals(LocalDate.of(1999, 1, 1), resultPerson.get().getBirthDate());
        Assert.assertEquals("123456789", resultPerson.get().getPhoneNo());

        //cleanup
        personService.deletePerson(personId1);
        personService.deletePerson(personId2);
    }

    @Test
    public void savePerson() {
        //given
        Person person1 = new Person("Michal", "Nowak", LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska", LocalDate.of(1988, 2, 2), "545655443");

        //when
        Person resultPerson1 = personService.savePerson(person1);
        Person resultPerson2 = personService.savePerson(person2);

        //then
        Assert.assertEquals("Michal", resultPerson1.getFirstName());
        Assert.assertEquals("Nowak", resultPerson1.getLastName());
        Assert.assertEquals(LocalDate.of(1999, 1, 1), resultPerson1.getBirthDate());
        Assert.assertEquals("123456789", resultPerson1.getPhoneNo());
        Assert.assertEquals("Ewa", resultPerson2.getFirstName());
        Assert.assertEquals("Koska", resultPerson2.getLastName());
        Assert.assertEquals(LocalDate.of(1988, 2, 2), resultPerson2.getBirthDate());
        Assert.assertEquals("545655443", resultPerson2.getPhoneNo());

        //cleanup
        personService.deletePerson(resultPerson1.getId());
        personService.deletePerson(resultPerson2.getId());
    }

    @Test
    public void getAllPersons() {
        //given
        Person person1 = new Person("Michal", "Nowak", LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska", LocalDate.of(1988, 2, 2), "545655443");

        //when
        int beforeSave = personService.getAllPersons().size();
        long personId1 = personService.savePerson(person1).getId();
        long personId2 = personService.savePerson(person2).getId();
        int afterSave = personService.getAllPersons().size();

        //then
        Assert.assertEquals(0, beforeSave);
        Assert.assertEquals(2, afterSave);

        //cleanup
        personService.deletePerson(personId1);
        personService.deletePerson(personId2);
    }

    @Test
    public void testFindByPhoneNo() {
        //given
        Person person1 = new Person("Michal", "Nowak", LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska", LocalDate.of(1988, 2, 2), "545655443");
        long personId1 = personService.savePerson(person1).getId();
        long personId2 = personService.savePerson(person2).getId();

        //when
        List<Person> resultPersons = personService.getPersonsByPhoneNo("123456789");

        //then
        Assert.assertEquals(1, resultPersons.size());

        //cleanup
        personService.deletePerson(personId1);
        personService.deletePerson(personId2);
    }

    @Test
    public void testFindByContainsLastName() {
        //given
        Person person1 = new Person("Michal", "Nowak", LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska", LocalDate.of(1988, 2, 2), "545655443");
        Person person3 = new Person("Marek", "Nowakowski", LocalDate.of(1987, 2, 2), "545655454");
        long personId1 = personService.savePerson(person1).getId();
        long personId2 = personService.savePerson(person2).getId();
        long personId3 = personService.savePerson(person3).getId();

        //when
        List<Person> resultPersons = personService.getPersonsByContainsLastName("Nowak");

        //then
        Assert.assertEquals(2, resultPersons.size());

        //cleanup
        personService.deletePerson(personId1);
        personService.deletePerson(personId2);
        personService.deletePerson(personId3);
    }
}