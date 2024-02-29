package ar.dev.maxisandoval.webappmaxcotas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    private String contrasena;
    private String rol;
    private String nombre;
    private String apellido;

    @OneToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;
}