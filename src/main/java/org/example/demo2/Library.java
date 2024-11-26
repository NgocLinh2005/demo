package org.example.demo2;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Document> documents = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public void addDocument(Document doc) {
        documents.add(doc);
    }

    public void removeDocument(String id) {
        documents.removeIf(doc -> doc.getId().equals(id));
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User findUser(String id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }
}
