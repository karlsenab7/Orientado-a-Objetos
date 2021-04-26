package application.screen.window;

import application.classes.GameManagement;
import application.classes.Skill;
import application.screen.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.IOException;


public class SkillDetailController {

    // Untuk ganti scene
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Skill skill;
    private Integer NItem;
    private boolean learnable;

    @FXML
    private Button close;

    @FXML
    private TextArea skillDesc;

    @FXML
    private ImageView img;

    @FXML
    private Button learn_skill;

    @FXML
    private ComboBox<Integer> NBuang;

    @FXML
    private Button Buang;

    public void resetState()
    {
        NBuang.getSelectionModel().clearSelection();
        NBuang.getItems().clear();

        String assetPath = "application/assets/";
        img.setImage(new Image(assetPath + skill.getIcon()));

        showSkillDesc();

        if (!learnable || NItem == -1)
            return;
        for (int i = 1; i <= NItem; i++) {
            NBuang.getItems().add(i);
        }



    }

    public void setSkill(Skill skill, boolean learnable) {
        this.skill = skill;
        this.learnable = learnable;
    }

    public void setNItem(Integer NItem) {
        this.NItem = NItem;
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
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        resetState();
        stage.close();
    }

    public void handleButtonLearnSkill(ActionEvent event) throws IOException {
//        ScreenController.callPopupWindow("LearnSkill", "Learn Skill");
        if (!learnable || GameManagement.player.getActiveEngimonIdx() == -1) {
            System.out.println("Cannot learn");
            return;
        }
        System.out.println("Learn Skill");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/screen/window/LearnSkill.fxml"));
            Parent root = (Parent) loader.load();
            LearnSkillController lc = loader.getController();
            lc.setSkill(skill);
            lc.setImg(img.getImage());
            lc.resetState();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            handleButtonClose(event);
        } catch (IOException e) {
            System.out.println("Couldn't load file :(");
            e.printStackTrace();
        }

    }

    public void handleButtonBuang(ActionEvent event) throws  IOException {
        if (event.getSource() == Buang && NItem != -1) {
//            System.out.println("Membuang Skill");
            int selectedIdx = NBuang.getSelectionModel().getSelectedIndex();
            if (selectedIdx == -1)
                return;
            Integer selectedVal = NBuang.getItems().get(selectedIdx); // total yang dibuang
//            System.out.println("Total yang dibuang : " + selectedVal);
            int skillIdx = GameManagement.getPlayer().getInventorySkill().getInventory().indexOf(skill);

            GameManagement.player.discardSkill(skillIdx, selectedVal);
            handleButtonClose(event);
        }
    }
}
