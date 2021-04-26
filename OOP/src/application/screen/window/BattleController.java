package application.screen.window;

import application.classes.Database;
import application.classes.Engimon;
import application.classes.Battle;
import application.classes.GameManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Label statusLabelEnemy;
    @FXML
    private Label statusLabelOwn;
    @FXML
    private ImageView imageViewEnemy;
    @FXML
    private ImageView imageViewOwn;

    private int activeEngimon;
    private static Engimon enemyEngimon = null;

    public void initialize()
    {
//        imageViewEnemy.setImage(new Image("application/assets/1.png"));
    }

    // Tombol back
    public void handleBackButton(ActionEvent event) throws IOException {
        // Untuk mengganti scene
        // Jika ingin digunakan merujuk ke scene lain, ganti isi getResource()
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void handleFightButton(ActionEvent event) throws IOException
    {
        GameManagement.player.battle(enemyEngimon);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void handleRunButton(ActionEvent event) throws IOException{
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void resetStateBattle(Engimon engimon)
    {
        try {
            enemyEngimon = engimon;
            resetEnemyState();
            resetOwnEngimonState();
        }
        catch (Exception e)
        {
            System.out.println("Exception in resetStateBattle");
            System.out.println(e.getMessage());
        }

    }

    public void resetEnemyState()
    {
//        imageViewEnemy = new ImageView();
//        statusLabelEnemy = new Label();
        String assetPath = "application/assets/";
        imageViewEnemy.setImage(new Image(assetPath + enemyEngimon.get_icon()));
        statusLabelEnemy.setText(
                "Nama : " + enemyEngimon.get_engimon_name() +
                        "\nSpesies : " + enemyEngimon.get_engimon_species() +
                        "\nLevel : " + enemyEngimon.get_level() +
                        "\nElemen Utama : " + enemyEngimon.get_engimon_elements().get(0).get_element() +
                        "\nSkill Pertama : " + enemyEngimon.get_engimon_skills().get(0).getName()

        ); //setText

    }

    public void resetOwnEngimonState()
    {
//        imageViewOwn = new ImageView();
//        statusLabelOwn = new Label();
        String assetPath = "application/assets/";
        Engimon e = GameManagement.player.getActiveEngimon();
        imageViewOwn.setImage(new Image(assetPath + e.get_icon()));
        statusLabelOwn.setText(
                "Nama : " + e.get_engimon_name() +
                        "\nSpesies : " + e.get_engimon_species() +
                        "\nLevel : " + e.get_level() +
                        "\nElemen Utama : " + e.get_engimon_elements().get(0).get_element() +
                        "\nSkill Pertama : " + e.get_engimon_skills().get(0).getName()

        ); //setText

    }

}
