package application.classes;

import application.screen.window.InfoEngimonController;
import application.screen.window.WinBattleController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class EngimonButton extends Button {
    private String assetsPath = "application/assets/";
    private Engimon engimon;
    private ImageView icon;

    public EngimonButton(Engimon engimonTemp)
    {
        super();
        engimon = engimonTemp;
        this.setMaxSize(18, 18);

        String hexColor = getHexColor(engimon.get_engimon_elements().get(0));
        this.setStyle("-fx-background-color: " + hexColor + ";");

        icon = new ImageView(new Image(assetsPath + engimon.get_icon()));
        icon.setFitWidth(15);
        icon.setFitHeight(15);
        this.setGraphic(icon);


        this.setOnAction(e -> engimonButtonActionClick());

    }

    public void engimonButtonActionClick()
    {
        showEngimonInfo();
    }

    public void showEngimonInfo()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/screen/window/InfoEngimon.fxml"));
            Parent root = (Parent) loader.load();
            InfoEngimonController ic = loader.getController();
            ic.resetState(engimon);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e)
        {
            System.out.println("Exception in showEngimonInfo EngimonButton");
            System.out.println(e.getMessage());
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

    public void changeSize(double w, double h)
    {
        this.setMaxSize(w, h);
        this.icon.setFitWidth(w-3);
        this.icon.setFitHeight(h-3);
        this.setPrefSize(w, h);

    }

}
