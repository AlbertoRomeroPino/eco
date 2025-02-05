package org.example.controller;

import org.example.model.dao.HuellaDao;
import org.example.model.entity.*;
import org.example.services.HuellaService;
import org.example.utils.Utils;
import org.example.viewTerminal.ViewHuella;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.UnitValue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.math.BigDecimal;


public class ControladorHuella {
    public static void ControladorHuella() {
        int arg;

        do {
            arg = ViewHuella.VistaHuella();

            switch (arg) {
                case 1:
                    //Crear un Huella
                    HuellaService.CreateHuella();
                    break;
                case 2:
                    // Actualizar un Huella
                    HuellaService.ActualizarHuella();
                    break;
                case 3:
                    // Mostrar todos los Huella
                    HuellaService.MostrarHuella();
                    break;
                case 4:
                    // Borrar un Huella
                    HuellaService.EliminarHuella();
                    break;
                case 5:
                    // Huella de carbono
                    Huella huellaCarbono = HuellaDao.BuscarHuellaId(Utils.leeNumero("Inserte el id de la huella"));
                    BigDecimal factorEmision = huellaCarbono.getIdActividad().getIdCategoria().getFactorEmision();
                    BigDecimal valorHuella = huellaCarbono.getValor();
                    BigDecimal Carbono = factorEmision.multiply(valorHuella);
                    System.out.println(Carbono + " " + huellaCarbono.getIdActividad().getIdCategoria().getUnidad());

                    break;
                case 6:
                    // Comparar
                    HuellaService.CompararHuella();
                    break;
                case 7:
                    generarPDF();
                    break;
                case 8:
                    // Salir
            }
        } while (arg != 8);
    }

    public static void generarPDF() {
        try {
            // Obtener nombre del usuario
            String nombreUsuario = Sesion.getSesion().getUsuario().getNombre();

            // Obtener la fecha y hora actual en formato "dd-MM-yyyy_HH-mm"
            String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm"));

            // Construir el nombre del archivo con usuario y fecha
            String destino = "./PDF/" + nombreUsuario + "_" + fechaActual + ".pdf";

            // Crear el documento PDF
            PdfWriter writer = new PdfWriter(destino);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Obtener datos de huella
            List<Huella> huellas = HuellaDao.BuscarHuella();

            // Agregar título
            document.add(new Paragraph("Reporte de Huella de Carbono").setBold().setFontSize(18));
            document.add(new Paragraph("Usuario: " + nombreUsuario).setFontSize(14));
            document.add(new Paragraph("Fecha de generación: " + fechaActual).setFontSize(12));

            // Crear la tabla
            float[] columnWidths = {3, 3, 3}; // Columnas para Fecha, Valor, Actividad
            Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();

            // Agregar encabezados
            table.addHeaderCell("Fecha");
            table.addHeaderCell("Valor");
            table.addHeaderCell("Actividad");

            // Agregar datos de la huella de carbono
            for (Huella h : huellas) {
                table.addCell(h.getFecha().toString()); // Asegúrate de tener getFecha() en Huella
                table.addCell(String.valueOf(h.getValor()));
                table.addCell(h.getIdActividad().getNombre()); // Ajusta según tu modelo
            }

            // Agregar la tabla al documento
            document.add(table);
            document.close();

            System.out.println("PDF generado en: " + destino);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
