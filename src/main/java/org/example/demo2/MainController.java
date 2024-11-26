package org.example.demo2;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    private Library library = new Library();

    @FXML
    private TableView<Document> documentTable;

    public void handleAddDocument() {
        // Code for adding a document
    }

    public void handleRemoveDocument() {
        // Code for removing a document
    }

    public void handleBorrowDocument() {
        // Code for borrowing a document
    }

    public void handleReturnDocument() {
        // Code for returning a document
    }

    public void handleSearchBooks(String query) {
        GoogleBooksAPI googleBooksAPI = new GoogleBooksAPI();

        try {
            String jsonResponse = googleBooksAPI.searchBooks(query);
            List<Document> results = parseBooksFromJson(jsonResponse); // Implement JSON parsing here
            documentTable.getItems().setAll(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<Document, String> idColumn;

    @FXML
    private TableColumn<Document, String> titleColumn;

    @FXML
    private TableColumn<Document, String> authorColumn;

    @FXML
    private TableColumn<Document, String> genreColumn;

    @FXML
    private TableColumn<Document, String> quantityColumn;

    @FXML
    public void initialize() {
        // Bind table columns to Document properties
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        quantityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantity()));
        // Handle genre for Book type
        genreColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Book) {
                return new SimpleStringProperty(((Book) cellData.getValue()).getGenre());
            }
            return new SimpleStringProperty("N/A");
        });
    }

    @FXML
    public void handleSearchBooks() {
        String query = searchField.getText().trim();

        if (query.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a search term.");
            alert.show();
            return;
        }

        GoogleBooksAPI googleBooksAPI = new GoogleBooksAPI();
        try {
            String jsonResponse = googleBooksAPI.searchBooks(query);
            List<Document> results = parseBooksFromJson(jsonResponse);

            if (results.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "No results found.");
                alert.show();
            } else {
                documentTable.getItems().setAll(results);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error fetching data from Google Books API.");
            alert.show();
        }
    }


    private List<Document> parseBooksFromJson(String jsonResponse) {
            List<Document> books = new ArrayList<>();
            // Use Gson or Jackson to parse JSON response
            Gson gson = new Gson();
            JsonObject root = gson.fromJson(jsonResponse, JsonObject.class);
            JsonArray items = root.getAsJsonArray("items");

            if (items != null) {
                for (JsonElement item : items) {
                    JsonObject volumeInfo = item.getAsJsonObject().getAsJsonObject("volumeInfo");

                    String id = item.getAsJsonObject().get("id").getAsString();
                    String title = volumeInfo.has("title") ? volumeInfo.get("title").getAsString() : "Unknown Title";
                    String author = volumeInfo.has("authors")
                            ? volumeInfo.getAsJsonArray("authors").get(0).getAsString()
                            : "Unknown Author";
                    String genre = volumeInfo.has("categories")
                            ? volumeInfo.getAsJsonArray("categories").get(0).getAsString()
                            : "General";

                    books.add(new Book(id, title, author, 1, genre));
                }
            }

            return books;
        }
    }
