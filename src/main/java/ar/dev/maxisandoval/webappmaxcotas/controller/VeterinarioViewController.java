package ar.dev.maxisandoval.webappmaxcotas.controller;

import ar.dev.maxisandoval.webappmaxcotas.model.Veterinario;
import ar.dev.maxisandoval.webappmaxcotas.service.VeterinarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
@AllArgsConstructor
public class VeterinarioViewController {

    private final VeterinarioService veterinarioService;

    @GetMapping("/veterinarios")
    public String listarVeterinarios(Model model) {
        List<Veterinario> veterinarios = veterinarioService.listarVeterinarios();
        model.addAttribute("veterinarios", veterinarios);

        return "veterinarios";
    }

    @GetMapping("/agregarVeterinario")
    public String mostrarFormularioAgregarVeterinario(Model model) {
        model.addAttribute("veterinario", new Veterinario());
        return "agregarVeterinario";
    }

    @PostMapping("/guardarVeterinario")
    public String guardarVeterinario(@ModelAttribute Veterinario veterinario) {
        veterinarioService.guardarVeterinario(veterinario);

        return "redirect:/veterinarios";
    }
}