package com.example.application.service;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PdfAnalysisService {
    private static String fileSrc = "C:\\Users\\user\\Desktop\\Test\\Luke_Essay.pdf";
    private static String picFormat = "png";
    private static String picSuffix = "." + picFormat;
    private File file;
    private PDDocument pdDocument;
    private PDFRenderer pdfRenderer;


    private static int pageNumber = 0;

    private static String title;
    private static String subject;
    private static String author;
    private static String keyWord;
    private static String creator;
    private static String creationDate;
    private static String modificationDate = null;


    public void Initial () throws IOException {
        file = new File(fileSrc);
        pdDocument = PDDocument.load(file);
        pdfRenderer = new PDFRenderer(pdDocument);

        //Get properties of PDF file
        PDDocumentInformation pdDocumentInformation = pdDocument.getDocumentInformation();
        title = pdDocumentInformation.getTitle();
        subject = pdDocumentInformation.getSubject();
        author = pdDocumentInformation.getAuthor();
        keyWord = pdDocumentInformation.getKeywords();
        creator = pdDocumentInformation.getCreator();
        creationDate = pdDocumentInformation.getCreationDate().getTime().toString();
        modificationDate = pdDocumentInformation.getModificationDate().getTime().toString();
    }

    //Print properties of the file instead of the pdf content
    public void showProperties (String fileSrc) {
        if(fileSrc == null){
            fileSrc = this.fileSrc;
        }
        File file = new File(fileSrc);
        System.out.println("========= Properties of " + file.getName() + " =========");
        System.out.println(file.getParent());
        System.out.println("title:---> " + title);
        System.out.println("subject:---> " + subject);
        System.out.println("author:---> " + author);
        System.out.println("keyword:---> " + keyWord);
        System.out.println("creator:---> " + creator);
        System.out.println("creation_date:---> " + creationDate);
        System.out.println("modification_date:---> " + modificationDate);
        System.out.println("========= End of properties =========");
    }


    //Scan pdf and load the content to a txt document, then save in a specific address
    public void getContent (String textDest) throws IOException {
        //If user does not set dest address(null) there has a default address for the output txt file.
        if(textDest == null){
            textDest = file.getParent() + "\\" + file.getName() + ".txt";
        }
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(pdDocument);
        FileWriter fileWriter = new FileWriter(textDest);
        fileWriter.write(text);
        fileWriter.close();
    }
    //Convert all pages to picture file and save to a default address
    public void convertPdfToPic () throws IOException {
        while (pageNumber < pdDocument.getNumberOfPages()) {
            BufferedImage image = pdfRenderer.renderImage(pageNumber);
            ImageIO.write(image, picFormat, new File(file.getAbsolutePath() + "WHOLE_" +
                    ++pageNumber + picSuffix));
        }
        pageNumber = 0;
    }

    //Convert a specified page to picture file and save to a default address
    public void convertPdfToPic (int pageNumber) throws IOException {
        BufferedImage image = pdfRenderer.renderImage(pageNumber);
        ImageIO.write(image, picFormat, new File(file.getAbsolutePath() + "WHOLE_" +
                ++pageNumber + picSuffix));
    }

    //Extract all pictures in the PDF file
    public void extractPic () throws IOException {
        while (pageNumber < pdDocument.getNumberOfPages()) {
            PDPage pdPage = pdDocument.getPage(pageNumber);
            PDResources pdResources = pdPage.getResources();
            for (COSName c : pdResources.getXObjectNames()) {
                PDXObject o = pdResources.getXObject(c);
                if (o instanceof org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) {
                    ImageIO.write(((org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) o).getImage(), "png",
                            new File(file.getAbsolutePath() + "_" + pageNumber +
                                    "_" + new Random().nextDouble() + picSuffix));
                }
            }
            ++pageNumber;
        }
        pageNumber = 0;
    }

    //Extract all picture on specified page in the PDF file
    public void extractPic (int pageNumber) throws IOException {
        PDPage pdPage = pdDocument.getPage(pageNumber - 1);
        PDResources pdResources = pdPage.getResources();
        for (COSName c : pdResources.getXObjectNames()) {
            PDXObject o = pdResources.getXObject(c);
            if (o instanceof org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) {
                ImageIO.write(((org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) o).getImage(), picFormat,
                        new File(file.getAbsolutePath() + "_" + pageNumber +
                                "_" + new Random().nextDouble() + picSuffix));
            }
        }
    }

}

