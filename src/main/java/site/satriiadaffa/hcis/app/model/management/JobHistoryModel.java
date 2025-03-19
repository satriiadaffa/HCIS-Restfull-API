package site.satriiadaffa.hcis.app.model.management;

import jakarta.persistence.*;
import lombok.*;
import site.satriiadaffa.hcis.app.model.EmployeeModel;

import java.util.Date;

@Data
@Entity
@Table(name = "job_history")
@NoArgsConstructor
@AllArgsConstructor
public class JobHistoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeModel employeeModel;

    @ManyToOne
    @JoinColumn(name = "old_position_id")
    private PositionModel oldPosition;

    @ManyToOne
    @JoinColumn(name = "new_position_id", nullable = false)
    private PositionModel newPosition;

    @ManyToOne
    @JoinColumn(name = "old_department_id")
    private DepartmentModel oldDepartment;

    @ManyToOne
    @JoinColumn(name = "new_department_id", nullable = false)
    private DepartmentModel newDepartment;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(length = 255)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private EmployeeModel approvedBy;
}
