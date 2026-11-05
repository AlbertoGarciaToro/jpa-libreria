package mx.fes.unam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
    @GetMapping("ver_usuario")
    public String usuario(Model model){
        model.addAttribute("contenido","Lista Usuario");
        return "usuario/usuario";
    }
}
