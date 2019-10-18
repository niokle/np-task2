package application.service;

import application.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.repository.PersonRepository;

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

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public List<Person> getPersonsByPhoneNo(String phoneNo) {
        return personRepository.findByPhoneNo(phoneNo);
    }

    public List<Person> getPersonsByContainsLastName(String lastName) {
        return personRepository.findByLastNameContains(lastName);
    }
}
