package com.example.demo.config;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

@Component
public class PdfGenerator {

	public void generatePdf(User user) throws Exception {

		String userHome = System.getProperty("user.home");
		String desktopPath = userHome + File.separator + "Desktop" + File.separator + "UserDetails";

		Path directory = Paths.get(desktopPath);
		Files.createDirectories(directory);

		String pdfPath = desktopPath + File.separator + user.getName() + "_Invoice.pdf";

		try (PdfWriter writer = new PdfWriter(pdfPath);
				PdfDocument pdf = new PdfDocument(writer);
				Document document = new Document(pdf)) {

			Paragraph companyHeader = new Paragraph("Company Name\nInvoice").setTextAlignment(TextAlignment.CENTER)
					.setBold().setFontSize(18);
			document.add(companyHeader);

			String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			Paragraph dateParagraph = new Paragraph("Date: " + currentDate).setTextAlignment(TextAlignment.RIGHT);
			document.add(dateParagraph);

			Paragraph placeParagraph = new Paragraph("Place: " + user.getPlace()).setTextAlignment(TextAlignment.RIGHT);
			document.add(placeParagraph);

			document.add(new Paragraph("\n").setFontSize(10));

			Table table = new Table(2);
			table.addCell("Name").addCell(user.getName());
			table.addCell("Deposit Amount").addCell(user.getDepositAmount().toString());
			// table.addCell("Date").addCell(user.getDate().toString());
			table.setMarginTop(10f);

			document.add(table);

			Paragraph footer = new Paragraph("\nThank you for your business!").setTextAlignment(TextAlignment.CENTER)
					.setItalic();
			document.add(footer);
		}
	}
}
