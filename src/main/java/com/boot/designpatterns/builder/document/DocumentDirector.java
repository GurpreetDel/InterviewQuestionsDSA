package com.boot.designpatterns.builder.document;

/**
 * Director class for document construction.
 * This class is responsible for directing the construction process of the document
 * by calling the builder methods in the correct order.
 */
public class DocumentDirector {
    
    private DocumentBuilder documentBuilder;
    
    /**
     * Constructor that takes a DocumentBuilder.
     * 
     * @param documentBuilder The builder to use for document construction
     */
    public DocumentDirector(DocumentBuilder documentBuilder) {
        this.documentBuilder = documentBuilder;
    }
    
    /**
     * Set a different builder for the director.
     * 
     * @param documentBuilder The new builder to use
     */
    public void setDocumentBuilder(DocumentBuilder documentBuilder) {
        this.documentBuilder = documentBuilder;
    }
    
    /**
     * Construct a simple document with just title and body.
     * 
     * @param title The title of the document
     * @param body The body content of the document
     * @return The constructed document
     */
    public IDocument constructSimpleDocument(String title, String body) {
        System.out.println("Building a simple document...");
        documentBuilder.addTitle(title);
        documentBuilder.addBody(body);
        return documentBuilder.buildDocument();
    }
    
    /**
     * Construct a complete document with title, body, author, and conclusion.
     * 
     * @param title The title of the document
     * @param body The body content of the document
     * @param author The author of the document
     * @param conclusion The conclusion of the document
     * @return The constructed document
     */
    public IDocument constructCompleteDocument(String title, String body, String author, String conclusion) {
        System.out.println("Building a complete document...");
        documentBuilder.addTitle(title);
        documentBuilder.addBody(body);
        documentBuilder.addAuthor(author);
        documentBuilder.addConclusion(conclusion);
        return documentBuilder.buildDocument();
    }
    
    /**
     * Construct a document with the specified parts.
     * 
     * @param title The title of the document (can be null)
     * @param body The body content of the document (can be null)
     * @param author The author of the document (can be null)
     * @param conclusion The conclusion of the document (can be null)
     * @param includeParts An array of booleans indicating which parts to include [title, body, author, conclusion]
     * @return The constructed document
     */
    public IDocument constructCustomDocument(String title, String body, String author, String conclusion, boolean[] includeParts) {
        System.out.println("Building a custom document...");
        
        if (includeParts.length != 4) {
            throw new IllegalArgumentException("includeParts array must have exactly 4 elements");
        }
        
        if (includeParts[0] && title != null) {
            documentBuilder.addTitle(title);
        }
        
        if (includeParts[1] && body != null) {
            documentBuilder.addBody(body);
        }
        
        if (includeParts[2] && author != null) {
            documentBuilder.addAuthor(author);
        }
        
        if (includeParts[3] && conclusion != null) {
            documentBuilder.addConclusion(conclusion);
        }
        
        return documentBuilder.buildDocument();
    }
}