package remzen.zentrack.models.computers.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import remzen.zentrack.models.computers.model.EArea;
import remzen.zentrack.models.computers.model.EState;
import remzen.zentrack.models.computers.model.EType;


@Data
public class ComputerRequest {
    @NotBlank(message = "El código es requerido")
    private String code;

    @NotNull(message = "El tipo es requerido")
    private EType type;

    @NotNull(message = "El estado es requerido")
    private EState state;

    @NotNull(message = "El área es requerido")
    private EArea area;

    private Integer gridRow;
    private Integer gridCol;

    private String description;
}
