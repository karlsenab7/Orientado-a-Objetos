package application.screen.window;

import application.classes.Engimon;
import application.classes.EngimonButton;
import application.classes.Skill;
import application.classes.SkillButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class WinBattleController
{
    private Engimon engimonReward;
    private Skill skillReward;

    @FXML
    private VBox rewardPanel;

    public void resetState(Engimon e, Skill s)
    {
        engimonReward = e;
        skillReward = s;
        resetRewardPanel();
    }

    public void resetRewardPanel()
    {
        if (engimonReward == null)
        {
            System.out.println("Engimon Reward is null");
        }
        else
        {
            EngimonButton eb = new EngimonButton(engimonReward);
            eb.changeSize(54, 54);
            rewardPanel.getChildren().add(eb);
        }

        if (skillReward == null)
        {
            System.out.println("Skill reward is null");
        }
        else
        {
            SkillButton sb = new SkillButton(skillReward, false);
            sb.changeSize(54, 54);
            rewardPanel.getChildren().add(sb);
        }
    }

    public void handleCloseButton(ActionEvent event)
    {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
