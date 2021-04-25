package application.classes;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class EngimonButton extends Button {
    private String assetsPath = "application/assets/";
    private Engimon engimon;
    private ImageView icon;

    public EngimonButton(Engimon engimonTemp)
    {
        super();
        engimon = engimonTemp;
        this.setMaxSize(18, 18);
        icon = new ImageView(new Image(assetsPath + engimon.get_icon()));
        icon.setFitWidth(15);
        icon.setFitHeight(15);
        this.setGraphic(icon);
        if(engimon.get_engimon_elements().contains(new Element("Fire"))){
            this.setStyle("-fx-border-color: red");
        }else if(engimon.get_engimon_elements().contains(new Element("Water"))){
            this.setStyle("-fx-border-color: blue");
        }else if(engimon.get_engimon_elements().contains(new Element("Electric"))){
            this.setStyle("-fx-border-color: yellow");
        }else if(engimon.get_engimon_elements().contains(new Element("Ground"))){
            this.setStyle("-fx-border-color: brown");
        }else if(engimon.get_engimon_elements().contains(new Element("Ice"))){
            this.setStyle("-fx-border-color: white");
        }

        this.setOnAction(e -> engimonButtonActionClick());

    }

    public void engimonButtonActionClick()
    {
        System.out.println("Call engimon info");
    }

}
