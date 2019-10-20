package application.controller;

import application.dto.PersonDto;
import application.exception.PersonNotFoundException;
import application.mapper.PersonMapper;
import application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("v1/person/")
public class PersonController {
    private PersonService personService;
    private PersonMapper personMapper;

    @Autowired
    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @GetMapping("total")
    public int getTotalPersons() {
        return personService.getAllPersonsSorted().size();
    }

    @GetMapping("list")
    public List<PersonDto> getAllPersonsSorted() {
        return personMapper.personsToPersonsDtos(personService.getAllPersonsSorted());
    }

    @GetMapping("listpaged")
    public List<PersonDto> getAllPersonsSortedPaged(@RequestParam("page") int page) {
        return personMapper.personsToPersonsDtos(personService.getAllPersonsSortedPaged(page));
    }

    @GetMapping("lastname/{lastName}")
    public List<PersonDto> getPersonsByContainsLastName(@PathVariable String lastName) {
        return personMapper.personsToPersonsDtos(personService.getPersonsByContainsLastName(lastName));
    }

    @DeleteMapping("id/{id}")
    public void deletePerson(@PathVariable Long id) throws PersonNotFoundException {
        personService.deletePerson(id);
    }

    @DeleteMapping("all")
    public void deleteAllPersons() {
        personService.deleteAllPersons();
    }

    @GetMapping("oldest")
    public PersonDto getOldestPerson() throws PersonNotFoundException {
        return personMapper.personToPersonDto(personService.getOldestPersons());
    }
}
