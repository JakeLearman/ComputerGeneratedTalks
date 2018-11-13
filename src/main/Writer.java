package main;

// For file management
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

//Vital Libraries for XHTML Conversion and PDF Generation
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class Writer {

    // Constructor takes each section of the paper as a string.
    public static void write(String title, String author, String _abstract, String intro, String method, String conc, String refer) throws Exception {

        // Load XHTML Template as Object
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        System.out.println("Thymeleaf: XHTML Template Resolver Loaded.");

        // Use Thymeleaf to set text for each escape code in template.
        Context context = new Context();
        
        context.setVariable("pTitle", title);
        context.setVariable("aName", author);
        context.setVariable("aIntro", "Abstract");
        context.setVariable("aTitle1", "Introduction");
        context.setVariable("aTitle2", "Method");
        context.setVariable("aTitle3", "Conclusion");
        context.setVariable("aReferences", "References");

        context.setVariable("aIntroContent", _abstract);
        context.setVariable("aTitle1Content", intro);
        context.setVariable("aTitle2Content", method);
        context.setVariable("aTitle3Content", conc);
        context.setVariable("aReferenceContent", refer);

        // System.out.println("Thymeleaf: Assigned variables based on template.\n\n");
        String html = templateEngine.process("template", context);
        // System.out.println("\n\nThymeleaf: Redefined XHTML with new template.");

        // Use flying-saucer/iText to generate PDF from new (formatted) XHTML.
        String PDFName = title + ".pdf";
        
        
       OutputStream outputStream = new FileOutputStream(PDFName);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        //System.out.println("flying-saucer: Created PDF from XHTML (XHTML -> XSL-FO -> PDF) using iText.");

        // Open the PDF after generation if possible for the system.
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(PDFName);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

}