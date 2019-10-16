package repository;

import domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    @Override
    Person save(Person person);

    @Override
    List<Person> findAll();

    @Override
    Optional<Person> findById(Long id);
}
