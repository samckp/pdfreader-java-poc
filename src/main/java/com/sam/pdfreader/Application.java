package com.sam.pdfreader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws IOException {

		extractPdf("src/main/resources/Form.pdf");
	}
	public static void extractPdf(String path) throws IOException {

		PDDocument doc = PDDocument.load(new File(path));
		PDFTextStripper pdfStripper = new PDFTextStripper();

		String text = pdfStripper.getText(doc);

		LOGGER.info("Text in PDF\n---------------------------------");
		LOGGER.info(text);

		// Extract pin code
		Pattern p = Pattern.compile("\\s\\d\\d\\d\\d\\d\\d\\s");
		Matcher m = p.matcher(text);

		while (m.find()){
			LOGGER.info("Pin : "+ m.group());
		}

		doc.close();
	}
}
