package application.screen.window;

import application.classes.Engimon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoseBattleController {

    @FXML
    private ImageView imageViewEngimon;
    @FXML
    private Label statusLabel;
    @FXML
    private Label deadLabel;
    @FXML
    private Button closeButton;

    private Engimon engimon;

    public void resetState(Engimon e)
    {
        engimon = e;
        if (e == null)
        {
            System.out.println("Engimon is null in lose Window");
        }
        resetEngimonState();
    }

    public void resetEngimonState()
    {
        String assetPath = "application/assets/";
        imageViewEngimon.setImage(new Image(assetPath + engimon.get_icon()));
        statusLabel.setText(
                "Name : " + engimon.get_engimon_name() +
                        "\nLevel : " + engimon.get_level() +
                        "\nLive : " + (engimon.get_live()-1)

        );

        if (engimon.get_live()-1 <= 0)
        {
            deadLabel.setText("Dead");
        }
        else
        {
            deadLabel.setText("");
        }
//        System.out.println(deadLabel.getText());
    }

    public void handleCloseButton(ActionEvent event)
    {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

}
