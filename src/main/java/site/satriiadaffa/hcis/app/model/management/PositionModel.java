package site.satriiadaffa.hcis.app.model.management;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "positions")
@NoArgsConstructor @AllArgsConstructor
public class PositionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long positionId;

    @Column(nullable = false, length = 100)
    private String positionName;
}


