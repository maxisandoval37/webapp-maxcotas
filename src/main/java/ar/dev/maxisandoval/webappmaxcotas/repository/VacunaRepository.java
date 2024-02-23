package ar.dev.maxisandoval.webappmaxcotas.repository;

import ar.dev.maxisandoval.webappmaxcotas.model.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacunaRepository extends JpaRepository <Vacuna, Long> { }