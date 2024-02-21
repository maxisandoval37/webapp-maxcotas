package ar.dev.maxisandoval.webappmaxcotas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data //toString, equals, hashcode, getters y setters
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nombre;
    private String matricula;
    private String email;

    @OneToMany (mappedBy = "veterinario")
    private List<Mascota> mascotasAtendidas;
}
