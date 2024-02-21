package ar.dev.maxisandoval.webappmaxcotas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data //toString, equals, hashcode, getters y setters
@NoArgsConstructor
@AllArgsConstructor
public class Vacuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nombre;
    private LocalDate fechaVencimiento;
}
