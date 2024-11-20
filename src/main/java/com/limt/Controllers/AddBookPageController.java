package com.limt.Controllers;

import com.limt.Lib.Utils;
import com.limt.dbms.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddBookPageController implements Initializable {

    @FXML
    private Button addBookBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TextField bookAuthorField;

    @FXML
    private TextField bookCategoryField;

    @FXML
    private TextField bookIDField;

    @FXML
    private TextField bookISBNField;

    @FXML
    private TextField bookImagePathField;

    @FXML
    private TextField bookPublisherField;

    @FXML
    private TextField bookQuantityField;

    @FXML
    private TextField bookTitleField;

    @FXML
    private Label msgLabel;

    @FXML
    void HandleAddBookBtn(ActionEvent event) {
        try {
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            Connection connection = DatabaseManager.connect();
            assert connection != null;
            connection.setAutoCommit(false);

            String BookID = bookIDField.getText();
            String BookISBN = bookISBNField.getText();
            String BookTitle = bookTitleField.getText();
            String BookAuthor = bookAuthorField.getText();
            String BookCategory = bookCategoryField.getText();
            String BookPublisher = bookPublisherField.getText();
            int BookQuantity = Integer.parseInt(bookQuantityField.getText());
            String BookImagePath = bookImagePathField.getText();

            String query = "INSERT INTO Book VALUES(? ,? , ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, BookID);
            preparedStatement.setString(2, BookISBN);
            preparedStatement.setString(3, BookTitle);
            preparedStatement.setString(4, BookAuthor);
            preparedStatement.setString(5, BookCategory);
            preparedStatement.setString(6, BookPublisher);
            preparedStatement.setInt(7, BookQuantity);
            preparedStatement.setInt(8, BookQuantity);
            preparedStatement.setString(9, BookImagePath);

            preparedStatement.execute();
            connection.commit();
            msgLabel.setText("Successfully Added Book");
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

    @FXML
    void HandleBackToDashboard(ActionEvent event) {

    }

    @FXML
    void HandleBrowseLink(ActionEvent event) {

    }

    @FXML
    void HandleClearAllField(ActionEvent event) {
        bookIDField.clear();
        bookISBNField.clear();
        bookTitleField.clear();
        bookAuthorField.clear();
        bookCategoryField.clear();
        bookPublisherField.clear();
        bookQuantityField.clear();
        bookImagePathField.clear();
    }

    @FXML
    void HandleScanISBN(ActionEvent event) throws IOException{
        Utils.HandleAddPage("/fxml/ScanISBNPage.fxml");
    }

    @FXML
    void HandleAddBookWithApiBtn(ActionEvent event) throws IOException {
        Utils.HandleAddPage("/fxml/AddBookWithApiPage.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HandleClearAllField(null);
    }
}
