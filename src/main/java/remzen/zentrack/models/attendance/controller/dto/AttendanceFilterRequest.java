package remzen.zentrack.models.attendance.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceFilterRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private String department;
    private String employeeName;
}