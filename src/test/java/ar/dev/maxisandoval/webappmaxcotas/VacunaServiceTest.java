package ar.dev.maxisandoval.webappmaxcotas;

import ar.dev.maxisandoval.webappmaxcotas.model.Vacuna;
import ar.dev.maxisandoval.webappmaxcotas.service.VacunaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VacunaServiceTest {

    @Autowired
    private VacunaService vacunaService;

    private Vacuna vacunaGuardada;

    @BeforeEach
    void setup () {
        Vacuna vacuna = new Vacuna();
        vacuna.setNombre("Moquillo");
        vacuna.setFechaVencimiento(LocalDate.now().plusDays(30L));

        vacunaGuardada = vacunaService.guardarVacuna(vacuna);
    }

    @Test
    void testGuardarVacuna() {
        assertNotNull(vacunaGuardada.getId());
        assertEquals("Moquillo", vacunaGuardada.getNombre());
        assertEquals(LocalDate.now().plusDays(30L), vacunaGuardada.getFechaVencimiento());
    }

    @Test
    void testListarVacunas() {
        List<Vacuna> vacunas = vacunaService.listarVacunas();
        assertFalse(vacunas.isEmpty());
    }

    @Test
    void testObtenerVacunaPorId() {
        Long vacunaId = 1L;
        Vacuna vacuna = vacunaService.obtenerVacunaPorId(vacunaId);

        assertNotNull(vacuna);
        assertEquals(vacunaId, vacuna.getId());
    }

    @Test
    void testEliminarMascota() {
        Long vacunaId = 1L;

        //Buscamos si existe la vacuna antes de eliminarla
        assertNotNull(vacunaService.obtenerVacunaPorId(vacunaId));

        vacunaService.eliminarVacuna(vacunaId);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            vacunaService.obtenerVacunaPorId(vacunaId);
        });

        String expectedMessage = "No se encontró la vacuna: " + vacunaId;
        String actualMessage = exception.getMessage();

        //Corroboramos que efectivamente fue eliminada
        assertTrue(actualMessage.contains(expectedMessage));
    }
}