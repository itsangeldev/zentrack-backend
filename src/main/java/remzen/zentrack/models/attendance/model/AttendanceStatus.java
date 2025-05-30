package remzen.zentrack.models.attendance.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum AttendanceStatus {
    PRESENT,
    ABSENT,
    LATE,
    VACATION,
    WEEKEND,
    HOLIDAY_WORKED,
    INABILITY;

    public static AttendanceStatus fromString(String value) {
        try {
            return AttendanceStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            // Lógica para manejar valores desconocidos
            if(isWeekend(value)) { // Método auxiliar para detectar fin de semana
                return WEEKEND;
            }
            return ABSENT; // Valor por defecto si no se reconoce
        }
    }

    private static boolean isWeekend(String dateString) {
        // Implementar lógica para detectar si la fecha es fin de semana
        // Ejemplo usando LocalDate
        LocalDate date = LocalDate.parse(dateString);
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }
}
