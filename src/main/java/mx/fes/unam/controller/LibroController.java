package mx.fes.unam.controller;

import jakarta.validation.Valid;
import mx.fes.unam.model.entities.libro.LibroDTO;
import mx.fes.unam.service.libreria.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("libro")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping("ver_lista")
    public String prueba(Model model){
        List<LibroDTO> lista =libroService.listarLibros();
        model.addAttribute("lista",lista);
        model.addAttribute("contenido","Lista de Libros");
        return "libro/libro";
    }
    @GetMapping("eliminar-libro/{id}")
    public String eliminar(@PathVariable("id")Integer id,
                               RedirectAttributes modelo){
        libroService.eliminarLibro(id.longValue());
        modelo.addFlashAttribute("success","Se borro con éxito Lote");
        return "redirect:/libro/ver_lista";
    }
    @GetMapping("modificar-libro/{id}")
    public String modificar(@PathVariable("id") Integer id,Model modelo){
        LibroDTO dto=libroService.obtenerLibro(id.longValue());
        modelo.addAttribute("contenido","Modificar Libro");
        modelo.addAttribute("libro",dto);
        return "libro/alta-libro";
    }
    @PostMapping("salvar-libro")
    public String salvarLibro(@Valid @ModelAttribute("libro") LibroDTO libro,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Model modelo){
        if(bindingResult.hasErrors()){
            modelo.addAttribute("libro",libro);
            modelo.addAttribute("contenido","Verifica los errores siguientes");
            return "libro/alta-libro";
        }
        libroService.guardarLibro(libro);
        redirectAttributes.addFlashAttribute("success","Se almaceno con éxito Lote");
        return "redirect:/libro/ver_lista";
    }
    @GetMapping("alta-libro")
    public String alta(Model model){
        model.addAttribute("libro",new LibroDTO());
        model.addAttribute("contenido","Alta Libros");
        return "libro/alta-libro";
    }
}
