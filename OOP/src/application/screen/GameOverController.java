package application.screen;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class GameOverController {

    @FXML
    private Button backToMenuButton;

    public void backToMenuButtonActionClick(ActionEvent event)
    {
        System.out.println("Back To Main Menu");
        ScreenController.activate("MainMenu");
    }

}
