package application.screen.window;

import application.classes.GameManagement;
import application.classes.Skill;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
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
    private Image img;

//    @FXML
//    private DocFlavor.URL location;

    @FXML
    private ImageView imgSkill;

    @FXML
    private ComboBox<String> slotSkill;

    @FXML
    private TextArea detailSkill;

    @FXML
    private Button cancel;

    @FXML
    private Button learnSkill;

    @FXML
    void initialize() {
        slotSkill.getSelectionModel().selectedItemProperty().addListener(
                (v, oldValue, newValue) -> showSkillDesc()
        );
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public void showSkillDesc() {
        try {
            if (slotSkill.getSelectionModel().getSelectedItem().endsWith("Empty Slot")) {
                detailSkill.setText("Empty Slot");
                return;
            }
            int skillIdx = slotSkill.getSelectionModel().getSelectedIndex();
            Skill skill = GameManagement.getPlayer().getActiveEngimon().get_engimon_skills().get(skillIdx);
            // menampilkan deskripsi skill pada textArea
            String nama = "Nama : " + skill.getName();
            String el;
            if (skill.getElement().size() > 1) {
                el = "\nElement : " + skill.getElement().get(0).get_element() + " / " + skill.getElement().get(1).get_element();
            } else {
                el = "\nElement : " + skill.getElement().get(0).get_element();
            }
            String power = "\nPower : " + skill.getPower();
            String mastery = "\nMastery : " + skill.getMastery();
            String desc = "\nDescription : " + skill.getDescription();
            detailSkill.setText(
                    nama + el + power + mastery + desc
            );
        } catch (NullPointerException e) {
            System.out.println("Slot belum dipilih");
        }

    }

    public void resetState() {
        slotSkill.getSelectionModel().clearSelection();
        slotSkill.getItems().clear();

        imgSkill.setImage(img);
        detailSkill.setText("Pilih slot terlebih dahulu");

        for (int i = 0; i < 4; i++) {
            slotSkill.getItems().add("Slot " + (i+1) + " - " + "Empty Slot");
        }
        for (Skill s : GameManagement.getPlayer().getActiveEngimon().get_engimon_skills()) {
            if (s.getSlot() == 0) {
                // slot 1
                slotSkill.getItems().set(0, "Slot 1 - " + s.getName());
            } else if (s.getSlot() == 1) {
                //slot 2
                slotSkill.getItems().set(1, "Slot 2 - " + s.getName());
            } else if (s.getSlot() == 2) {
                //slot 2
                slotSkill.getItems().set(1, "Slot 3 - " + s.getName());
            } else if (s.getSlot() == 3) {
                //slot 2
                slotSkill.getItems().set(1, "Slot 4 - " + s.getName());
            }
        }
    }

    public void handleButtonCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        resetState();
        stage.close();
    }

    public void handleButtonLearn(ActionEvent event) throws  IOException {

        int skillIdx = GameManagement.getPlayer().getInventorySkill().getInventory().indexOf(skill);
        int selectedIdx = slotSkill.getSelectionModel().getSelectedIndex();

        if (slotSkill.getSelectionModel().isEmpty()) {
            System.out.println("Slot belum dipilih");
        } else {
            if (slotSkill.getSelectionModel().getSelectedItem().endsWith("Empty Slot")) {
                detailSkill.setText("Empty Slot");
                GameManagement.player.getActiveEngimon().add_skill(this.skill);
//                GameManagement.player.getActiveEngimon().get_engimon_skills().add(this.skill);
            } else {
                GameManagement.player.getActiveEngimon().replace_skill(this.skill, selectedIdx);
//                GameManagement.player.getActiveEngimon().get_engimon_skills().set(selectedIdx, this.skill);
            }
            GameManagement.player.discardSkill(skillIdx, 1);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }

    }

}
