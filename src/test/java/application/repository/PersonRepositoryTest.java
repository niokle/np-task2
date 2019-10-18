package application.repository;

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
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void testSave() {
        //given
        Person person1 = new Person("Michal", "Nowak", LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska", LocalDate.of(1988, 2, 2), "545655443");

        //when
        Person resultPerson1 = personRepository.save(person1);
        Person resultPerson2 = personRepository.save(person2);

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
        personRepository.delete(resultPerson1);
        personRepository.delete(resultPerson2);
    }

    @Test
    public void testFindAll() {
        //given
        Person person1 = new Person("Michal", "Nowak", LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska", LocalDate.of(1988, 2, 2), "545655443");

        //when
        int beforeSave = personRepository.findAll().size();
        long personId1 = personRepository.save(person1).getId();
        long personId2 = personRepository.save(person2).getId();
        int afterSave = personRepository.findAll().size();

        //then
        Assert.assertEquals(0, beforeSave);
        Assert.assertEquals(2, afterSave);

        //cleanup
        personRepository.deleteById(personId1);
        personRepository.deleteById(personId2);
    }

    @Test
    public void testFindById() {
        //given
        Person person1 = new Person("Michal", "Nowak", LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska", LocalDate.of(1988, 2, 2), "545655443");
        long personId1 = personRepository.save(person1).getId();
        long personId2 = personRepository.save(person2).getId();

        //when
        Optional<Person> resultPerson = personRepository.findById(personId1);

        //then
        Assert.assertEquals("Michal", resultPerson.get().getFirstName());
        Assert.assertEquals("Nowak", resultPerson.get().getLastName());
        Assert.assertEquals(LocalDate.of(1999, 1, 1), resultPerson.get().getBirthDate());
        Assert.assertEquals("123456789", resultPerson.get().getPhoneNo());

        //cleanup
        personRepository.deleteById(personId1);
        personRepository.deleteById(personId2);
    }

    @Test
    public void testFindByPhoneNo() {
        //given
        Person person1 = new Person("Michal", "Nowak", LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska", LocalDate.of(1988, 2, 2), "545655443");
        long personId1 = personRepository.save(person1).getId();
        long personId2 = personRepository.save(person2).getId();

        //when
        List<Person> resultPersons = personRepository.findByPhoneNo("123456789");

        //then
        Assert.assertEquals(1, resultPersons.size());

        //cleanup
        personRepository.deleteById(personId1);
        personRepository.deleteById(personId2);
    }

    @Test
    public void testFindByContainsLastName() {
        //given
        Person person1 = new Person("Michal", "Nowak", LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska", LocalDate.of(1988, 2, 2), "545655443");
        Person person3 = new Person("Marek", "Nowakowski", LocalDate.of(1987, 2, 2), "545655454");
        long personId1 = personRepository.save(person1).getId();
        long personId2 = personRepository.save(person2).getId();
        long personId3 = personRepository.save(person3).getId();

        //when
        List<Person> resultPersons = personRepository.findByLastNameContains("Nowak");

        //then
        Assert.assertEquals(2, resultPersons.size());

        //cleanup
        personRepository.deleteById(personId1);
        personRepository.deleteById(personId2);
        personRepository.deleteById(personId3);
    }
}