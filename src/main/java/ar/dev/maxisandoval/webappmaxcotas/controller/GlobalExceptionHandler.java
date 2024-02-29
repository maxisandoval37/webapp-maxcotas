package ar.dev.maxisandoval.webappmaxcotas.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        // Puedes personalizar el manejo de excepciones según tus necesidades
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}