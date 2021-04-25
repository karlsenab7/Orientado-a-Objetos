package application.screen;

import application.classes.Database;
import application.classes.GameManagement;
import application.classes.Skill;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.xml.crypto.Data;

public class MainMenuController {

    @FXML
    private Button exitButton;
    @FXML
    private Button newGameButton;
    @FXML
    private Button continueButton;
    @FXML
    private Button optionButton;

    public void exitButtonActionClick(ActionEvent event)
    {
        System.out.println("Exit");
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void newGameButtonActionClick(ActionEvent event)
    {
        System.out.println("New Game");
        ScreenController.activate("MainDisplay");
    }

    public void continueButtonActionClick(ActionEvent event)
    {
        Skill s = Database.getSkillDBbyID("s2");
//        GameManagement.player.getInventoryEngimon().getInventory().get(0).add_skill(s);
//        System.out.println(GameManagement.player.getInventoryEngimon().getInventory().get(1).get_engimon_skills().get(1).getName());
        GameManagement.player.getInventoryEngimon().getInventory().get(0).set_engimon_name("QQQQ");
//        try
//        {
//            GameManagement.player.getInventorySkill().addInventory(s);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        GameManagement.engimonLiar.get(0).get_position().setX(9999);
//        GameManagement.player.getActiveEngimonPosition().setX(9999);
//        Database.saveEngimonSkillPlayerDB();
//        Database.savePlayerEngimonDB();
//        Database.savePlayerSkillItemDB();
//        Database.saveEngimonLiarDB();
//        Database.savePosPlayerPosActiveDB();

//        Database.saveDatabase();
        System.out.println("Continue");
        ScreenController.activate("MainDisplay");
    }

    public void optionButtonActionClick(ActionEvent event)
    {
        System.out.println("Option");
        ScreenController.activate("Option");
    }
}
