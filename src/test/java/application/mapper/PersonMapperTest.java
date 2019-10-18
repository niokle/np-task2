package application.mapper;

import application.domain.Person;
import application.dto.PersonDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonMapperTest {

    @Autowired
    PersonMapper personMapper;

    @Test
    public void personToPersonDto() {
        //given
        Person person = new Person(1L,"Michal", "Nowak", LocalDate.of(1999, 1, 1), "123456789");

        //when
        PersonDto personDto = personMapper.personToPersonDto(person);

        //then
        Assert.assertEquals(1L, personDto.getId());
        Assert.assertEquals("Michal", personDto.getFirstName());
        Assert.assertEquals("Nowak", personDto.getLastName());
        Assert.assertEquals(person.getAgeYears(), personDto.getAge());
        Assert.assertEquals("123456789", personDto.getPhoneNo());
    }

    @Test
    public void personsToPersonsDtos() {
        //given
        Person person1 = new Person(1L, "Michal", "Nowak", LocalDate.of(1999, 1, 1), "123456789");
        Person person2 = new Person(2L, "Zosia", "Mak", LocalDate.of(2000, 2, 2), "987654321");
        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);

        //when
        List<PersonDto> personsDtos = personMapper.personsToPersonsDtos(persons);

        //then
        Assert.assertEquals(2, personsDtos.size());
    }
}