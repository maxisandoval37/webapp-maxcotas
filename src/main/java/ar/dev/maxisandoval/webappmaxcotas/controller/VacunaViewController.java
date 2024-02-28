package ar.dev.maxisandoval.webappmaxcotas.controller;

import ar.dev.maxisandoval.webappmaxcotas.model.Vacuna;
import ar.dev.maxisandoval.webappmaxcotas.service.VacunaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
@AllArgsConstructor
public class VacunaViewController {

    private final VacunaService vacunaService;
    @GetMapping("/vacunas")
    public String listarVacunas(Model model) {
        List<Vacuna> vacunas = vacunaService.listarVacunas();
        model.addAttribute("vacunas", vacunas);

        return "vacunas";
    }

    @GetMapping("/agregarVacuna")
    public String mostrarFormularioNuevaVacuna(Model model) {
        model.addAttribute("vacuna", new Vacuna());
        return "agregarVacuna";
    }

    @PostMapping("/guardarVacuna")
    public String guardarVacuna(@ModelAttribute Vacuna vacuna) {
        vacunaService.guardarVacuna(vacuna);
        return "redirect:/vacunas";
    }
}