package ar.dev.maxisandoval.webappmaxcotas.controller;

import ar.dev.maxisandoval.webappmaxcotas.model.Usuario;
import ar.dev.maxisandoval.webappmaxcotas.model.Veterinario;
import ar.dev.maxisandoval.webappmaxcotas.service.CustomUserDetailsService;
import ar.dev.maxisandoval.webappmaxcotas.service.VeterinarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PanelAdminController {

    private final CustomUserDetailsService customUserDetailsService;
    private final VeterinarioService veterinarioService;

    @GetMapping("/gestorRoles")
    public String gestorRoles(Model model) {
        model.addAttribute("usuarios", customUserDetailsService.listarUsuariosRegistrados());
        return "gestorRoles";
    }

    @GetMapping("/actualizarRolUsuario/{id}")
    public String mostrarFormularioActualizarUsuario(@PathVariable Long id, Model model){
        Usuario usuario = customUserDetailsService.obtenerUsuarioPorId(id);
        model.addAttribute("usuario", usuario);

        return "actualizarRolUsuario";
    }

    @PostMapping("/actualizarRolUsuario/{id}")
    public String actualizarRolUsuario(@PathVariable Long id, @RequestParam(required = false) String rol, @RequestParam(required = false) String matricula, @RequestParam(required = false) String email) {
        if (rol.equals("ROL_VETERINARIO") && matricula != null && email != null) {

            Veterinario veterinarioNuevo = new Veterinario();
            veterinarioNuevo.setMatricula(matricula);
            veterinarioNuevo.setEmail(email);
            veterinarioNuevo.setUsuario(customUserDetailsService.obtenerUsuarioPorId(id));

            Veterinario veterinarioGuardado = veterinarioService.guardarVeterinario(veterinarioNuevo);
            customUserDetailsService.actualizarRolUsuarioVeterinario(id, veterinarioGuardado);
        } else {
            customUserDetailsService.actualizarRolUsuario(id, rol);
        }
        return "redirect:/gestorRoles";
    }

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarMascota(@PathVariable Long id){
        customUserDetailsService.eliminarUsuario(id);
        return "redirect:/gestorRoles";
    }
}