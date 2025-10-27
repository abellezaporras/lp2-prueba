package com.project.clinica.controller;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.clinica.entity.Medicamento;
import com.project.clinica.services.MedicamentoServices;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/repo")
public class ReporteController {
	
	@Autowired
	private MedicamentoServices servicoMed;
	
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping("/medicamentos")
	public void medicamentos(HttpServletResponse response) {
		try {
			System.setProperty("java.awt.headless", "true");
			//obtener lista de medicamentos
			List<Medicamento> lista=servicoMed.listar();
			
			InputStream reporte = new ClassPathResource("reporte_medicamentos.jasper").getInputStream();


			JRBeanCollectionDataSource origen=new JRBeanCollectionDataSource(lista);
			
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, origen);

			
			//System.out.println("Ruta reportes: " + getClass().getResource("reporte_medicamentos.jasper"));
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=reporte.pdf");
			OutputStream out = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);
			out.flush();

			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/tipoBien")
	public void tipoBien(HttpServletResponse response) {
		try {
			System.setProperty("java.awt.headless", "true");
			InputStream reporte = new ClassPathResource("reporte1.jasper").getInputStream();
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, dataSource.getConnection());

			
			//System.out.println("Ruta reportes: " + getClass().getResource("reporte_medicamentos.jasper"));
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=reporte1.pdf");
			OutputStream out = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);
			out.flush();
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
