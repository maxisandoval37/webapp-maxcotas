package ar.dev.maxisandoval.webappmaxcotas.service;

import ar.dev.maxisandoval.webappmaxcotas.model.Mascota;
import ar.dev.maxisandoval.webappmaxcotas.model.Veterinario;
import ar.dev.maxisandoval.webappmaxcotas.repository.MascotaRepository;
import ar.dev.maxisandoval.webappmaxcotas.repository.VacunaRepository;
import ar.dev.maxisandoval.webappmaxcotas.repository.VeterinarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MascotaService {

    private MascotaRepository mascotaRepository;
    private VeterinarioRepository veterinarioRepository;
    private VacunaRepository vacunaRepository;

    public Mascota obtenerMascotaPorId(Long id) {
       return mascotaRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontró la mascota: "+id));
    }

    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAllByOrderByNombreIgnoreCaseAsc();
    }

    public Mascota guardarMascota (Mascota mascota, Long idVeterinario, List<Long> idVacunas) {

        Veterinario veterinario = veterinarioRepository.findById(idVeterinario)
                .orElseThrow(() -> new RuntimeException("No se encontró el veterinario ("+idVeterinario+") al momento de guardar la mascota."));

        mascota.setVeterinario(veterinario);

        if (idVacunas != null) {
            mascota.setVacunasAplicadas(vacunaRepository.findAllById(idVacunas));
        }

        return mascotaRepository.save(mascota);
    }
}
