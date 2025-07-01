package com.boot.designpatterns.builder.document;

/**
 * Interface for document objects.
 * This interface defines the common behavior for all document types.
 */
public interface IDocument {
    /**
     * Display the document content.
     */
    void display();
    
    /**
     * Get the document type.
     * 
     * @return The type of the document (e.g., "HTML", "PDF")
     */
    String getType();
    
    /**
     * Get the document content.
     * 
     * @return The content of the document
     */
    String getContent();
}