package com.proximate.www.dashmate.utils;

import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

//import com.lowagie.text.pdf.PdfWriter;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRExporter;
//import net.sf.jasperreports.engine.JRExporterParameter;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.export.JRPdfExporter;
//import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
//import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
//import net.sf.jasperreports.engine.export.JRXlsExporter;
//import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
//import net.sf.jasperreports.engine.util.JRSaver;
//import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("deprecation")
public class Reportes  implements Serializable {
	
	JasperReportsPdfView reporte;
	
//	private JasperReport reporte;
//	private JasperPrint print; // exportar reporte a axcel

	public boolean jasperReport(String ruta, InputStream dataSourceName,
			Map<String, Object> params, Connection conn)
			throws ClassNotFoundException, FileNotFoundException {
		
//		this.reporte = new JasperReportsPdfView();
//		this.reporte.
		
//		reporte = JasperCompileManager.compileReport(dataSourceName);
//		this.print = JasperFillManager.fillReport(this.reporte, params, conn);
//		if (this.print.getPages().isEmpty()) {
//			return false;
//		}
////		int permisos =PdfWriter.ALLOW_PRINTING;
//		// Esta clase es la encargada de exportar el archivo a pdf
//		final JRExporter jtrtf = new JRPdfExporter();
////		jtrtf.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
//		jtrtf.setParameter(JRPdfExporterParameter.IS_128_BIT_KEY, Boolean.TRUE);
////		jtrtf.setParameter(JRPdfExporterParameter.PERMISSIONS, permisos);
//		
//		jtrtf.setParameter(JRExporterParameter.JASPER_PRINT, print);
//		jtrtf.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(ruta + ".pdf")); // your output goes here
//		
//		// Gurdamos una copia en el computador Ejemplo c:/reportes.jrprint
////		JRSaver.saveObject(this.print, ruta + ".jrprint");
//		// Gurdamos una copia en el computador Ejemplo c:/reportes.pdf
//		jtrtf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, ruta + ".pdf");
//		// este metodo exporta a los diferentes formatos en este caso pdf
//		jtrtf.exportReport();
//		// Metodo que se encarga de mostrar el reporte en la pantalla
//		JasperViewer.viewReport(this.print, false, Locale.getDefault());
		return true;
	}

}
