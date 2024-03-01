package ar.dev.maxisandoval.webappmaxcotas.controller;

import ar.dev.maxisandoval.webappmaxcotas.model.Mascota;
import ar.dev.maxisandoval.webappmaxcotas.model.Veterinario;
import ar.dev.maxisandoval.webappmaxcotas.repository.UsuarioRepository;
import ar.dev.maxisandoval.webappmaxcotas.service.MascotaService;
import ar.dev.maxisandoval.webappmaxcotas.service.VacunaService;
import ar.dev.maxisandoval.webappmaxcotas.service.VeterinarioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class MascotaViewController {

    private final MascotaService mascotaService;
    private final VeterinarioService veterinarioService;
    private final VacunaService vacunaService;
    private final UsuarioRepository usuarioRepository;

    @GetMapping("/mascotas")
    public String listarMascotas(Model model) {
        List<Mascota> mascotas;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        mostrarRolesUsuarioActual(authentication);

        Veterinario veterinario = usuarioRepository.findByUsername(username).getVeterinario();

        if (veterinario != null) {
            mascotas = veterinario.getMascotasAtendidas();
        }
        else {
            mascotas = mascotaService.listarMascotas();
        }

        model.addAttribute("mascotas", mascotas);
        return "mascotas";
    }

    private void mostrarRolesUsuarioActual (Authentication authentication){
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String rol = authority.getAuthority();
            log.info("Rol actual: "+rol);
        }
    }

    @GetMapping("/agregarMascota")
    public String mostrarFormularioAgregarMascota(Model model) {
        model.addAttribute("veterinarios", veterinarioService.listarVeterinarios());
        model.addAttribute("vacunas", vacunaService.listarVacunas());
        model.addAttribute("mascota", new Mascota());

        return "agregarMascota";
    }

    @PostMapping("/guardarMascota")
    public String guardarMascota(@ModelAttribute Mascota mascota, @RequestParam Long idVeterinario, @RequestParam(required = false) List<Long> idVacunas){
        mascotaService.guardarMascota(mascota, idVeterinario, idVacunas);
        return "redirect:/mascotas";
    }

    @GetMapping("/actualizarMascota/{id}")
    public String mostrarFormularioActualizarMascota(@PathVariable Long id, Model model){
        Mascota mascota = mascotaService.obtenerMascotaPorId(id);
        model.addAttribute("mascota", mascota);
        model.addAttribute("veterinarios", veterinarioService.listarVeterinarios());
        model.addAttribute("vacunas", vacunaService.listarVacunas());

        return "actualizarMascota";
    }

    @PostMapping("/actualizarMascota/{idMascota}")
    public String actualizarMascota(@PathVariable Long idMascota, @ModelAttribute Mascota mascotaActualizada, @RequestParam Long idVeterinario, @RequestParam(required = false) List<Long> idVacunas) {
        mascotaService.actualizarMascota(idMascota, mascotaActualizada, idVeterinario, idVacunas);
        return "redirect:/mascotas";
    }

    @GetMapping("/eliminarMascota/{id}")
    public String eliminarMascota(@PathVariable Long id){
        mascotaService.eliminarMascota(id);
        return "redirect:/mascotas";
    }
}