package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MainMenuController {

    // Untuk ganti scene
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML private GridPane scenePane; // Cocokkan dengan tipe Pane yang dibuat dan fx:id nya

    // Tombol Play
    public void handlePlayButton(ActionEvent event) throws IOException {

        // Untuk mengganti scene
        // Jika ingin digunakan merujuk ke scene lain, ganti isi getResource()
        root = FXMLLoader.load(getClass().getResource("/Scenes/Overworld.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Tombol Exit
    public void handleExitButton(ActionEvent event) {

        // Memberi popup alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit!");
        alert.setContentText("Are you sure you want to exit?");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("You successfully exited!");

            // Menutup program
            stage.close();
        }
    }
}
