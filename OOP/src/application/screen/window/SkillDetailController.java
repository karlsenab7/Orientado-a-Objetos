package application.screen.window;

import application.classes.Skill;
import application.screen.ScreenController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class SkillDetailController {

    // Untuk ganti scene
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static Skill skill;

    @FXML
    private Button close;

    @FXML
    private Button learn_skill;

    @FXML
    private TextField NBuang;

    @FXML
    private Button Buang;



    public void resetState()
    {
        NBuang.setText("");
    }


    public void handleButtonClose(ActionEvent event) throws IOException {
        if (event.getSource() == close) {
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            resetState();
            stage.close();
        }
    }

    public void handleButtonLearnSkill(ActionEvent event) throws IOException {
        if (event.getSource() == learn_skill) {
            System.out.println("Learn Skill");
            ScreenController.callPopupWindow("LearnSkill", "Learn Skill");
        }
    }

    public void handleButtonBuang(ActionEvent event) throws  IOException {
        if (event.getSource() == Buang) {
            System.out.println("Membuang Skill");
            String count = NBuang.getText();
            System.out.println("Total yang dibuang : " + count);
        }
    }
}
