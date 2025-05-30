package remzen.zentrack.models.employees.model;


import jakarta.persistence.*;
import lombok.*;
import remzen.zentrack.models.attendance.model.AttendanceRecord;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String employeeId;

    private String name;
    private String department;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<AttendanceRecord> records = new ArrayList<>();


}