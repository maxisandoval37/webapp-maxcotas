package ar.dev.maxisandoval.webappmaxcotas;

import ar.dev.maxisandoval.webappmaxcotas.model.Veterinario;
import ar.dev.maxisandoval.webappmaxcotas.service.VeterinarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VeterinarioServiceTest {

    @Autowired
    private VeterinarioService veterinarioService;

    private Veterinario veterinarioGuardado;

    @BeforeEach
    void setup () {
        Veterinario veterinario = new Veterinario();
        veterinario.setMatricula("ASD123");
        veterinario.setEmail("drjuandiaz@example.com");

        veterinarioGuardado = veterinarioService.guardarVeterinario(veterinario);
    }

    @Test
    void testGuardarVeterinario() {
        assertNotNull(veterinarioGuardado.getId());
        assertEquals("ASD123", veterinarioGuardado.getMatricula());
        assertEquals("drjuandiaz@example.com", veterinarioGuardado.getEmail());
    }

    @Test
    void testListarVeterinario() {
        List<Veterinario> veterinarios = veterinarioService.listarVeterinarios();
        assertFalse(veterinarios.isEmpty());
    }

    @Test
    void testObtenerVeterinarioPorId() {
        Long veterinarioId = 1L;
        Veterinario veterinario = veterinarioService.obtenerVeterinarioPorId(veterinarioId);

        assertNotNull(veterinario);
        assertEquals(veterinarioId, veterinario.getId());
    }
}