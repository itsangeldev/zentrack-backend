package remzen.zentrack.models.employees.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import remzen.zentrack.models.attendance.controller.dto.AttendanceRecordDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDataDTO {
    private String employeeId;
    private String name;
    private String departmentName;
    private List<AttendanceRecordDTO> records;
}