package application.screen.window;

import application.classes.Skill;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

//import javax.print.DocFlavor;
import java.io.IOException;
import java.util.ResourceBundle;

public class LearnSkillController {
    // Untuk ganti scene
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Skill skill;

    @FXML
    private ResourceBundle resources;

//    @FXML
//    private DocFlavor.URL location;

    @FXML
    private ImageView ImgSkill;

    @FXML
    private ComboBox<String> slotSkill;

    @FXML
    private TextArea detailSkill;

    @FXML
    private Button cancel;

    @FXML
    private Button learnSkill;

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public void showSkillDesc() {
        // menampilkan deskripsi skill pada textArea
        String nama = "Nama : " + this.skill.getName();
        String el;
        if (this.skill.getElement().size() > 1) {
            el = "\nElement : " + this.skill.getElement().get(0).get_element() + " / " + this.skill.getElement().get(1).get_element();
        } else {
            el = "\nElement : " + this.skill.getElement().get(0).get_element();
        }
        String power = "\nPower : " + this.skill.getPower();
        String mastery = "\nMastery : " + this.skill.getMastery();
        String desc = "\nDescription : " + this.skill.getDescription();
        detailSkill.setText(
                nama + el + power + mastery + desc
        );
    }

    public void resetState() {
        slotSkill.getSelectionModel().clearSelection();
        slotSkill.getItems().clear();

        slotSkill.getItems().add("Slot 1");
        slotSkill.getItems().add("Slot 2");
        slotSkill.getItems().add("Slot 3");
        slotSkill.getItems().add("Slot 4");

        showSkillDesc();
    }

    public void handleButtonCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        resetState();
        stage.close();
    }

}
