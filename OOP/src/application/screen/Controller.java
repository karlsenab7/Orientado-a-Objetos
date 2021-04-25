package application.screen;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.Image ;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Button;

public class Controller implements Initializable {
    public TilePane Map;
    public TilePane invent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        invent.setPrefColumns(10);
        invent.setPrefRows(10);
        invent.setPrefTileHeight(18);
        invent.setPrefTileWidth(18);
        invent.setVgap(20);
        invent.setHgap(20);
        loadImage();
        loadEngimon();
    }

    private void loadImage(){
        Image img = new Image("grass.png");
        Map.setPrefColumns(25);
        Map.setPrefRows(10);
        Map.setPrefTileHeight(33);
        Map.setPrefTileWidth(33);
        Map.setVgap(5);
        Map.setHgap(5);
        Map.setAlignment(Pos.CENTER);
        for (int i = 0; i < 10; i++){
            for(int j = 0; j < 25; j++){
                ImageView im = new ImageView(img);
                im.fitHeightProperty().bind(Map.tileHeightProperty());
                im.fitWidthProperty().bind(Map.tileWidthProperty());
                StackPane s = new StackPane();
                s.getChildren().add(im);
                Map.getChildren().add(s);
            }
        }
        Image engi = new Image("1.png");
        ImageView im = new ImageView(engi);
        im.fitHeightProperty().bind(Map.tileHeightProperty());
        im.fitWidthProperty().bind(Map.tileWidthProperty());
        StackPane s = (StackPane) Map.getChildren().get(0);
        s.getChildren().add(im);
    }
    public void loadEngimon(){
        invent.getChildren().clear();
        Image engi = new Image("1.png");
        for(int i = 0; i < 10;i++){
            Button b = new Button();
            b.setMaxSize(18,18);
            ImageView im = new ImageView(engi);
            im.setFitWidth(15);
            im.setFitHeight(15);
            b.setGraphic(im);
            b.setStyle("-fx-border-color: red");
            invent.getChildren().add(b);
        }
    }
    public void loadSkill(){
        invent.getChildren().clear();
        Image item = new Image("s1.png");
        for(int i = 0; i < 10;i++){
            Button b = new Button();
            b.setMaxSize(18,18);
            ImageView im = new ImageView(item);
            im.setFitWidth(15);
            im.setFitHeight(15);
            b.setGraphic(im);
            invent.getChildren().add(b);
        }
    }
}
