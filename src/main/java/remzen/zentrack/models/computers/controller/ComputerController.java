package remzen.zentrack.models.computers.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import remzen.zentrack.models.computers.controller.dto.ComputerRequest;
import remzen.zentrack.models.computers.model.BeanComputer;
import remzen.zentrack.models.computers.model.EArea;
import remzen.zentrack.models.computers.service.ComputerService;

import java.util.List;

@RestController
@RequestMapping("/auth/zentrack/computers")
@CrossOrigin(origins = {"http://localhost:8081", "http://192.168.1.88:8081"})
@Validated
public class ComputerController {

    private final ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping("/list") // Cambiado de PostMapping a GetMapping
    public ResponseEntity<List<BeanComputer>> getAllComputers() {
        return ResponseEntity.ok(computerService.getAllComputers());
    }

    @PostMapping("/{id}")
    public ResponseEntity<BeanComputer> getComputerById(@PathVariable Long id) {
        return ResponseEntity.ok(computerService.getComputerById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<BeanComputer> createComputer(@Valid @RequestBody ComputerRequest request) {
        return ResponseEntity.ok(computerService.createComputer(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BeanComputer> updateComputer(
            @PathVariable Long id,
            @Valid @RequestBody ComputerRequest request) {
        return ResponseEntity.ok(computerService.updateComputer(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComputer(@PathVariable Long id) {
        computerService.deleteComputer(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/change-area/{id}")
    public ResponseEntity<BeanComputer> changeComputerArea(
            @PathVariable Long id,
            @RequestParam EArea newArea) {
        return ResponseEntity.ok(computerService.changeComputerArea(id, newArea));
    }
}