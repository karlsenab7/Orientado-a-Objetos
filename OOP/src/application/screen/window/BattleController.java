package application.screen.window;

import application.classes.Engimon;
import application.classes.Battle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private int ActiveEngimon;
    private Engimon EnemyEngimon = null;

    // Tombol back
    public void handleBackButton(ActionEvent event) throws IOException {
        // Untuk mengganti scene
        // Jika ingin digunakan merujuk ke scene lain, ganti isi getResource()
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void handleFightButton(ActionEvent event) throws IOException {
        // Inisialisasi terlebih dahulu active engimon dan enemy engimon
        //Battle current_battle = new Battle(this.ActiveEngimon, this.EnemyEngimon);
        //current_battle.fight();
        System.out.println("Battling");
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
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
