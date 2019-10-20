package application.service;

import application.domain.Person;
import application.exception.PersonNotFoundException;
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
    public void getPerson() throws PersonNotFoundException {
        //given
        Person person1 = new Person("Michal", "Nowak",
                LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska",
                LocalDate.of(1988, 2, 2), "545655443");
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
    public void savePerson() throws PersonNotFoundException {
        //given
        Person person1 = new Person("Michal", "Nowak",
                LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska",
                LocalDate.of(1988, 2, 2), "545655443");

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
    public void getAllPersonsSorted() throws PersonNotFoundException {
        //given
        Person person1 = new Person("Michal", "Nowak",
                LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska",
                LocalDate.of(2015, 2, 2), "545655443");
        Person person3 = new Person("Zosia", "Koska",
                LocalDate.of(2000, 2, 2), "545655555");

        //when
        int beforeSave = personService.getAllPersonsSorted().size();
        long personId1 = personService.savePerson(person1).getId();
        long personId2 = personService.savePerson(person2).getId();
        long personId3 = personService.savePerson(person3).getId();
        int afterSave = personService.getAllPersonsSorted().size();

        //then
        Assert.assertEquals(0, beforeSave);
        Assert.assertEquals(3, afterSave);
        Assert.assertEquals("Michal", personService.getAllPersonsSorted().get(0).getFirstName());
        Assert.assertEquals("Zosia", personService.getAllPersonsSorted().get(1).getFirstName());
        Assert.assertEquals("Ewa", personService.getAllPersonsSorted().get(2).getFirstName());

        //cleanup
        personService.deletePerson(personId1);
        personService.deletePerson(personId2);
        personService.deletePerson(personId3);
    }

    @Test
    public void testFindByPhoneNo() throws PersonNotFoundException {
        //given
        Person person1 = new Person("Michal", "Nowak",
                LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska",
                LocalDate.of(1988, 2, 2), "545655443");
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
    public void testFindByContainsLastName() throws PersonNotFoundException {
        //given
        Person person1 = new Person("Michal", "Nowak",
                LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska",
                LocalDate.of(1988, 2, 2), "545655443");
        Person person3 = new Person("Marek", "Nowakowski",
                LocalDate.of(1987, 2, 2), "545655454");
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

    @Test
    public void testGetOldestPerson() throws PersonNotFoundException {
        //given
        Person person1 = new Person("Michal", "Nowak",
                LocalDate.of(2015, 1, 1), "123456789");
        Person person2 = new Person("Ewa", "Koska",
                LocalDate.of(1823, 2, 2), "545655443");
        Person person3 = new Person("Zosia", "Koska",
                LocalDate.of(2000, 2, 2), "545655555");

        //when
        long personId1 = personService.savePerson(person1).getId();
        long personId2 = personService.savePerson(person2).getId();
        long personId3 = personService.savePerson(person3).getId();
        Optional<Person> resultPerson = Optional.ofNullable(personService.getOldestPersons());

        //then
        Assert.assertEquals("Ewa", resultPerson.get().getFirstName());

        //cleanup
        personService.deletePerson(personId1);
        personService.deletePerson(personId2);
        personService.deletePerson(personId3);
    }

    @Test
    public void testGetAllPersonsSortedPaged() throws PersonNotFoundException {
        //given
        Person person1 = new Person("1", "1",
                LocalDate.of(1200, 1, 1), "111111111");
        Person person2 = new Person("2", "2",
                LocalDate.of(1400, 2, 2), "222222222");
        Person person3 = new Person("3", "3",
                LocalDate.of(1600, 3, 3), "333333333");
        Person person4 = new Person("4", "4",
                LocalDate.of(1300, 4, 4), "444444444");
        Person person5 = new Person("5", "5",
                LocalDate.of(1500, 5, 5), "555555555");
        Person person6 = new Person("6", "6",
                LocalDate.of(1700, 6, 6), "666666666");
        Person person7 = new Person("7", "7",
                LocalDate.of(1900, 7, 7), "777777777");
        Person person8 = new Person("8", "8",
                LocalDate.of(1800, 8, 8), "888888888");
        Person person9 = new Person("9", "9",
                LocalDate.of(1100, 9, 9), "999999999");


        //when
        long personId1 = personService.savePerson(person1).getId();
        long personId2 = personService.savePerson(person2).getId();
        long personId3 = personService.savePerson(person3).getId();
        long personId4 = personService.savePerson(person4).getId();
        long personId5 = personService.savePerson(person5).getId();
        long personId6 = personService.savePerson(person6).getId();
        long personId7 = personService.savePerson(person7).getId();
        long personId8 = personService.savePerson(person8).getId();
        long personId9 = personService.savePerson(person9).getId();
        List<Person> listPage1 = personService.getAllPersonsSortedPaged(1);
        List<Person> listPage2 = personService.getAllPersonsSortedPaged(2);
        List<Person> listPage3 = personService.getAllPersonsSortedPaged(3);

        //then
        Assert.assertEquals(5, listPage1.size());
        Assert.assertEquals(4, listPage2.size());
        Assert.assertEquals(0, listPage3.size());
        Assert.assertEquals("9", listPage1.get(0).getFirstName());
        Assert.assertEquals("5", listPage1.get(4).getFirstName());
        Assert.assertEquals("3", listPage2.get(0).getFirstName());
        Assert.assertEquals("7", listPage2.get(3).getFirstName());

        //cleanup
        personService.deletePerson(personId1);
        personService.deletePerson(personId2);
        personService.deletePerson(personId3);
        personService.deletePerson(personId4);
        personService.deletePerson(personId5);
        personService.deletePerson(personId6);
        personService.deletePerson(personId7);
        personService.deletePerson(personId8);
        personService.deletePerson(personId9);
    }
}