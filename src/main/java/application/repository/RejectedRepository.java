package application.repository;

import application.domain.Rejected;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RejectedRepository extends CrudRepository<Rejected, Long> {
    @Override
    Rejected save(Rejected rejected);

    @Override
    List<Rejected> findAll();

    @Override
    Optional<Rejected> findById(Long id);

    List<Rejected> findByFileName(String fileName);
}
