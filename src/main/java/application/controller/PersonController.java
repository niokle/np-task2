package application.controller;

import application.domain.Person;
import application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("v1/person/")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("total")
    public int getTotalPersons() {
        return personService.getAllPersonsSorted().size();
    }

    @GetMapping("list")
    public List<Person> getAllPersonsSorted() {
        return personService.getAllPersonsSorted();
    }

    @GetMapping("lastname/{lastName}")
    public List<Person> getPersonsByContainsLastName(@PathVariable String lastName) {
        return personService.getPersonsByContainsLastName(lastName);
    }

    @DeleteMapping("id/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }

    @DeleteMapping("all")
    public void deleteAllPersons() {
        personService.deleteAllPersons();
    }
}
