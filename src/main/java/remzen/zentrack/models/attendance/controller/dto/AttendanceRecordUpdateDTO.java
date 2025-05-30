package remzen.zentrack.models.attendance.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import remzen.zentrack.models.attendance.model.AttendanceStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecordUpdateDTO {
    private LocalDate date;
    private AttendanceStatus status;
}
