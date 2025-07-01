package com.boot.designpatterns.builder.document;

/**
 * Client class to demonstrate the Builder pattern with the Document example.
 * This class shows how to create different types of documents using the same construction process.
 */
public class DocumentClient {
    
    /**
     * Main method to run the demonstration.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("===== Document Builder Pattern Demonstration =====\n");
        
        // Create builders for different document types
        HTMLDocumentBuilder htmlBuilder = new HTMLDocumentBuilder();
        PDFDocumentBuilder pdfBuilder = new PDFDocumentBuilder();
        
        // Create a director to manage the construction process
        DocumentDirector director = new DocumentDirector(htmlBuilder);
        
        // Sample content for documents
        String title = "Why use design patterns";
        String body = "Design patterns are reusable solutions to common problems in software design. "
                + "They represent best practices evolved over time by experienced software developers. "
                + "Using design patterns helps to create more flexible, reusable, and maintainable code.";
        String author = "C. H. Afzal";
        String conclusion = "Happy Coding!";
        
        // Construct a simple HTML document
        System.out.println("\n----- Building Simple HTML Document -----");
        IDocument simpleHtmlDoc = director.constructSimpleDocument(title, body);
        System.out.println("\nSimple HTML Document Details: " + simpleHtmlDoc);
        System.out.println("Document Type: " + simpleHtmlDoc.getType());
        
        // Display the document
        System.out.println("\nDisplaying Simple HTML Document:");
        simpleHtmlDoc.display();
        
        // Construct a complete HTML document
        System.out.println("\n----- Building Complete HTML Document -----");
        htmlBuilder.reset(); // Reset the builder to create a new document
        IDocument completeHtmlDoc = director.constructCompleteDocument(title, body, author, conclusion);
        System.out.println("\nComplete HTML Document Details: " + completeHtmlDoc);
        
        // Switch to building a PDF document
        director.setDocumentBuilder(pdfBuilder);
        
        // Construct a simple PDF document
        System.out.println("\n----- Building Simple PDF Document -----");
        IDocument simplePdfDoc = director.constructSimpleDocument(title, body);
        System.out.println("\nSimple PDF Document Details: " + simplePdfDoc);
        System.out.println("Document Type: " + simplePdfDoc.getType());
        
        // Display the document
        System.out.println("\nDisplaying Simple PDF Document:");
        simplePdfDoc.display();
        
        // Construct a custom PDF document with only title and conclusion
        System.out.println("\n----- Building Custom PDF Document -----");
        pdfBuilder.reset(); // Reset the builder to create a new document
        boolean[] includeParts = {true, false, false, true}; // Include only title and conclusion
        IDocument customPdfDoc = director.constructCustomDocument(title, body, author, conclusion, includeParts);
        System.out.println("\nCustom PDF Document Details: " + customPdfDoc);
        
        // Display the document
        System.out.println("\nDisplaying Custom PDF Document:");
        customPdfDoc.display();
        
        // Demonstrate how the Builder pattern separates construction from representation
        System.out.println("\n===== Builder Pattern Benefits =====");
        System.out.println("1. Same construction process creates different document types");
        System.out.println("2. Construction steps are hidden from the client");
        System.out.println("3. New document types can be added without changing the Director");
        System.out.println("4. Document objects are created step by step");
        System.out.println("5. The client code is simplified and more readable");
    }
}