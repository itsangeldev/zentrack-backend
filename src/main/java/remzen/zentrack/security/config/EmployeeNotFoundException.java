package remzen.zentrack.security.config;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String employeeId) {
        super("Empleado no encontrado con ID: " + employeeId);
    }
}