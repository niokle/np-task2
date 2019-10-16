package service;

import domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> getPerson(Long id) {
        return personRepository.findById(id);
    }

    public Long savePerson(Person person) {
        return personRepository.save(person).getId();
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
}
