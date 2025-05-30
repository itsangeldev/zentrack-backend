package remzen.zentrack.models.employees.controller.dto;

import lombok.Builder;
import lombok.Data;
import remzen.zentrack.models.attendance.model.AttendanceStatus;
import java.time.LocalDate;

@Data
@Builder
public class AttendanceDetailDTO {
    private LocalDate date;
    private AttendanceStatus status;
}