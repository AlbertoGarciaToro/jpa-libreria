package mx.fes.unam.service.venta;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import mx.fes.unam.model.entities.Venta.VentaListDTO;
import org.springframework.stereotype.Service;

@Service
public class PdfVentaService {

    public byte[] generatePdf(List<VentaListDTO> lista) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 36, 36, 36, 36);
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Paragraph title = new Paragraph("Reporte de Ventas");
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20f); // Espacio estético entre el título y la tabla
            document.add(title);

            // Creamos una tabla de 5 columnas según los datos de VentaListDTO
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            // Opcional: Ajustar proporciones de ancho de columnas si lo deseas
            table.setWidths(new float[]{1f, 2.5f, 2.5f, 1.2f, 1.5f});

            // Definimos color de cabecera (un azul pastel elegante)
            Color headerColor = new Color(167, 218, 255);

            // Cabeceras de la tabla
            String[] headers = {"ID", "Libro", "Usuario", "Cantidad", "Total"};
            for (String headerTitle : headers) {
                PdfPCell celda = new PdfPCell(new Phrase(headerTitle));
                celda.setBackgroundColor(headerColor);
                celda.setPadding(8f);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(celda);
            }

            // Rellenamos las filas con los datos de cada venta
            for (VentaListDTO venta : lista) {
                table.addCell(String.valueOf(venta.getId()));
                table.addCell(venta.getTituloLibro() != null ? venta.getTituloLibro() : "N/A");
                table.addCell(venta.getNombreUsuario() != null ? venta.getNombreUsuario() : "N/A");
                table.addCell(String.valueOf(venta.getCantidad()));
                table.addCell("$ " + venta.getTotal());
            }

            document.add(table);
            document.close();
            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF de ventas", e);
        }
    }
}