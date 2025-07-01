package com.boot.designpatterns.builder.document;

/**
 * HTML document implementation of the IDocument interface.
 * This class represents an HTML document with title, body, author, and conclusion sections.
 */
public class HTMLDocument implements IDocument {
    private String title;
    private String body;
    private String author;
    private String conclusion;
    
    /**
     * Default constructor.
     */
    public HTMLDocument() {
        System.out.println("Creating HTML document");
    }
    
    /**
     * Set the title of the HTML document.
     * 
     * @param title The title to set
     */
    public void setTitle(String title) {
        this.title = title;
        System.out.println("HTML title set: " + title);
    }
    
    /**
     * Set the body of the HTML document.
     * 
     * @param body The body content to set
     */
    public void setBody(String body) {
        this.body = body;
        System.out.println("HTML body set: " + body.substring(0, Math.min(20, body.length())) + "...");
    }
    
    /**
     * Set the author of the HTML document.
     * 
     * @param author The author to set
     */
    public void setAuthor(String author) {
        this.author = author;
        System.out.println("HTML author set: " + author);
    }
    
    /**
     * Set the conclusion of the HTML document.
     * 
     * @param conclusion The conclusion to set
     */
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
        System.out.println("HTML conclusion set: " + conclusion);
    }
    
    @Override
    public void display() {
        System.out.println("\n<!DOCTYPE html>");
        System.out.println("<html>");
        System.out.println("<head>");
        System.out.println("    <title>" + (title != null ? title : "Untitled Document") + "</title>");
        System.out.println("</head>");
        System.out.println("<body>");
        
        if (title != null) {
            System.out.println("    <h1>" + title + "</h1>");
        }
        
        if (body != null) {
            System.out.println("    <div class=\"content\">");
            System.out.println("        <p>" + body + "</p>");
            System.out.println("    </div>");
        }
        
        if (conclusion != null) {
            System.out.println("    <div class=\"conclusion\">");
            System.out.println("        <h2>Conclusion</h2>");
            System.out.println("        <p>" + conclusion + "</p>");
            System.out.println("    </div>");
        }
        
        if (author != null) {
            System.out.println("    <div class=\"author\">");
            System.out.println("        <p>Written by: " + author + "</p>");
            System.out.println("    </div>");
        }
        
        System.out.println("</body>");
        System.out.println("</html>");
    }
    
    @Override
    public String getType() {
        return "HTML Document";
    }
    
    @Override
    public String getContent() {
        StringBuilder content = new StringBuilder();
        content.append("<!DOCTYPE html>\n");
        content.append("<html>\n");
        content.append("<head>\n");
        content.append("    <title>").append(title != null ? title : "Untitled Document").append("</title>\n");
        content.append("</head>\n");
        content.append("<body>\n");
        
        if (title != null) {
            content.append("    <h1>").append(title).append("</h1>\n");
        }
        
        if (body != null) {
            content.append("    <div class=\"content\">\n");
            content.append("        <p>").append(body).append("</p>\n");
            content.append("    </div>\n");
        }
        
        if (conclusion != null) {
            content.append("    <div class=\"conclusion\">\n");
            content.append("        <h2>Conclusion</h2>\n");
            content.append("        <p>").append(conclusion).append("</p>\n");
            content.append("    </div>\n");
        }
        
        if (author != null) {
            content.append("    <div class=\"author\">\n");
            content.append("        <p>Written by: ").append(author).append("</p>\n");
            content.append("    </div>\n");
        }
        
        content.append("</body>\n");
        content.append("</html>");
        
        return content.toString();
    }
    
    @Override
    public String toString() {
        return "HTMLDocument{" +
                "title='" + title + '\'' +
                ", bodyLength=" + (body != null ? body.length() : 0) +
                ", author='" + author + '\'' +
                ", conclusion='" + conclusion + '\'' +
                '}';
    }
}