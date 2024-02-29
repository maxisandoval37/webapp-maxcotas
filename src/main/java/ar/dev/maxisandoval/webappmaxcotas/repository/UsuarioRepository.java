package ar.dev.maxisandoval.webappmaxcotas.repository;

import ar.dev.maxisandoval.webappmaxcotas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
