package remzen.zentrack.models.employees.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {


    @Query("SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.records WHERE e.id IN :ids")
    List<Employee> findEmployeesWithAttendances(@Param("ids") List<Long> ids);
    Optional<Employee> findByEmployeeId(String employeeId);



}