package org.example.demo2;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private List<Document> borrowedDocuments;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedDocuments = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<Document> getBorrowedDocuments() { return borrowedDocuments; }

    public void borrowDocument(Document doc) {
        borrowedDocuments.add(doc);
    }

    public void returnDocument(Document doc) {
        borrowedDocuments.remove(doc);
    }
}
