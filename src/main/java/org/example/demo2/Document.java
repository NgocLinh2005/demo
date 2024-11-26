package org.example.demo2;

public abstract class Document {
    private String id;
    private String title;
    private String author;
    private int quantity;

    public Document(String id, String title, String author, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getQuantity() { return quantity + ""; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public abstract String getType();
}
