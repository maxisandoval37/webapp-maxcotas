package ar.dev.maxisandoval.webappmaxcotas.service;

import ar.dev.maxisandoval.webappmaxcotas.model.Mascota;
import ar.dev.maxisandoval.webappmaxcotas.model.Veterinario;
import ar.dev.maxisandoval.webappmaxcotas.repository.MascotaRepository;
import ar.dev.maxisandoval.webappmaxcotas.repository.VacunaRepository;
import ar.dev.maxisandoval.webappmaxcotas.repository.VeterinarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void eliminarMascota(Long id){
        Optional<Mascota> mascotaOptional = mascotaRepository.findById(id);

        if (mascotaOptional.isPresent()){
            mascotaRepository.delete(mascotaOptional.get());
        }
        else {
            throw new RuntimeException("Mascota no encontrada al momento de la eliminacion");
        }
    }

    public void actualizarMascota (Long idMascota, Mascota mascotaActualizada, Long idVeterinario, List<Long> idVacunas) {
        Optional<Mascota> mascotaOptional = mascotaRepository.findById(idMascota);

        Veterinario veterinario = veterinarioRepository.findById(idVeterinario)
                .orElseThrow(() -> new RuntimeException("No se encontró el veterinario ("+idVeterinario+") al momento de guardar la mascota."));

        if (idVacunas != null) {
            mascotaActualizada.setVacunasAplicadas(vacunaRepository.findAllById(idVacunas));
        }

        mascotaActualizada.setVeterinario(veterinario);

        Mascota mascotaExistente = construirMascota(mascotaActualizada, mascotaOptional);
        mascotaRepository.save(mascotaExistente);
    }

    private Mascota construirMascota(Mascota mascotaActualizada, Optional<Mascota> mascotaOptional) {
        Mascota.MascotaBuilder mascotaBuilder = Mascota.builder();

        mascotaOptional.ifPresent(mascotaExistente -> {
            mascotaBuilder
                    .id(mascotaExistente.getId())
                    .nombre(mascotaActualizada.getNombre())
                    .especie(mascotaActualizada.getEspecie())
                    .fechaNacimiento(mascotaActualizada.getFechaNacimiento())
                    .sexo(mascotaActualizada.getSexo())
                    .veterinario(mascotaActualizada.getVeterinario())
                    .vacunasAplicadas(mascotaActualizada.getVacunasAplicadas());
        });

        return mascotaBuilder.build();
    }
}