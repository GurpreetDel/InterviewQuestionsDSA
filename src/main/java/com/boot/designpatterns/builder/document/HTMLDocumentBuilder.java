package com.boot.designpatterns.builder.document;

/**
 * Concrete builder for HTML documents.
 * This class implements the methods to build the specific parts of an HTML document.
 */
public class HTMLDocumentBuilder extends DocumentBuilder {
    
    private HTMLDocument htmlDocument;
    
    /**
     * Constructor initializes a new HTMLDocumentBuilder.
     */
    public HTMLDocumentBuilder() {
        this.htmlDocument = new HTMLDocument();
    }
    
    @Override
    public void addTitle(String title) {
        htmlDocument.setTitle(title);
    }
    
    @Override
    public void addBody(String body) {
        htmlDocument.setBody(body);
    }
    
    @Override
    public void addAuthor(String author) {
        htmlDocument.setAuthor(author);
    }
    
    @Override
    public void addConclusion(String conclusion) {
        htmlDocument.setConclusion(conclusion);
    }
    
    @Override
    public IDocument buildDocument() {
        return htmlDocument;
    }
    
    /**
     * Reset the builder to create a new document.
     */
    public void reset() {
        this.htmlDocument = new HTMLDocument();
    }
}