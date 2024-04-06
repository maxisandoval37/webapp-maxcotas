package ar.dev.maxisandoval.webappmaxcotas;

import  ar.dev.maxisandoval.webappmaxcotas.model.*;
import  ar.dev.maxisandoval.webappmaxcotas.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MascotaServiceTest {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private VacunaService vacunaService;

    private Veterinario veterinarioGuardado;
    private Vacuna vacunaGuardada;
    private Mascota mascotaBasica;

    @BeforeEach
    void setup() {
        Veterinario veterinario = new Veterinario();
        veterinario.setMatricula("123ABC");
        veterinario.setEmail("juan.perez@example.com");

        veterinarioGuardado = veterinarioService.guardarVeterinario(veterinario);

        Vacuna vacuna = new Vacuna();
        vacuna.setNombre("Vacuna contra el COVID-19");
        vacuna.setFechaVencimiento(LocalDate.now());
        vacunaGuardada = vacunaService.guardarVacuna(vacuna);

        mascotaBasica = new Mascota();
        mascotaBasica.setNombre("fatiga");
        mascotaBasica.setEspecie("perro");
        mascotaBasica.setSexo("macho");
        mascotaBasica.setFechaNacimiento(LocalDate.now());
    }

    @Test
    void testGuardarMascotaSinVacunas() {
        mascotaBasica.setVeterinario(veterinarioGuardado);
        Mascota mascotaGuardada = mascotaService.guardarMascota(mascotaBasica, veterinarioGuardado.getId(), null);

        assertNotNull(mascotaGuardada.getId());
        assertEquals("fatiga", mascotaGuardada.getNombre());
        assertEquals("perro", mascotaGuardada.getEspecie());
        assertEquals(veterinarioGuardado.getId(), mascotaGuardada.getVeterinario().getId());
    }

    @Test
    void testGuardarMascotaConVacunas() {
        mascotaBasica.setFechaNacimiento(LocalDate.now());
        mascotaBasica.setVeterinario(veterinarioGuardado);

        List<Long> idsVacunasAplicadasList = new ArrayList<>();
        idsVacunasAplicadasList.add(vacunaGuardada.getId());

        // Guardamos la mascota junto con la vacuna
        Mascota mascotaGuardada = mascotaService.guardarMascota(mascotaBasica, veterinarioGuardado.getId(), idsVacunasAplicadasList);

        assertNotNull(mascotaGuardada.getId());
        assertEquals("fatiga", mascotaGuardada.getNombre());
        assertEquals("perro", mascotaGuardada.getEspecie());
        assertEquals(veterinarioGuardado.getId(), mascotaGuardada.getVeterinario().getId());
        assertFalse(mascotaGuardada.getVacunasAplicadas().isEmpty());
    }

    @Test
    void testListarMascotas() {
        List<Mascota> mascotas = mascotaService.listarMascotas();
        assertFalse(mascotas.isEmpty());
    }

    @Test
    void testObtenerMascotaPorId() {
        Long mascotaId = 2L;
        Mascota mascota = mascotaService.obtenerMascotaPorId(mascotaId);

        assertNotNull(mascota);
        assertEquals(mascotaId, mascota.getId());
    }

    @Test
    void testActualizarMascotaSinVacunas() {
        Long mascotaId = 2L;

        Mascota mascotaActualizada = new Mascota();
        mascotaActualizada.setNombre("firulais");
        mascotaActualizada.setEspecie("perro");
        mascotaActualizada.setSexo("macho");
        mascotaActualizada.setFechaNacimiento(LocalDate.now());
        mascotaActualizada.setVeterinario(veterinarioGuardado);

        mascotaService.actualizarMascota(mascotaId, mascotaActualizada, veterinarioGuardado.getId() ,null);

        Mascota mascotaDespuesDeActualizar = mascotaService.obtenerMascotaPorId(mascotaId);

        assertEquals(mascotaActualizada.getNombre(), mascotaDespuesDeActualizar.getNombre());
        assertEquals(mascotaActualizada.getEspecie(), mascotaDespuesDeActualizar.getEspecie());
        assertEquals(mascotaActualizada.getVeterinario().getId(), mascotaDespuesDeActualizar.getVeterinario().getId());
    }

    @Test
    void testEliminarMascota() {
        Long mascotaId = 1L;

        //Buscamos si existe la mascota antes de eliminarla
        assertNotNull(mascotaService.obtenerMascotaPorId(mascotaId));

        mascotaService.eliminarMascota(mascotaId);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            mascotaService.obtenerMascotaPorId(mascotaId);
        });

        String expectedMessage = "No se encontró la mascota: " + mascotaId;
        String actualMessage = exception.getMessage();

        //Corroboramos que efectivamente fue eliminada
        assertTrue(actualMessage.contains(expectedMessage));
    }
}