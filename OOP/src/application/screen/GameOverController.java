package application.screen;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameOverController {

    @FXML
    private Button backToMenuButton;

    public void backToMenuButtonActionClick(ActionEvent event)
    {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

}
