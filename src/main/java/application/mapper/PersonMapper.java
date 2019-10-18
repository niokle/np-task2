package application.mapper;

import application.domain.Person;
import application.dto.PersonDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonMapper {
    public PersonDto personToPersonDto(Person person) {
        return new PersonDto(person.getId(), person.getFirstName(), person.getLastName(), person.getAgeYears(), person.getPhoneNo());
    }

    public List<PersonDto> personsToPersonsDtos(List<Person> persons) {
        List<PersonDto> personsDtos = new ArrayList<>();
        for (Person person : persons) {
            personsDtos.add(personToPersonDto(person));
        }
        return personsDtos;
    }
}
