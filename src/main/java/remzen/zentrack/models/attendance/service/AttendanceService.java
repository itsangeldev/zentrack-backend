package remzen.zentrack.models.attendance.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import remzen.zentrack.models.attendance.controller.dto.*;
import remzen.zentrack.models.attendance.model.AttendanceRecord;
import remzen.zentrack.models.attendance.model.AttendanceRecordRepository;
import remzen.zentrack.models.attendance.model.AttendanceStatus;
import remzen.zentrack.models.employees.controller.dto.AttendanceDetailDTO;
import remzen.zentrack.models.employees.controller.dto.EmployeeDataDTO;
import remzen.zentrack.models.employees.controller.dto.EmployeeWithAttendancesDTO;
import remzen.zentrack.models.employees.model.Employee;
import remzen.zentrack.models.employees.model.EmployeeRepository;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static remzen.zentrack.models.attendance.model.AttendanceStatus.ABSENT;
import static remzen.zentrack.models.attendance.model.AttendanceStatus.LATE;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRecordRepository recordRepository;


    public void processAttendanceUpload(AttendanceUploadRequest request) {
        request.getEmployees().forEach(employeeData -> {
            Employee employee = employeeRepository.findByEmployeeId(employeeData.getEmployeeId())
                    .orElseGet(() -> createNewEmployee(employeeData));

            processEmployeeRecords(employee, employeeData.getRecords());
        });
    }

    private Employee createNewEmployee(EmployeeDataDTO dto) {
        Employee newEmployee = new Employee();
        newEmployee.setEmployeeId(dto.getEmployeeId());
        newEmployee.setName(dto.getName());
        newEmployee.setDepartment(dto.getDepartmentName());
        return employeeRepository.save(newEmployee);
    }

    private void processEmployeeRecords(Employee employee, List<AttendanceRecordDTO> records) {
        records.forEach(recordDto -> {
            AttendanceRecord record = recordRepository.findByEmployeeAndDate(employee, recordDto.getDate())
                    .orElse(new AttendanceRecord());

            mapDtoToRecord(recordDto, record);
            record.setEmployee(employee);

            recordRepository.save(record);
        });
    }

    private void mapDtoToRecord(AttendanceRecordDTO dto, AttendanceRecord record) {
        record.setDate(dto.getDate());
        record.setEntryTime(dto.getEntryTime());
        record.setExitTime(dto.getExitTime());
        record.setNotes(dto.getNotes());

        if(dto.getStatus() == null) {
            record.setStatus(ABSENT);
        } else {
            record.setStatus(AttendanceStatus.fromString(dto.getStatus().name()));
        }
    }


    private String formatDate(LocalDate date) {
        String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int day = date.getDayOfMonth();

        String daySuffix;
        if (day >= 11 && day <= 13) {
            daySuffix = "th";
        } else {
            switch (day % 10) {
                case 1:  daySuffix = "st"; break;
                case 2:  daySuffix = "nd"; break;
                case 3:  daySuffix = "rd"; break;
                default: daySuffix = "th"; break;
            }
        }

        return month + " " + day + daySuffix;
    }
    @Transactional
    public void updateAttendanceRecords(AttendanceUpdateRequest request) {
        Employee employee = employeeRepository.findByEmployeeId(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        request.getRecords().forEach(update -> {
            AttendanceRecord record = recordRepository.findByEmployeeAndDate(employee, update.getDate())
                    .orElseGet(() -> {
                        AttendanceRecord newRecord = new AttendanceRecord();
                        newRecord.setEmployee(employee);
                        newRecord.setDate(update.getDate());
                        return newRecord;
                    });

            record.setStatus(update.getStatus());
            recordRepository.save(record);
        });

        applyAutomaticAbsences(employee);
    }

    private void applyAutomaticAbsences(Employee employee) {
        List<AttendanceRecord> lateRecords = recordRepository
                .findByEmployeeAndStatusOrderByDateAsc(employee, LATE);

        if (lateRecords.size() >= 3) {
            AttendanceRecord oldestLate = lateRecords.get(0);
            oldestLate.setStatus(ABSENT);
            recordRepository.save(oldestLate);
        }
    }

    public Page<EmployeeWithAttendancesDTO> getEmployeesWithAttendances(Pageable pageable) {
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        List<Employee> employeesWithAttendances = employeeRepository.findEmployeesWithAttendances(
                employeePage.getContent()
                        .stream()
                        .map(Employee::getId)
                        .collect(Collectors.toList())
        );

        // Convertimos a DTO
        List<EmployeeWithAttendancesDTO> content = employeesWithAttendances.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        // Retornamos la página con la información completa
        return new PageImpl<>(
                content,
                pageable,
                employeePage.getTotalElements()
        );
    }

    private EmployeeWithAttendancesDTO convertToDto(Employee employee) {
        return EmployeeWithAttendancesDTO.builder()
                .department(employee.getDepartment())
                .employeeId(employee.getEmployeeId())
                .name(employee.getName())
                .attendances(employee.getRecords().stream()
                        .map(record -> AttendanceDetailDTO.builder()
                                .date(record.getDate())
                                .status(record.getStatus())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
    @Transactional
    public void deleteAllEmployeesAndAttendances() {
        recordRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    public List<EmployeeWithAttendancesDTO> getAllEmployeesWithAttendances() {
        List<Employee> employees = employeeRepository.findAll();

        List<Employee> employeesWithAttendances = employeeRepository.findEmployeesWithAttendances(
                employees.stream().map(Employee::getId).collect(Collectors.toList())
        );

        return employeesWithAttendances.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}