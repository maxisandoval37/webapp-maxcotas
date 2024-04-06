package ar.dev.maxisandoval.webappmaxcotas.controller;

import ar.dev.maxisandoval.webappmaxcotas.service.CustomUserDetailsService;
import ar.dev.maxisandoval.webappmaxcotas.service.VeterinarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class VeterinarioViewController {

    private final CustomUserDetailsService customUserDetailsService;
    private final VeterinarioService veterinarioService;

    @GetMapping("/veterinarios")
    public String listarVeterinarios(Model model) {
        model.addAttribute("veterinarios", veterinarioService.listarVeterinarios());
        model.addAttribute("userService", customUserDetailsService);

        return "veterinarios";
    }
}