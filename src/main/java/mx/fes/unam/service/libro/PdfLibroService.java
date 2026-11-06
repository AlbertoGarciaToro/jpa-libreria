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

            Paragraph title = new Paragraph("Reporte Libros");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            PdfPTable table = new PdfPTable(2);
            PdfPCell celda=new PdfPCell(new Phrase("Autor"));
            celda.setBackgroundColor(new Color(167,218,255));
            celda.setPadding(8f);
            table.addCell(celda);
            celda=new PdfPCell(new Phrase("Precio"));
            celda.setBackgroundColor(new Color(167,18,55));
            celda.setPadding(8f);
            table.addCell(celda);
            for (LibroDTO datos:lista){
                table.addCell(datos.getAutor());
                table.addCell(String.valueOf(datos.getPrecio()));

            }
            document.add(table);
            document.close();
            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }

}