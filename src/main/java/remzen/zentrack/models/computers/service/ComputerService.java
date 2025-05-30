package remzen.zentrack.models.computers.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import remzen.zentrack.models.computers.controller.dto.ComputerRequest;
import remzen.zentrack.models.computers.model.BeanComputer;
import remzen.zentrack.models.computers.model.EArea;
import remzen.zentrack.models.computers.model.IComputer;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComputerService {

    private final IComputer computerRepository;

    public List<BeanComputer> getAllComputers() {
        return computerRepository.findAll();
    }

    public BeanComputer getComputerById(Long id) {
        return computerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Computadora no encontrada con id: " + id));
    }

    public BeanComputer createComputer(ComputerRequest request) {
        Optional<BeanComputer> existingComputer =  computerRepository.findByCode(request.getCode());
        if(existingComputer.isPresent()) {
            throw new IllegalArgumentException("Ya existe una computadora con el código: " + request.getCode());
        }

        BeanComputer computer = new BeanComputer();
        computer.setCode(request.getCode());
        computer.setType(request.getType());
        computer.setState(request.getState());
        computer.setArea(request.getArea());
        computer.setDescription(request.getDescription());


        return computerRepository.save(computer);
    }

    public BeanComputer updateComputer(Long id, ComputerRequest request) {
        BeanComputer computer = computerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Computadora no encontrada"));

        computer.setCode(request.getCode());
        computer.setType(request.getType());
        computer.setState(request.getState());
        computer.setArea(request.getArea());
        computer.setDescription(request.getDescription());
        computer.setGridRow(request.getGridRow());
        computer.setGridCol(request.getGridCol());

        return computerRepository.save(computer);
    }

    public void deleteComputer(Long id) {
        BeanComputer computer = getComputerById(id);
        computerRepository.delete(computer);
    }

    public BeanComputer changeComputerArea(Long id, EArea newArea) {
        BeanComputer computer = getComputerById(id);
        computer.setArea(newArea);
        return computerRepository.save(computer);
    }
}