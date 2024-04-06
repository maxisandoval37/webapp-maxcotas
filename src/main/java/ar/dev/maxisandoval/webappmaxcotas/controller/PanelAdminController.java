package ar.dev.maxisandoval.webappmaxcotas.controller;

import ar.dev.maxisandoval.webappmaxcotas.model.Mascota;
import ar.dev.maxisandoval.webappmaxcotas.model.Usuario;
import ar.dev.maxisandoval.webappmaxcotas.service.CustomUserDetailsService;
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
    public String actualizarRolUsuario(@PathVariable Long id, @RequestParam String rol) {
        customUserDetailsService.actualizarRolUsuario(id, rol);
        return "redirect:/gestorRoles";
    }

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarMascota(@PathVariable Long id){
        customUserDetailsService.eliminarUsuario(id);
        return "redirect:/gestorRoles";
    }

    //TODO En caso de que el rol sea veterinario, solicitar cargar la matricula y el email para insertar nuevo vete
}
