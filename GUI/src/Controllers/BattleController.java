package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BattleController {

    // Untuk ganti scene
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button fightbutton;
    @FXML
    private Button runbutton;
    @FXML
    private Label Status1;
    @FXML
    private Label Status2;

    private int ActiveEngimon = null;
    private Engimon EnemyEngimon = null;

    // Tombol back
    public void handleBackButton(ActionEvent event) throws IOException {
        // Untuk mengganti scene
        // Jika ingin digunakan merujuk ke scene lain, ganti isi getResource()
        root = FXMLLoader.load(getClass().getResource("/Scenes/Overworld.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void handleFightButton(ActionEvent event) throws IOException {
        // Inisialisasi terlebih dahulu active engimon dan enemy engimon
        Battle current_battle = new Battle(this.ActiveEngimon, this.EnemyEngimon);
        current_battle.fight();
        // Yang bawah belum bener implementasinya, baru gambaran kasar
//        if(current_battle.fight() == win){
//            root = FXMLLoader.load(getClass().getResource("/Scenes/WinBattle.fxml"));
//        } else {
//            root = FXMLLoader.load(getClass().getResource("/Scenes/LoseBattle.fxml"));
//        }
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

    public void handleRunButton(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/Scenes/Overworld.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
