package application.screen;

import application.classes.Database;
import application.classes.GameManagement;
import application.classes.Skill;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
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
        initGameData(true);
        ScreenController.activate("MainDisplay");
    }

    public void continueButtonActionClick(ActionEvent event)
    {
        System.out.println("Continue");
        initGameData(false);
        ScreenController.activate("MainDisplay");
    }

    public void optionButtonActionClick(ActionEvent event)
    {
        System.out.println("Option");
        ScreenController.activate("Option");
    }

    public void initGameData(boolean newGame)
    {
        try {
            Database db = new Database();
            GameManagement gm = new GameManagement(newGame);


            Pane rootGameOver = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
//            System.out.println("dsdas");

            Pane rootMainDisplay = FXMLLoader.load(getClass().getResource("Overworld.fxml"));
            Pane rootSkillDetail = FXMLLoader.load(getClass().getResource("window/SkillDetail.fxml"));
            Pane rootLearnSkill = FXMLLoader.load(getClass().getResource("window/LearnSkill.fxml"));

            Pane rootEngimonInventory = FXMLLoader.load(getClass().getResource("window/EngimonInventory.fxml"));
            Pane rootBreedScene = FXMLLoader.load(getClass().getResource("window/BreedScene.fxml"));

            Pane ParentException = FXMLLoader.load(getClass().getResource("window/ParentLevelException.fxml"));
            Pane rootBattle = FXMLLoader.load(getClass().getResource("window/Battle.fxml"));


            ScreenController sc = new ScreenController();


            sc.addScreen("GameOver", rootGameOver);

            sc.addScreen("MainDisplay", rootMainDisplay);
            sc.addScreen("SkillDetail", rootSkillDetail);
            sc.addScreen("LearnSkill", rootLearnSkill);

            sc.addScreen("EngimonInventory", rootEngimonInventory);
            sc.addScreen("BreedScene", rootBreedScene);

            sc.addScreen("ParentLevelException", ParentException);
            sc.addScreen("Battle", rootBattle);
        }
        catch (Exception e)
        {
            System.out.println("Execption in initGameData");
            System.out.println(e.getMessage());
        }
    }

}
