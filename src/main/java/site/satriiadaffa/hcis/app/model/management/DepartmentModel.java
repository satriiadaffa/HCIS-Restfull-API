package site.satriiadaffa.hcis.app.model.management;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "departments")
@NoArgsConstructor @AllArgsConstructor
public class DepartmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Column(nullable = false, length = 100)
    private String departmentName;
}
