package remzen.zentrack.models.attendance.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import remzen.zentrack.models.employees.controller.dto.EmployeeDataDTO;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceUploadRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private List<EmployeeDataDTO> employees;
}