package ar.dev.maxisandoval.webappmaxcotas.service;

import ar.dev.maxisandoval.webappmaxcotas.model.Vacuna;
import ar.dev.maxisandoval.webappmaxcotas.repository.VacunaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class VacunaService {

    VacunaRepository vacunaRepository;

    public Vacuna obtenerVacunaPorId(Long id) {
        return vacunaRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontró la vacuna: "+id));
    }

    public List<Vacuna> listarVacunas() {
        return vacunaRepository.findAll();
    }

    public Vacuna guardarVacuna(Vacuna vacuna) {
        return vacunaRepository.save(vacuna);
    }

    public void eliminarVacuna(Long id) {
        vacunaRepository.deleteById(id);
    }
}