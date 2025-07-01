package com.boot.designpatterns.builder.document;

/**
 * Concrete builder for PDF documents.
 * This class implements the methods to build the specific parts of a PDF document.
 */
public class PDFDocumentBuilder extends DocumentBuilder {
    
    private PDFDocument pdfDocument;
    
    /**
     * Constructor initializes a new PDFDocumentBuilder.
     */
    public PDFDocumentBuilder() {
        this.pdfDocument = new PDFDocument();
    }
    
    @Override
    public void addTitle(String title) {
        pdfDocument.setTitle(title);
    }
    
    @Override
    public void addBody(String body) {
        pdfDocument.setBody(body);
    }
    
    @Override
    public void addAuthor(String author) {
        pdfDocument.setAuthor(author);
    }
    
    @Override
    public void addConclusion(String conclusion) {
        pdfDocument.setConclusion(conclusion);
    }
    
    @Override
    public IDocument buildDocument() {
        return pdfDocument;
    }
    
    /**
     * Reset the builder to create a new document.
     */
    public void reset() {
        this.pdfDocument = new PDFDocument();
    }
}