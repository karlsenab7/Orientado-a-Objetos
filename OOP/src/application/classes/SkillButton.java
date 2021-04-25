package application.classes;

import application.screen.ScreenController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public SkillButton(Skill skillTemp)
    {
        super();
        this.setMaxSize(18, 18);
        this.skill = skillTemp;
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
    }
}
