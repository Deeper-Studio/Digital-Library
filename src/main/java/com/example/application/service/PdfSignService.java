package com.example.application.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.cert.Certificate;

public class PdfSignService extends AesService{
    //Address must be refered to local computer!!!
    private static char[] password = "WangJiawei18726077".toCharArray();//Password of keystory
    private static String fileSrc = "C:\\Users\\user\\Desktop\\CNSCC212 Advanced Programming PDFSign\\Luke_Essay.pdf" ;//Original pdf
    private static String fileDest = "C:\\Users\\user\\Desktop\\CNSCC212 Advanced Programming PDFSign\\Signed_Luke_Essay.pdf" ;//Pdf after Signed
    private static String stampPath = "C:\\Users\\user\\Desktop\\CNSCC212 Advanced Programming PDFSign\\Stamp.png";//The stamp for visible signature
    private static String p12StreamPath = "C:\\Users\\user\\Desktop\\CNSCC212 Advanced Programming PDFSign\\group2.p12";//Certification address
    private static String reason = "For CNSCC212 Advanced Programming";
    private static String location = "Weihai Campus, Beijing Jiaotong University\n" +
            "Xiandai Road 69, Nanhai Xinqu (Nanhai New Area)\n" +
            "Weihai City, Shandong, China";


    public static void sign()
            throws GeneralSecurityException, IOException, DocumentException {
        FileInputStream src = new FileInputStream(fileSrc); //Load source file
        FileOutputStream dest = new FileOutputStream(fileDest);//Set out put address
        InputStream p12Stream = new FileInputStream(p12StreamPath);//Load certification

        //Read the keystore and get the private key and certificate chain
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(p12Stream, password);
        String alias = (String)keyStore.aliases().nextElement();
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, password);
        Certificate[] chain = keyStore.getCertificateChain(alias);

        // Creating the reader and the stamper，start pdfreader
        PdfReader reader = new PdfReader(src);
        // The target file output stream
        // Create the signature tool PdfStamper with the last Boolean parameter
        // If false, PDF files can only be signed once, multiple times, and the last time valid
        // If true, the PDF can be appended with signatures, and the checkmark tool can recognize whether the document
        // has been modified after each signature
        PdfStamper stamper = PdfStamper.createSignature(reader, dest, '\0', null, false);

        // Get the digital signature property object and set the digital signature property
        PdfSignatureAppearance pdfSignatureAppearance = stamper.getSignatureAppearance();
        pdfSignatureAppearance.setReason(reason);
        pdfSignatureAppearance.setLocation(location);

        // Set the signature location, page number, and name of the signature domain
        // The position of the signature is the coordinate of the position of the seal relative to the PDF page,
        // and the origin is the lower left corner of the PDF page
        // The four parameters are: bottom left corner of stamp, bottom left corner of stamp,
        // top right corner of stamp, top right corner of stamp
        pdfSignatureAppearance.setVisibleSignature(new Rectangle(0, 800, 100, 700),1, "sig1");
        // pdfSignatureAppearance.setVisibleSignature(new Rectangle(0, 800, 100, 700),reader.getNumberOfPages(), "sig1");
        // pdfSignatureAppearance.setVisibleSignature(new Rectangle(Rectangle.ALIGN_RIGHT + 1, Rectangle.ALIGN_BOTTOM, Rectangle.ALIGN_RIGHT, Rectangle.ALIGN_BOTTOM+1), reader.getNumberOfPages(), "sig1");
        // Read the stamp image. This image is the image of the iText package
        Image image = Image.getInstance(stampPath);
        pdfSignatureAppearance.setSignatureGraphic(image);
        pdfSignatureAppearance.setCertificationLevel(PdfSignatureAppearance.CERTIFIED_NO_CHANGES_ALLOWED);

        // Set the display mode of the stamp
        pdfSignatureAppearance.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);


        // Digest algorithm
        ExternalDigest digest = new BouncyCastleDigest();
        // Signature algorithm
        ExternalSignature signature = new PrivateKeySignature(privateKey, DigestAlgorithms.SHA1, null);
        // Call the IText signature method to complete the PDF cryptostandard.cms signature
        MakeSignature.signDetached(pdfSignatureAppearance, digest, signature, chain, null, null, null, 0, MakeSignature.CryptoStandard.CMS);
    }

    public void setReason(String reason){
        this.reason = reason;
        System.out.println("New reason set successfully.");
    }

    public String getReason(){
        return this.reason;
    }

    public void setP12StreamPath(String p12StreamPath){
        this.p12StreamPath = p12StreamPath;
        System.out.println("New p12 Stream path set successfully.");
    }

    public String getKeystorePath(){
        return this.p12StreamPath;
    }

    public void setLocation(String location){
        this.location = location;
        System.out.println("New location set successfully.");
    }

    public String getLocation(){
        return this.location;
    }

    public void setFileSrc(String fileSrc){
        this.fileSrc = fileSrc;
        System.out.println("New file source set successfully.");
    }

    public String getFileSrc(){
        return this.fileSrc;
    }

    public void setFileDest(String fileDest){
        this.fileDest = fileDest;
        System.out.println("New file destination set successfully.");
    }

    public String getFileDest(){
        return this.fileDest;
    }

    public void setStampPath(String stampPath){
        this.stampPath = stampPath;
        System.out.println("New stamp file source set successfully.");
    }

    public void setPassword(String password){
        this.password = password.toCharArray();
        System.out.println("New password set successfully.");
    }

    public String getPassword(char[] password) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        return AesService.aesEncrypt(password.toString(),AesService.keyGenerator()).toString();
    }

    //Set all the properties at once
    public void setProperties(String fileSrc, String fileDest, String password, String stampPath, String p12StreamPath,
                              String reason , String location){
        setFileSrc(fileSrc);
        setFileDest(fileDest);
        setStampPath(stampPath);
        setP12StreamPath(p12StreamPath);
        setReason(reason);
        setLocation(location);
    }

}
