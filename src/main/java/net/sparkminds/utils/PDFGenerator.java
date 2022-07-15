package net.sparkminds.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.client.HttpServerErrorException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import lombok.Data;
import net.sparkminds.dto.ApplicationResponseDto;
import net.sparkminds.dto.PastProjectResponseDto;
import net.sparkminds.entity.Application;

@Data
public class PDFGenerator {
	
	private List<ApplicationResponseDto> applications;
	
	public void generate(HttpServletResponse response) throws DocumentException, IOException {

		// Creating the Object of Document
		Document document = new Document(PageSize.A3);

		// Getting instance of PdfWriter
		PdfWriter.getInstance(document, response.getOutputStream());

		// Opening the created document to modify it
		document.open();

		// Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		// Creating paragraph
		Paragraph paragraph = new Paragraph("List Of Application", fontTiltle);

		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		// Adding the created paragraph in document
		document.add(paragraph);

//		String imageFile = "C:/itextExamples/javafxLogo.jpg";
//		ImageData data = ImageDataFactory.create(imageFile);
		
		// Creating a table of 4 columns
		PdfPTable table = new PdfPTable(5);

		// Setting width of table, its columns and spacing
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 2, 2, 3, 2, 4 });
		table.setSpacingBefore(5);

		// Create Table Cells for table header
		PdfPCell cell = new PdfPCell();

		// Setting the background color and padding
		cell.setBackgroundColor(CMYKColor.MAGENTA);
		cell.setPadding(5);

		// Creating font
		// Setting font style and size
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);

		// Adding headings in the created table cell/ header
		// Adding Cell to table
//		cell.setPhrase(new Phrase("ID", font));
//		table.addCell(cell);
		cell.setPhrase(new Phrase("Image", font));
        table.addCell(cell);
		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Email", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("GitHub", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("PastProject", font));
		table.addCell(cell);

		// Iterating over the list of students
		for (ApplicationResponseDto application : applications) {
			// Adding student id
//			table.addCell(String.valueOf(application.getId()));
		    String imageUrl = "https://avatars.githubusercontent.com/" + application.getGithub();
		    Image image = Image.getInstance(imageUrl);
		    table.addCell(image);
			table.addCell(application.getName());
			table.addCell(application.getEmail());
			table.addCell(application.getGithub());
			
			List<String> project = application.getPastProjects().stream().map(entity -> {
				return String.format("Name:  %s \nEmployment: %s \nCapacity: %s \nDuration: %s \nStartYear: %s \nRole: %s \nTeamSize: %s \nLinkToRepository: %s \nLinkToLiveUrl: %s \n", 
				        entity.getPastProjectName(),entity.getEmployment(), entity.getCapacity(), entity.getDuration(),
				        entity.getStartYear(), entity.getRole(), entity.getTeamSize(), entity.getLinkToRepository(), entity.getLinkToLiveUrl());
            }).collect(Collectors.toList());
			String projects = String.join(" \n", project);
            table.addCell(projects); 
			};
                    
            	
			

			
			 
			
		
		// Adding the created table to document
		document.add(table);

		// Closing the document
		document.close();

	}
}
