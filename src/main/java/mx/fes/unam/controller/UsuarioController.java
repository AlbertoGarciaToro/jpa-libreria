    package mx.fes.unam.controller;

    import jakarta.validation.Valid;
    import mx.fes.unam.model.entities.Usuario.UsuarioDTO;
    import mx.fes.unam.model.entities.libro.LibroDTO;
    import mx.fes.unam.service.libro.PdfLibroService;
    import mx.fes.unam.service.usuario.PdfUsuarioService;
    import mx.fes.unam.service.usuario.UsuarioService;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;

    import java.util.List;

    @Controller
    @RequestMapping("usuario")
    public class UsuarioController {

        private final PdfUsuarioService pdfUsuarioService;
        private final UsuarioService usuarioService;

        public UsuarioController(UsuarioService usuarioService, PdfUsuarioService pdfUsuarioService) {
            this.usuarioService = usuarioService;
            this.pdfUsuarioService = pdfUsuarioService;
        }

        @GetMapping("ver_lista")
        public String usuario(Model model){

            List<UsuarioDTO> lista = usuarioService.listaUsuarios();
            model.addAttribute("contenido","Lista Usuario");
            model.addAttribute("lista", lista);
            return "usuario/usuario";
        }

    //    este es uno existete porque viene de la lista ya mostrada
        @GetMapping("modificar-usuario/{id}")
        public String modificar(@PathVariable("id") Integer id, Model model){

            UsuarioDTO dto = usuarioService.obtenerUsuario(id.longValue());
            model.addAttribute("contenido", "Modificar Usuario");
            model.addAttribute("usuario", dto);
            return "usuario/alta-usuario";
        }

    //    se cuando se envia la modificacion de uno ya existente
        @PostMapping("salvar-usuario")
        public String salvarUsuario(@Valid @ModelAttribute("usuario") UsuarioDTO usuario,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    Model model){

            if(bindingResult.hasErrors()){
                model.addAttribute("usuario",usuario);
                model.addAttribute("contenido","Verifica los errores siguientes");
                return "usuario/alta-usuario";
            }

            usuarioService.guardarUsuario(usuario);
            redirectAttributes.addFlashAttribute("success","Se almaceno con éxito Lote");
            return "redirect:/usuario/ver_lista";
        }


        @GetMapping("eliminar-usuario/{id}")
        public String eliminarUsuario(@PathVariable("id") Long id , RedirectAttributes modelo){

            usuarioService.eliminarUsuario((id));
            modelo.addFlashAttribute("success","Se borro con éxito Lote");
            return "redirect:/usuario/ver_lista";
        }

        @GetMapping("generar_pdf")
        public ResponseEntity<byte[]> generar(Model model) {
            List<UsuarioDTO> lista = usuarioService.listaUsuarios();
            byte[] pdfBytes = pdfUsuarioService.generatePdf(lista);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "reporte.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        }

    }

