package org.example.demo2;

public class Book extends Document {
    private String genre;

    public Book(String id, String title, String author, int quantity, String genre) {
        super(id, title, author, quantity);
        this.genre = genre;
    }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    @Override
    public String getType() {
        return "Book";
    }
}

