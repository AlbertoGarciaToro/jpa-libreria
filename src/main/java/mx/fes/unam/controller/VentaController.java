package mx.fes.unam.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.fes.unam.model.entities.Usuario.UsuarioDTO;
import mx.fes.unam.model.entities.Venta.VentaDTO;
import mx.fes.unam.model.entities.Venta.VentaListDTO;
import mx.fes.unam.model.entities.libro.LibroDTO;
import mx.fes.unam.repository.LibroRepository;
import mx.fes.unam.repository.UsuarioRepository;
import mx.fes.unam.service.libro.LibroService;
import mx.fes.unam.service.usuario.PdfUsuarioService;
import mx.fes.unam.service.usuario.UsuarioService;
import mx.fes.unam.service.venta.PdfVentaService;
import mx.fes.unam.service.venta.VentaMapper;
import mx.fes.unam.service.venta.VentaService;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("venta")
public class VentaController    {

    private final VentaService ventaService;
    private final UsuarioService usuarioService;
    private final LibroService libroService;
    private final PdfVentaService pdfVentaService;





    @GetMapping("ver_lista")
    public String venta(Model model){

        List<VentaDTO> lista = ventaService.listaVentas();

        model.addAttribute("contenido","Lista Ventas");
        model.addAttribute("lista", ventaService.listarVentasConDetalle());

        return "venta/venta";
    }

    @GetMapping("modificar-venta/{id}")
    public String modificar(@PathVariable("id") Integer id, Model model){

        VentaDTO dto = ventaService.obtenerVenta(id.longValue());
        model.addAttribute("contenido", "Modificar Venta");
        model.addAttribute("venta", dto);
        model.addAttribute("libros",libroService.listarLibros());
        model.addAttribute("usuarios",usuarioService.listaUsuarios());

        return "venta/alta-venta";
    }


    @PostMapping("salvar-venta")
    public String salvar(@Valid @ModelAttribute("venta") VentaDTO venta,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("venta",venta);
            model.addAttribute("libros",libroService.listarLibros());
            model.addAttribute("usuarios",usuarioService.listaUsuarios());
            model.addAttribute("contenido","Verifica los errores siguientes");
            return "venta/alta-venta";
        }

        ventaService.guardarVenta(venta);
        redirectAttributes.addFlashAttribute("success","Se almaceno con éxito Lote");
        return "redirect:/venta/ver_lista";
    }

    @GetMapping("generar_pdf")
    public ResponseEntity<byte[]> generar(Model model) {
        List<VentaListDTO> lista = ventaService.listarVentasConDetalle();
        byte[] pdfBytes = pdfVentaService.generatePdf(lista);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "reporte.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }


    @GetMapping("eliminar-venta/{id}")
    public String eliminar(@PathVariable("id")Integer id,
                           RedirectAttributes modelo){
        ventaService.eliminarVenta(id.longValue());
        modelo.addFlashAttribute("success","Se borro con éxito Lote");
        return "redirect:/venta/ver_lista";
    }



    @GetMapping("alta-venta")
    public String alta(Model model){
        model.addAttribute("venta",new VentaDTO());
        model.addAttribute("contenido","Alta Venta");
        return "venta/alta-venta";
    }




}
