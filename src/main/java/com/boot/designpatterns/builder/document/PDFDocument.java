package com.boot.designpatterns.builder.document;

/**
 * PDF document implementation of the IDocument interface.
 * This class represents a PDF document with title, body, author, and conclusion sections.
 */
public class PDFDocument implements IDocument {
    private String title;
    private String body;
    private String author;
    private String conclusion;

    /**
     * Default constructor.
     */
    public PDFDocument() {
        System.out.println("Creating PDF document");
    }

    /**
     * Set the title of the PDF document.
     * 
     * @param title The title to set
     */
    public void setTitle(String title) {
        this.title = title;
        System.out.println("PDF title set: " + title);
    }

    /**
     * Set the body of the PDF document.
     * 
     * @param body The body content to set
     */
    public void setBody(String body) {
        this.body = body;
        System.out.println("PDF body set: " + body.substring(0, Math.min(20, body.length())) + "...");
    }

    /**
     * Set the author of the PDF document.
     * 
     * @param author The author to set
     */
    public void setAuthor(String author) {
        this.author = author;
        System.out.println("PDF author set: " + author);
    }

    /**
     * Set the conclusion of the PDF document.
     * 
     * @param conclusion The conclusion to set
     */
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
        System.out.println("PDF conclusion set: " + conclusion);
    }

    @Override
    public void display() {
        System.out.println("\n%PDF-1.7");
        System.out.println("% PDF Document Simulation");
        System.out.println("1 0 obj");
        System.out.println("<<");
        System.out.println("  /Type /Catalog");
        System.out.println("  /Pages 2 0 R");
        System.out.println(">>");
        System.out.println("endobj");

        System.out.println("\n% Document Content");
        System.out.println("3 0 obj");
        System.out.println("<<");
        System.out.println("  /Title (" + (title != null ? title : "Untitled Document") + ")");

        if (author != null) {
            System.out.println("  /Author (" + author + ")");
        }

        System.out.println("  /Content (");

        if (title != null) {
            System.out.println("    " + title);
            StringBuilder titleUnderline = new StringBuilder("    ");
            for (int i = 0; i < title.length(); i++) {
                titleUnderline.append("=");
            }
            System.out.println(titleUnderline.toString());
        }

        if (body != null) {
            System.out.println("\n    " + body.replace("\n", "\n    "));
        }

        if (conclusion != null) {
            System.out.println("\n    Conclusion:");
            StringBuilder conclusionLine = new StringBuilder("    ");
            for (int i = 0; i < 11; i++) {
                conclusionLine.append("-");
            }
            System.out.println(conclusionLine.toString());
            System.out.println("    " + conclusion);
        }

        System.out.println("  )");
        System.out.println(">>");
        System.out.println("endobj");

        System.out.println("\n% End of PDF");
    }

    @Override
    public String getType() {
        return "PDF Document";
    }

    @Override
    public String getContent() {
        StringBuilder content = new StringBuilder();
        content.append("%PDF-1.7\n");
        content.append("% PDF Document Simulation\n");
        content.append("1 0 obj\n");
        content.append("<<\n");
        content.append("  /Type /Catalog\n");
        content.append("  /Pages 2 0 R\n");
        content.append(">>\n");
        content.append("endobj\n\n");

        content.append("% Document Content\n");
        content.append("3 0 obj\n");
        content.append("<<\n");
        content.append("  /Title (").append(title != null ? title : "Untitled Document").append(")\n");

        if (author != null) {
            content.append("  /Author (").append(author).append(")\n");
        }

        content.append("  /Content (\n");

        if (title != null) {
            content.append("    ").append(title).append("\n");
            content.append("    ");
            for (int i = 0; i < title.length(); i++) {
                content.append("=");
            }
            content.append("\n");
        }

        if (body != null) {
            content.append("\n    ").append(body.replace("\n", "\n    ")).append("\n");
        }

        if (conclusion != null) {
            content.append("\n    Conclusion:\n");
            content.append("    ");
            for (int i = 0; i < 11; i++) {
                content.append("-");
            }
            content.append("\n");
            content.append("    ").append(conclusion).append("\n");
        }

        content.append("  )\n");
        content.append(">>\n");
        content.append("endobj\n\n");

        content.append("% End of PDF");

        return content.toString();
    }

    @Override
    public String toString() {
        return "PDFDocument{" +
                "title='" + title + '\'' +
                ", bodyLength=" + (body != null ? body.length() : 0) +
                ", author='" + author + '\'' +
                ", conclusion='" + conclusion + '\'' +
                '}';
    }
}
