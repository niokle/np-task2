package application.service;

import application.domain.Rejected;
import application.exception.RejectedNotFoundException;
import application.repository.RejectedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RejectedService {
    private RejectedRepository rejectedRepository;

    private static Logger LOGGER = LoggerFactory.getLogger(RejectedService.class);

    @Autowired
    public RejectedService(RejectedRepository rejectedRepository) {
        this.rejectedRepository = rejectedRepository;
    }

    public Optional<Rejected> getRejected(Long id) throws RejectedNotFoundException {
        try {
            Optional<Rejected> rejectedResult = rejectedRepository.findById(id);
            LOGGER.info("pobrano rekord, id: " + rejectedResult.get().getId());
            return rejectedResult;
        } catch (Exception ex) {
            LOGGER.error("brak rekordu w bazie, id: " + id);
            throw new RejectedNotFoundException("brak rekordu w bazie, id: " + id);
        }
    }

    public Rejected saveRejected(Rejected rejected) {
        Rejected rejectedSaved = rejectedRepository.save(rejected);
        LOGGER.info("zapisano rekord, id:" + rejectedSaved.getId());
        return rejectedSaved;
    }

    public List<Rejected> getAllRejected() {
        LOGGER.info("pobieranie wszystkich rekordów");
        return rejectedRepository.findAll();
    }

    public void deleteRejected(Long id) throws RejectedNotFoundException {
        try {
            rejectedRepository.deleteById(id);
            LOGGER.info("usnięto rekord, id: " + id);
        } catch (Exception ex) {
            LOGGER.error("nie można usunąć, brak rekordu w bazie, id: " + id);
            throw new RejectedNotFoundException("nie można usunąć, brak rekordu w bazie, id: " + id);
        }
    }

    public void deleteAllRejected() {
        LOGGER.info("usuwanie wszystkich rekordów");
        rejectedRepository.deleteAll();
    }

    public void deleteRejectedByFileName(String fileName) {
        LOGGER.info("kasowanie rekordów pobranych z pliku, fileName: " + fileName);
        List<Rejected> rejecteds = rejectedRepository.findByFileName(fileName);
        rejecteds.stream()
                .forEach(rejected -> rejectedRepository.deleteById(rejected.getId()));
    }
}
