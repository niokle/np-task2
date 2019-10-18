package application.controller;

import application.dto.RejectedDto;
import application.exception.RejectedNotFoundException;
import application.mapper.RejectedMapper;
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
    private RejectedMapper rejectedMapper;

    @Autowired
    public RejectedController(RejectedService rejectedService, RejectedMapper rejectedMapper) {
        this.rejectedService = rejectedService;
        this.rejectedMapper = rejectedMapper;
    }

    @GetMapping("list")
    public List<RejectedDto> getAllRejected() {
        return rejectedMapper.rejectedsToRejectedsDtos(rejectedService.getAllRejected());
    }

    @GetMapping("id/{id}")
    public RejectedDto getRejected(@PathVariable Long id) throws RejectedNotFoundException {
        return rejectedMapper.rejectedToRejectedDto(rejectedService.getRejected(id).orElseThrow(RejectedNotFoundException::new));
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
