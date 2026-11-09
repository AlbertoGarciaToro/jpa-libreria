package mx.fes.unam.service.libro;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import mx.fes.unam.model.entities.libro.LibroDTO;
import org.springframework.stereotype.Service;

@Service
public class PdfLibroService {

    public byte[] generatePdf(List<LibroDTO> lista) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 36, 36, 36, 36);
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Paragraph title = new Paragraph("Reporte de Libros");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100); // Ocupa todo el ancho de la hoja
            table.setWidths(new float[]{3.0f, 3.0f, 2.5f, 1.5f, 1.5f});

            String[] encabezados = {"Título", "Autor", "ISBN", "Precio", "Stock"};
            for (String textoHeader : encabezados) {
                PdfPCell celda = new PdfPCell(new Phrase(textoHeader));
                celda.setBackgroundColor(new Color(167, 218, 255));
                celda.setPadding(8f);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(celda);
            }

            for (LibroDTO datos : lista) {
                table.addCell(datos.getTitulo() != null ? datos.getTitulo() : "");
                table.addCell(datos.getAutor() != null ? datos.getAutor() : "");
                table.addCell(datos.getIsbn() != null ? datos.getIsbn() : "");
                table.addCell(datos.getPrecio() != null ? String.valueOf(datos.getPrecio()) : "0.0");
                table.addCell(datos.getStock() != null ? String.valueOf(datos.getStock()) : "0");
            }


            document.add(table);
            document.close();
            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }
}