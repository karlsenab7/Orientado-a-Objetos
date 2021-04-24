
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load scene utama
        // Jika ingin digunakan merujuk ke scene lain, ganti isi getResource()
        Parent root = FXMLLoader.load(getClass().getResource("Scenes/MainMenu.fxml"));
        primaryStage.setTitle("Engimon"); // Pasang judul

        Scene mainMenu = new Scene(root);
        primaryStage.setScene(mainMenu);
        primaryStage.show();

        // Untuk popup saat menutup program
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            exit(primaryStage);
        });

    }

    // Menutup program
    public void exit(Stage stage){

        // Memberi popup alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit!");
        alert.setContentText("Are you sure you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK){
            System.out.println("You successfully exited");

            // Menutup program
            stage.close();
        }
    }


    public static void main(String[] args) { launch(args); }
}
