package remzen.zentrack.models.attendance.controller.dto;

import lombok.Builder;
import lombok.Data;
import remzen.zentrack.models.attendance.model.AttendanceStatus;

import java.time.LocalDate;

@Data
@Builder
public class DayRecord {
    private LocalDate date;
    private String dayName;
    private String formattedDate;
    private AttendanceStatus status;
}
