package mx.fes.unam.service.usuario;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import mx.fes.unam.model.entities.Usuario.UsuarioDTO;
import org.springframework.stereotype.Service;

@Service
public class PdfUsuarioService {

    public byte[] generatePdf(List<UsuarioDTO> lista) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 36, 36, 36, 36);
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Paragraph title = new Paragraph("Reporte Usuarios");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Creamos una tabla de 3 columnas (Nombre, Email, Teléfono)
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100); // Opcional: para que ocupe todo el ancho

            // Cabecera: Nombre
            PdfPCell celda = new PdfPCell(new Phrase("Nombre"));
            celda.setBackgroundColor(new Color(167, 218, 255));
            celda.setPadding(8f);
            table.addCell(celda);

            // Cabecera: Email
            celda = new PdfPCell(new Phrase("Email"));
            celda.setBackgroundColor(new Color(167, 218, 255));
            celda.setPadding(8f);
            table.addCell(celda);

            // Cabecera: Teléfono
            celda = new PdfPCell(new Phrase("Teléfono"));
            celda.setBackgroundColor(new Color(167, 218, 255));
            celda.setPadding(8f);
            table.addCell(celda);

            // Rellenamos las filas con los datos de cada usuario
            for (UsuarioDTO datos : lista) {
                table.addCell(datos.getNombre());
                table.addCell(datos.getEmail());
                table.addCell(datos.getTelefono());
            }

            document.add(table);
            document.close();
            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF de usuarios", e);
        }
    }
}