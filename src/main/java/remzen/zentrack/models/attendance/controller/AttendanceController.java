package remzen.zentrack.models.attendance.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import remzen.zentrack.models.attendance.controller.dto.AttendanceUpdateRequest;
import remzen.zentrack.models.attendance.controller.dto.AttendanceUploadRequest;
import remzen.zentrack.models.attendance.service.AttendanceService;
import remzen.zentrack.models.employees.controller.dto.AttendancePaginationRequest;
import remzen.zentrack.models.employees.controller.dto.EmployeeWithAttendancesDTO;

import java.util.List;

@RestController
@RequestMapping("/auth/zentrack/attendance")
@CrossOrigin(origins = {"http://localhost:8081", "http://192.168.1.88:8081"})
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;



    @PostMapping("/upload")
    public ResponseEntity<?> uploadAttendanceData(@Valid @RequestBody AttendanceUploadRequest request) {
        attendanceService.processAttendanceUpload(request);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/employees-with-attendances")
    public ResponseEntity<Page<EmployeeWithAttendancesDTO>> getEmployeesWithAttendances(
            @RequestBody AttendancePaginationRequest request) {

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<EmployeeWithAttendancesDTO> result = attendanceService
                .getEmployeesWithAttendances(pageable);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/attendances-and-employees")
    public ResponseEntity<List<EmployeeWithAttendancesDTO>> getEmployeesWithAttendances() {
        return ResponseEntity.ok(attendanceService.getAllEmployeesWithAttendances());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAttendanceRecords(@Valid @RequestBody AttendanceUpdateRequest request) {
        attendanceService.updateAttendanceRecords(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<?> deleteAllEmployeesAndAttendances() {
        attendanceService.deleteAllEmployeesAndAttendances();
        return ResponseEntity.ok().build();
    }




}