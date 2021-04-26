package application.screen.window;

import application.classes.Element;
import application.classes.Skill;
import application.screen.ScreenController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class SkillDetailController {

    // Untuk ganti scene
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Skill skill;
    private boolean learnable;

    @FXML
    private Button close;

    @FXML
    private TextArea skillDesc;
    @FXML
    private Button learn_skill;

    @FXML
    private TextField NBuang;

    @FXML
    private Button Buang;



    public void resetState()
    {
        NBuang.setText("");
        showSkillDesc();
    }

    public void setSkill(Skill skill, boolean learnable) {
        this.skill = skill;
        this.learnable = learnable;
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
        skillDesc.setText(
                nama + el + power + mastery + desc
        );
    }
    public void handleButtonClose(ActionEvent event) throws IOException {
        if (event.getSource() == close) {
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            resetState();
            stage.close();
        }
    }

    public void handleButtonLearnSkill(ActionEvent event) throws IOException {
        if (event.getSource() == learn_skill && learnable) {
            System.out.println("Learn Skill");
            ScreenController.callPopupWindow("LearnSkill", "Learn Skill");
        }
    }

    public void handleButtonBuang(ActionEvent event) throws  IOException {
        if (event.getSource() == Buang && learnable) {
            System.out.println("Membuang Skill");
            String count = NBuang.getText();
            System.out.println("Total yang dibuang : " + count);
        }
    }
}
