package remzen.zentrack.models.computers.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "computers")
public class BeanComputer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long computerId;

    @Column(name = "code", unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "state",nullable = false)
    private EState state;

    @Enumerated(EnumType.STRING)
    @Column(name = "area",nullable = false)
    private EArea area;

    @Column(name = "grid_row")
    private Integer gridRow;

    @Column(name = "grid_col")
    private Integer gridCol;


    @Column(name = "description")
    private String description;
}
