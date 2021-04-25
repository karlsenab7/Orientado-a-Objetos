package application.screen;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
        System.out.println("Continue");
        ScreenController.activate("MainDisplay");
    }

    public void optionButtonActionClick(ActionEvent event)
    {
        System.out.println("Option");
        ScreenController.activate("Option");
    }
}
