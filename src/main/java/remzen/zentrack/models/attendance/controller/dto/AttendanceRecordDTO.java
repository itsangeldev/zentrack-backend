package remzen.zentrack.models.attendance.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import remzen.zentrack.models.attendance.model.AttendanceStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecordDTO {
    private LocalDate date;
    private LocalTime entryTime;
    private LocalTime exitTime;
    private AttendanceStatus status;
    private String notes;
}