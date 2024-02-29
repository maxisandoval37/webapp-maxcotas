package ar.dev.maxisandoval.webappmaxcotas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data //toString, equals, hashcode, getters y setters
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @NotBlank(message = "La especie no puede estar en blanco")
    private String especie;

    @NotBlank(message = "El sexo no puede estar en blanco")
    private String sexo;

    @NotNull(message = "La fecha de nacimiento no puede estar en blanco")
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    @NotNull(message = "El veterinario no puede estar en blanco")
    private Veterinario veterinario;

    @ManyToMany
    @JoinTable(name = "Mascota_Vacuna",
        joinColumns = @JoinColumn(name = "mascota_id"),
        inverseJoinColumns = @JoinColumn(name = "vacuna_id"))
    @Builder.Default
    private List<Vacuna> vacunasAplicadas = new ArrayList<>();
}