package remzen.zentrack.models.attendance.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceUpdateRequest {
    private String employeeId;
    private List<AttendanceRecordUpdateDTO> records;
}

