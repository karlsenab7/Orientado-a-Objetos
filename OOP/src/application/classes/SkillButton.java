package application.classes;


import application.screen.window.SkillDetailController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import application.screen.ScreenController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import application.screen.ScreenController;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

//invent.getChildren().clear();
//        Image item = new Image("application/assets/s1.png");
//        for(int i = 0; i < p.getInventorySkill().getInventory().size();i++){
//        Button b = new Button();
//        b.setMaxSize(18,18);
//        ImageView im = new ImageView(item);
//        im.setFitWidth(15);
//        im.setFitHeight(15);
//        b.setGraphic(im);
//        invent.getChildren().add(b);
//        }

public class SkillButton extends Button
{
    private String assetsPath = "application/assets/";
    private Skill skill;
    private ImageView icon;

    public SkillButton(Skill skillTemp, boolean learnable)
    {
        super();
        this.setMaxSize(18, 18);
        this.skill = skillTemp;
        String hexColor = getHexColor(skill.getElement().get(0));
        this.setStyle("-fx-background-color: " + hexColor + ";");
        try {
            icon = new ImageView(new Image(assetsPath + skill.getIcon()));
            icon.setFitWidth(15);
            icon.setFitHeight(15);
            this.setGraphic(icon);
        }
        catch (Exception e)
        {
            System.out.println("Cannot open skill icon" + skill.getIcon());
            System.out.println(e.getMessage());
        }

        this.setOnAction(e -> skillButtonActionClick());
    }

    public void skillButtonActionClick()
    {
        System.out.println("Call skill info window");
//        ScreenController.callPopupWindow("SkillDetail", "Skill Detail");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/screen/window/SkillDetail.fxml"));
            Parent root = (Parent) loader.load();
            SkillDetailController sc = loader.getController();
            sc.setSkill(skill);
            sc.setNItem(getSkillCount(this.skill));
            sc.resetState();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Couldn't load file :(");
            e.printStackTrace();
        }

    }

    public String getHexColor(Element element)
    {
        String electricHex = "#ebf700";
        String waterHex = "#00cbff";
        String fireHex  = "#ff0000";
        String iceHex = "#c8f4ff";
        String groundHex = "#ae6900";
        return switch (element.get_element()) {
            case "electric" -> electricHex;
            case "water" -> waterHex;
            case "fire" -> fireHex;
            case "ice" -> iceHex;
            default -> groundHex;
        };
    }

    public void changeSize(int i, int i1) {
        this.setMaxSize(i, i1);
        this.icon.setFitHeight(i1-3);
        this.icon.setFitWidth(i-3);
        this.setPrefSize(i, i1);
    }

    public Integer getSkillCount(Skill skill) {
        Integer skillIdx = GameManagement.getPlayer().getInventorySkill().getInventory().indexOf(skill);
        Integer count = GameManagement.getPlayer().getInventorySkill().getInventoryCount(skillIdx);
        return count;
    }
}
