package remzen.zentrack.models.employees.controller.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class EmployeeWithAttendancesDTO {
    private String department;
    private String employeeId;
    private String name;
    private List<AttendanceDetailDTO> attendances;
}