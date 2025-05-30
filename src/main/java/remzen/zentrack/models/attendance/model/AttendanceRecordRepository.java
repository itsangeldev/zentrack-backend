package remzen.zentrack.models.attendance.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import remzen.zentrack.models.employees.model.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {

    @Query("SELECT ar FROM AttendanceRecord ar WHERE " +
            "ar.employee = :employee AND " +
            "ar.date BETWEEN :startDate AND :endDate " +
            "ORDER BY ar.date ASC")
    List<AttendanceRecord> findByEmployeeAndDateBetween(
            @Param("employee") Employee employee,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    List<AttendanceRecord> findByEmployeeAndStatusOrderByDateAsc(
            @Param("employee") Employee employee,
            @Param("status") AttendanceStatus status
    );

    Optional<AttendanceRecord> findByEmployeeAndDate(Employee employee, LocalDate date);

}
