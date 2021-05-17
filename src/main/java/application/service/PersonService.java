package application.service;

import application.domain.Person;
import application.exception.PersonNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.repository.PersonRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private PersonRepository personRepository;

    private static Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> getPerson(Long id) throws PersonNotFoundException {
        try {
            Optional<Person> personResult = personRepository.findById(id);
            LOGGER.info("pobrano rekord, id: " + personResult.get().getId());
            return personResult;
        } catch (Exception ex) {
            LOGGER.error("brak rekordu w bazie, id: " + id);
            throw new PersonNotFoundException("brak rekordu w bazie, id: " + id);
        }
    }

    public Person savePerson(Person person) {
        Person personSaved = personRepository.save(person);
        LOGGER.info("zapisano rekord, id:" + personSaved.getId());
        return personSaved;
    }

    public List<Person> getAllPersonsSorted() {
        LOGGER.info("pobieranie wszystkich rekordów posortowanych po wieku");
        List<Person> persons = personRepository.findAll();
        persons.sort(Comparator.comparing(Person::getAgeDays).reversed());
        return persons;
    }

    public List<Person> getAllPersonsSortedPaged(int page) {
        List<Person> allPersonsSorted = getAllPersonsSorted();
        List<Person> personsList = new ArrayList<>();
        int recordsPerPage = 5;
        int lastRecordNo = page * recordsPerPage;
        int firstRecordNo = lastRecordNo - recordsPerPage + 1;
        if (lastRecordNo > allPersonsSorted.size()) {
            lastRecordNo = allPersonsSorted.size();
        }

        if (firstRecordNo <= lastRecordNo) {
            for (int i = firstRecordNo - 1; i < lastRecordNo; i++) {
                personsList.add(allPersonsSorted.get(i));
            }
            LOGGER.info("pobieranie wszystkich rekordów posortowanych po wieku ze strony " + page
                    + " (" + recordsPerPage + " rekordów na strone)");
        } else {
            LOGGER.info("nie ma rekordów na stronie " + page
                    + " (" + recordsPerPage + " rekordów na strone)");
        }
        return personsList;
    }

    public void deletePerson(Long id) throws PersonNotFoundException {
        try {
            personRepository.deleteById(id);
            LOGGER.info("usnięto rekord, id: " + id);
        } catch (Exception ex) {
            LOGGER.error("nie można usunąć, brak rekordu w bazie, id: " + id);
            throw new PersonNotFoundException("nie można usunąć, brak rekordu w bazie, id: " + id);
        }
    }

    public List<Person> getPersonsByPhoneNo(String phoneNo) {
        LOGGER.info("pobieranie rekordów po numerze telefonu");
        return personRepository.findByPhoneNo(phoneNo);
    }

    public List<Person> getPersonsByContainsLastName(String lastName) {
        LOGGER.info("pobieranie rekordów po nazwisku");
        return personRepository.findByLastNameContains(lastName);
    }

    public void deleteAllPersons() {
        LOGGER.info("usuwanie wszystkich rekordów");
        personRepository.deleteAll();
    }

    public Person getOldestPersons() throws PersonNotFoundException {
        LOGGER.info("pobieranie rekordu z najstarszą osobą");
        try {
            return personRepository.findAll().stream()
                    .max(Comparator.comparing(Person::getAgeDays)).orElseThrow(Exception::new);
        } catch (Exception ex) {
            throw new PersonNotFoundException("brak rekordów w bazie");
        }
    }
}
