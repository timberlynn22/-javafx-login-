package com.example.hw2;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;

public class HelloController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField stateField;
    @FXML
    private TextField zipField;
    @FXML
    private CheckBox appCheckBox;
    @FXML
    private CheckBox musicCheckBox;
    @FXML
    private ComboBox<String> musicTypeComboBox;
    @FXML
    private RadioButton gameRadio;
    @FXML
    private RadioButton productivityRadio;
    @FXML
    private RadioButton educationRadio;
    @FXML
    private TextField titleField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField accountField;

    @FXML
    private void handleAppCheckbox() {
        if (appCheckBox.isSelected()) {
            musicCheckBox.setDisable(true);
            musicTypeComboBox.setDisable(true);
        } else {
            musicCheckBox.setDisable(false);
            musicTypeComboBox.setDisable(false);
        }
    }

    @FXML
    private void handleMusicCheckbox() {
        if (musicCheckBox.isSelected()) {
            appCheckBox.setDisable(true);
            gameRadio.setDisable(true);
            productivityRadio.setDisable(true);
            educationRadio.setDisable(true);
        } else {
            appCheckBox.setDisable(false);
            gameRadio.setDisable(false);
            productivityRadio.setDisable(false);
            educationRadio.setDisable(false);
        }
    }

    @FXML
    private void onSubmitClick() {
        if (nameField.getText().isEmpty()) {
            show("Name is required", nameField);
            return;
        }
        if (streetField.getText().isEmpty()) {
            show("Street is required", streetField);
            return;
        }
        if (cityField.getText().isEmpty()) {
            show("City is required", cityField);
            return;
        }
        if (stateField.getText().isEmpty()) {
            show("State is required", stateField);
            return;
        }
        if (zipField.getText().isEmpty()) {
            show("Zip is required", zipField);
            return;
        }
        if (!appCheckBox.isSelected() && !musicCheckBox.isSelected()) {
            alert("Please select App or Music.");
            return;
        }
        if (titleField.getText().isEmpty()) {
            show("Title is required", titleField);
            return;
        }
        if (dateField.getText().isEmpty()) {
            show("Date Purchased is required", dateField);
            return;
        }
        if (accountField.getText().isEmpty()) {
            show("Account Number is required", accountField);
            return;
        }

        try {
            FileWriter writer;
            if (appCheckBox.isSelected()) {
                writer = new FileWriter("app.txt", true);
                writer.write("App Acquired:\n");
                writer.write("Type: " + getAppType() + "\n");
                writer.write("Title: " + titleField.getText() + "\n");
                writer.write("Date Purchased: " + dateField.getText() + "\n");
                writer.write("Customer Info:"+"\n");
                writer.write("Name: " + nameField.getText() + "\n");
                writer.write("Street: " + streetField.getText() + "\n");
                writer.write("City: " + cityField.getText() + "\n");
                writer.write("State: " + stateField.getText() + "\n");
                writer.write("Zip: " + zipField.getText() + "\n");
                writer.write("Account #: " + accountField.getText() + "\n");
                writer.write("******************\n");
            } else {
                if (musicTypeComboBox.getValue() == null || musicTypeComboBox.getValue().equals("CHOOSE ONE")) {
                    alert("Please choose a type of music.");
                    return;
                }
                writer = new FileWriter("music.txt", true);
                writer.write("Music Acquired:\n");
                writer.write("Genre: " + musicTypeComboBox.getValue() + "\n");
                writer.write("Title: " + titleField.getText() + "\n");
                writer.write("Date Purchased: " + dateField.getText() + "\n");
                writer.write("Customer Info:"+"\n");
                writer.write("Name: " + nameField.getText() + "\n");
                writer.write("Street: " + streetField.getText() + "\n");
                writer.write("City: " + cityField.getText() + "\n");
                writer.write("State: " + stateField.getText() + "\n");
                writer.write("Zip: " + zipField.getText() + "\n");
                writer.write("Account #: " + accountField.getText() + "\n");
                writer.write("*********************\n");

            }

            writer.close();

            clear();
            nameField.requestFocus();

        } catch (IOException e) {
            alert("Could not write to file.");
        }
    }

    @FXML
    private void onFinishClick() {
        System.exit(0);
    }

    private void show(String msg, TextField field) {
        alert(msg);
        field.requestFocus();
    }

    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private String getAppType() {
        if (gameRadio.isSelected()) return "Game";
        if (productivityRadio.isSelected()) return "Productivity";
        if (educationRadio.isSelected()) return "Education";
        return "None";
    }

    private void clear() {
        nameField.clear();
        streetField.clear();
        cityField.clear();
        stateField.clear();
        zipField.clear();
        appCheckBox.setSelected(false);
        musicCheckBox.setSelected(false);
        musicCheckBox.setDisable(false);
        appCheckBox.setDisable(false);
        musicTypeComboBox.getSelectionModel().clearSelection();
        musicTypeComboBox.setDisable(false);
        gameRadio.setSelected(false);
        productivityRadio.setSelected(false);
        educationRadio.setSelected(false);
        gameRadio.setDisable(false);
        productivityRadio.setDisable(false);
        educationRadio.setDisable(false);
        titleField.clear();
        dateField.clear();
        accountField.clear();
    }
}