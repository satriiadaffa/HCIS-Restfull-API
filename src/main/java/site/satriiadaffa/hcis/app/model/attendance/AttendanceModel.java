package site.satriiadaffa.hcis.app.model.attendance;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="attendances")
public class AttendanceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "clock_in_time")
    private LocalDateTime clockInTime;

    @Column(name = "clock_out_time")
    private LocalDateTime clockOutTime;

    @Column(name = "location")
    private String location;

    @Column(name = "status")
    private String status; // "Present", "Absent", "Late", etc.

    @Column(name = "workHours")
    private double workHours;

    // Constructor tambahan untuk inisialisasi tanpa menggunakan setter secara manual
    public AttendanceModel(Long employeeId, LocalDate date, String location) {
        this.employeeId = employeeId;
        this.date = date;
        this.location = location;
    }
}
