package remzen.zentrack.models.employees.controller.dto;

import lombok.Data;

@Data
public class AttendancePaginationRequest {
    private int page = 0;
    private int size = 10;
}