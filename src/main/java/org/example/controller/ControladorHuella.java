package org.example.controller;

import org.example.model.dao.HuellaDao;
import org.example.model.dao.RecomendacionDao;
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
                    System.out.println("Cada vez que realizas esta accion probocas: " + Carbono + " " + huellaCarbono.getIdActividad().getIdCategoria().getUnidad());

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
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (arg != 8);
    }

    public static void generarPDF() {
        // Obtener usuario y fecha actual con hora y minutos
        String nombreUsuario = Sesion.getSesion().getUsuario().getNombre();
        String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm"));

        // Definir el destino del archivo con el nombre del usuario y la fecha
        String destino = "./PDF/Reporte_" + nombreUsuario + "_" + fechaActual + ".pdf";

        try {
            // Crear el documento PDF
            PdfWriter writer = new PdfWriter(destino);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Obtener la lista de huellas del usuario
            List<Huella> huellas = HuellaDao.BuscarHuella();

            // Agregar título y usuario
            document.add(new Paragraph("📄 Reporte de Huella de Carbono").setBold().setFontSize(18));
            document.add(new Paragraph("👤 Usuario: " + nombreUsuario).setFontSize(14));
            document.add(new Paragraph("📅 Generado el: " + fechaActual.replace("_", " a las ")).setFontSize(12));
            document.add(new Paragraph("\n"));

            // Crear tabla con columnas para Fecha, Valor, Actividad y Recomendaciones
            float[] columnWidths = {3, 3, 3, 4};
            Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();

            // Agregar encabezados
            table.addHeaderCell("📅 Fecha");
            table.addHeaderCell("🔢 Valor");
            table.addHeaderCell("🚀 Actividad");
            table.addHeaderCell("💡 Recomendación");

            // Agregar datos de la huella de carbono con sus recomendaciones
            for (Huella h : huellas) {
                table.addCell(h.getFecha().toString());
                table.addCell(String.valueOf(h.getValor()));
                table.addCell(h.getIdActividad().getNombre());

                // Obtener recomendación según la actividad
                List<String> recomendaciones = RecomendacionDao.BuscarPorActividad(h.getIdActividad().getId());
                if (!recomendaciones.isEmpty()) {
                    StringBuilder recomendacionesText = new StringBuilder();
                    for (String rec : recomendaciones) {
                        recomendacionesText.append("Recomendación: " + rec + "\n");
                    }
                    table.addCell(recomendacionesText.toString());
                } else {
                    table.addCell("No hay recomendaciones disponibles para esta actividad.");
                }
            }

            // Agregar la tabla al documento y cerrar
            document.add(table);
            document.close();

            System.out.println("✅ PDF generado en: " + destino);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
