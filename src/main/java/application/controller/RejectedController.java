package application.controller;

import application.domain.Rejected;
import application.service.RejectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("v1/rejected/")
public class RejectedController {
    private RejectedService rejectedService;

    @Autowired
    public RejectedController(RejectedService rejectedService) {
        this.rejectedService = rejectedService;
    }

    @GetMapping("list")
    public List<Rejected> getAllRejected() {
        return rejectedService.getAllRejected();
    }

    @GetMapping("id/{id}")
    public Optional<Rejected> getRejected(@PathVariable Long id) {
        return rejectedService.getRejected(id);
    }

    @DeleteMapping("all")
    public void deleteAllRejected() {
        rejectedService.deleteAllRejected();
    }

    @DeleteMapping("id/{id}")
    public void deleteRejected(@PathVariable Long id) {
        rejectedService.deleteRejected(id);
    }
}
