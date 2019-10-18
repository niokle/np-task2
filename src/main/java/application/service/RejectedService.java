package application.service;

import application.domain.Rejected;
import application.repository.RejectedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RejectedService {
    private RejectedRepository rejectedRepository;

    @Autowired
    public RejectedService(RejectedRepository rejectedRepository) {
        this.rejectedRepository = rejectedRepository;
    }

    public Optional<Rejected> getRejected(Long id) {
        return rejectedRepository.findById(id);
    }

    public Rejected saveRejected(Rejected rejected) {
        return rejectedRepository.save(rejected);
    }

    public List<Rejected> getAllRejected() {
        return rejectedRepository.findAll();
    }

    public void deleteRejected(Long id) {
        rejectedRepository.deleteById(id);
    }

    public void deleteAllRejected() {
        rejectedRepository.deleteAll();
    }

    public void deleteRejectedByFileName(String fileName) {
        List<Rejected> rejecteds = rejectedRepository.findByFileName(fileName);
        rejecteds.stream()
                .forEach(rejected -> rejectedRepository.deleteById(rejected.getId()));
    }
}
