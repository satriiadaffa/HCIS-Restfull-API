package site.satriiadaffa.hcis.app.model.attendance;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attendance_summary")
public class AttendanceSummaryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "month", nullable = false)
    private int month;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "totalPresent")
    private int totalPresent;

    @Column(name = "totalAbsent")
    private int totalAbsent;

    @Column(name = "totalWorkHours")
    private double totalWorkHours;
}
