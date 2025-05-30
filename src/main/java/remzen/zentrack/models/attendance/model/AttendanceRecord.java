package remzen.zentrack.models.attendance.model;

import jakarta.persistence.*;
import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import remzen.zentrack.models.employees.model.Employee;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attendance_records")
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime entryTime;
    private LocalTime exitTime;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @Column(length = 255)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}