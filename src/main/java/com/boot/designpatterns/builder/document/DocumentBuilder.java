package com.boot.designpatterns.builder.document;

/**
 * Abstract builder class for document construction.
 * This class defines the methods for building the different parts of a document.
 * Concrete builders will implement these methods to construct specific document types.
 */
public abstract class DocumentBuilder {
    
    /**
     * Add a title to the document.
     * 
     * @param title The title to add
     */
    public void addTitle(String title) {
        // Default implementation does nothing
    }
    
    /**
     * Add body content to the document.
     * 
     * @param body The body content to add
     */
    public void addBody(String body) {
        // Default implementation does nothing
    }
    
    /**
     * Add an author to the document.
     * 
     * @param author The author to add
     */
    public void addAuthor(String author) {
        // Default implementation does nothing
    }
    
    /**
     * Add a conclusion to the document.
     * 
     * @param conclusion The conclusion to add
     */
    public void addConclusion(String conclusion) {
        // Default implementation does nothing
    }
    
    /**
     * Build and return the document.
     * 
     * @return The constructed document
     */
    public abstract IDocument buildDocument();
}