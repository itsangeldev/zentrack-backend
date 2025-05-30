package remzen.zentrack.models.computers.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IComputer extends JpaRepository<BeanComputer, Long> {
    Optional<BeanComputer> findByCode(@NotBlank(message = "El código es requerido") String code);
}