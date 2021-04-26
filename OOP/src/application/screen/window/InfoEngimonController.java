package application.screen.window;

import application.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;
//import java.util.SplittableRandom;

public class InfoEngimonController
{
    private Engimon engimon;

    @FXML
    private ImageView imageViewEngimon;
    @FXML
    private TextArea nameText;
    @FXML
    private TextArea parentText;
    @FXML
    private TextArea levelText;
    @FXML
    private TextArea speciesText;
    @FXML
    private TextArea elementText;
    @FXML
    private TextArea liveText;
    @FXML
    private TextArea deskripsiText;
    @FXML
    private TextArea expText;

    @FXML
    private HBox skill1Panel;
    @FXML
    private HBox skill2Panel;
    @FXML
    private HBox skill3Panel;
    @FXML
    private HBox skill4Panel;

    @FXML
    private Button closeButton;
    @FXML
    private Button throwButton;
    @FXML
    private Button setActiveButton;

    @FXML
    private ChoiceBox choiceThrow;

    public void resetState(Engimon e)
    {
        if (e == null)
        {
            System.out.println("Engimon is null in resetState InfoEngimon");
            return;
        }
        engimon = e;
        resetInfoEngimon();
        resetSkillEngimon();
        resetChoiceThrow();
    }

    private void resetChoiceThrow()
    {
        try {

            int idx = GameManagement.player.getInventoryEngimon().getInventory().indexOf(engimon);
            if (idx == -1)
            {
                System.out.println("Engimon cannot found in inventory");
                return;
            }

            choiceThrow.getSelectionModel().clearSelection();
            choiceThrow.getItems().clear();
            int n = GameManagement.player.getInventoryEngimon().getInventoryCount(idx);

            for (int i = 1; i < n+1; i++) {
                choiceThrow.getItems().add(i);
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception in resetChoiceThrow");
            System.out.println(e.getMessage());
        }

    }

    public void resetInfoEngimon()
    {
        String assetPath = "application/assets/";
        try
        {
            imageViewEngimon.setImage(new Image(assetPath + engimon.get_icon()));
            nameText.setText("Name:\n" + engimon.get_engimon_name());
            levelText.setText("Level:\n" + Integer.toString(engimon.get_level()));
            liveText.setText("Live:\n" + Integer.toString(engimon.get_live()));
            deskripsiText.setText( "Description:\n"+ engimon.get_deskripsi());
            speciesText.setText("Species:\n" + engimon.get_engimon_species());
            expText.setText("Exp:\n" + engimon.get_exp());


            List<Element> els = engimon.get_engimon_elements();
            String stringElement = "Elements:\n";
            for (int i = 0; i < els.size(); i++)
            {
                Element el = els.get(i);
                if (i != els.size()-1)
                    stringElement += el.get_element() + " X ";
                else
                    stringElement += el.get_element();
            }
            elementText.setText(stringElement);

            List<String> parents = engimon.get_engimon_parentName();
            String stringParents = "Parents:\n";
            for (int i = 0; i < parents.size(); i++)
            {
                String parent = parents.get(i);
                if (i != parents.size()-1)
                    stringParents += parent + " X ";
                else
                    stringParents += parent;
            }
            parentText.setText(stringParents);
        }
        catch (Exception e)
        {
            System.out.println("Exception in resetInfoEngimon");
            System.out.println(e.getMessage());
        }
    }

    public void resetSkillEngimon()
    {
        String assetPath = "application/assets/";
        try
        {
            List<Skill> skills = engimon.get_engimon_skills();
            skill1Panel.getChildren().clear();
            skill2Panel.getChildren().clear();
            skill3Panel.getChildren().clear();
            skill4Panel.getChildren().clear();
            for(int i = 0; i < 4; i++)
            {

                Skill s = null;
                for(Skill skill : skills)
                {
                    if (skill.getSlot() == i)
                        s = skill;
                }

                if (s != null)
                {
                    SkillButton sb = new SkillButton(s, false);
                    sb.changeSize(50, 50);
                    TextArea info = new TextArea();
                    info.setPrefWidth(260);
                    info.setPrefHeight(50);
                    info.setEditable(false);
                    info.setText("Pow:" + s.getPower() +"/" + "Mas:" + s.getMastery());

                    if (i == 0) {
                        skill1Panel.getChildren().add(sb);
                        skill1Panel.getChildren().add(info);
                    }
                    else if (i == 1) {
                        skill2Panel.getChildren().add(sb);
                        skill2Panel.getChildren().add(info);
                    }
                    else if (i == 2) {
                        skill3Panel.getChildren().add(sb);
                        skill3Panel.getChildren().add(info);
                    }
                    else {
                        skill4Panel.getChildren().add(sb);
                        skill4Panel.getChildren().add(info);
                    }
                }
                else
                {
                    Button sb = new Button();
                    sb.setPrefSize(50, 50);
                    TextArea info = new TextArea();
                    info.setPrefWidth(260);
                    info.setPrefHeight(50);
                    info.setEditable(false);
//                    info.setText("Pow:" + s.getPower() +"/" + "Mas:" + s.getMastery());

                    if (i == 0) {
                        skill1Panel.getChildren().add(sb);
                        skill1Panel.getChildren().add(info);
                    }
                    else if (i == 1) {
                        skill2Panel.getChildren().add(sb);
                        skill2Panel.getChildren().add(info);
                    }
                    else if (i == 2) {
                        skill3Panel.getChildren().add(sb);
                        skill3Panel.getChildren().add(info);
                    }
                    else {
                        skill4Panel.getChildren().add(sb);
                        skill4Panel.getChildren().add(info);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception in resetSkillEngimon");
            System.out.println(e.getMessage());
        }
    }

    public void handleCloseButton(ActionEvent event)
    {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void handleThrowButton(ActionEvent event)
    {
        int idx = choiceThrow.getSelectionModel().getSelectedIndex();
        if (idx == -1)
        {
            System.out.println("Num of throw is not selected");
            return;
        }

        int num = (Integer) choiceThrow.getItems().get(idx);
        int idxEngimon = GameManagement.player.getInventoryEngimon().getInventory().indexOf(engimon);
        if (idxEngimon == -1)
        {
            System.out.println("Engimon is not found");
            return;
        }

        GameManagement.player.discardEngimon(idxEngimon, num);
        handleCloseButton(event);
    }

    public void handleSetActive(ActionEvent event)
    {
        try
        {
            int idx = GameManagement.player.getInventoryEngimon().getInventory().indexOf(engimon);
            if (idx == -1)
            {
                System.out.println("Cannot found engimon");
                return;
            }
            GameManagement.player.switchActivEngimon(idx);
            handleCloseButton(event);
        }
        catch (Exception e)
        {
            System.out.println("Exception in handleSetActive");
            System.out.println(e.getMessage());
        }
    }
}
