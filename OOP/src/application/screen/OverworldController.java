package application.screen;

import application.classes.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OverworldController implements Initializable {
    //Map
    public TilePane Map;
    public TilePane invent;
    // Untuk ganti scene
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Untuk "Player"
    private Player p;
    //Peta
    private Peta map;

    // Tombol Up
    public void UpButton(ActionEvent event) {
        int oldX = p.getPosition().getX();
        int oldY = p.getPosition().getY();
        p.moveUP();
        map.setCellContent(new Position(oldX,oldY), Content.air);
        map.setCellContent(p.getPosition(), Content.player);
        Map.getChildren().clear();
        loadImage();
    }

    // Tombol Down
    public void DownButton(ActionEvent event) {
        int oldX = p.getPosition().getX();
        int oldY = p.getPosition().getY();
        p.moveDOWN();
        map.setCellContent(new Position(oldX,oldY), Content.air);
        map.setCellContent(p.getPosition(), Content.player);
        Map.getChildren().clear();
        loadImage();
    }

    // Tombol Right
    public void RightButton(ActionEvent event) {
        int oldX = p.getPosition().getX();
        int oldY = p.getPosition().getY();
        p.moveRIGHT();
        map.setCellContent(new Position(oldX,oldY), Content.air);
        map.setCellContent(p.getPosition(), Content.player);
        Map.getChildren().clear();
        loadImage();
    }

    // Tombol Left
    public void LeftButton(ActionEvent event) {
        int oldX = p.getPosition().getX();
        int oldY = p.getPosition().getY();
        p.moveLEFT();
        map.setCellContent(new Position(oldX,oldY), Content.air);
        map.setCellContent(p.getPosition(), Content.player);
        Map.getChildren().clear();
        loadImage();
    }

    // Tombol Exit
    public void handleExitButton(ActionEvent event) throws IOException {
        // Untuk mengganti scene
        // Jika ingin digunakan merujuk ke scene lain, ganti isi getResource()
        ScreenController.activate("MainMenu");
    }

    // Tombol Breeding
    public void handleBreedButton(ActionEvent event) throws IOException {
        // Untuk mengganti scene
        // Jika ingin digunakan merujuk ke scene lain, ganti isi getResource()
        ScreenController.callPopupWindow("BreedScene", "Breed");
    }

    // Tombol Engimon Inventory
    public void handleEngimonInventoryButton(ActionEvent event) throws IOException {
        // Untuk mengganti scene
        // Jika ingin digunakan merujuk ke scene lain, ganti isi getResource()
//        System.out.println("asdsad");
        ScreenController.callPopupWindow("EngimonInventory", "Engimon Inventory");
    }

    public void handleLegendButton(ActionEvent event) throws IOException {
        System.out.println(GameManagement.player.getInventoryEngimon().getInventory().size());
       ScreenController.callPopupWindow("Legend", "Legend");
    }

    public void handleInteractionButton(ActionEvent event) throws  IOException{
        System.out.println(p.getActiveEngimon().get_deskripsi());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Setting inventory
        invent.setPrefColumns(10);
        invent.setPrefRows(10);
        invent.setPrefTileHeight(18);
        invent.setPrefTileWidth(18);
        invent.setVgap(20);
        invent.setHgap(20);
        //Setting Map
        Map.setPrefColumns(25);
        Map.setPrefRows(10);
        Map.setPrefTileHeight(35);
        Map.setPrefTileWidth(35);
        Map.setVgap(5);
        Map.setHgap(5);
        Map.setAlignment(Pos.CENTER);
        Map.setOrientation(Orientation.VERTICAL);
        //Inisialisasi
        map = GameManagement.getPeta();
        p = GameManagement.getPlayer();
//        System.out.println(p.getActiveEngimonPosition().getX()+ p.getActiveEngimonPosition().getY());
        System.out.println(p.getPosition().getX() + p.getPosition().getY());
        loadImage();
        loadEngimon();

    }

    private void loadImage(){
        for (int i = 0; i < map.getSizeX(); i++){
            for(int j = 0; j < map.getSizeY(); j++){
                Image cell;
                switch (map.getCell(i,j).getCellType()){
                    case sea -> {
                        cell = new Image("application/assets/mapTile_187.png");;
                        break;
                    }
                    case tundra -> {
                        cell = new Image("application/assets/mapTile_077.png");;
                        break;
                    }
                    case mountain -> {
                        cell = new Image("application/assets/mapTile_027.png");
                        break;
                    }
                    default -> {
                        cell = new Image("application/assets/mapTile_022.png");
                        break;
                    }
                }
                ImageView im = new ImageView(cell);
                im.fitHeightProperty().bind(Map.tileHeightProperty());
                im.fitWidthProperty().bind(Map.tileWidthProperty());
                StackPane s = new StackPane();
                s.getChildren().add(im);
                Map.getChildren().add(s);
                if(map.getCellContent(i,j) == Content.engimon){
                    Image engi = new Image("application/assets/114-1146777_pixel-art-agumon-pixel-art.png");
                    ImageView im1 = new ImageView(engi);
                    im1.fitHeightProperty().bind(Map.tileHeightProperty());
                    im1.fitWidthProperty().bind(Map.tileWidthProperty());
                    s.getChildren().add(im1);
                }else if (map.getCellContent(i,j) == Content.player){
                    Image player = new Image("application/assets/Pokemon_Trainer_Boy-512.png");
                    ImageView im1 = new ImageView(player);
                    im1.fitHeightProperty().bind(Map.tileHeightProperty());
                    im1.fitWidthProperty().bind(Map.tileWidthProperty());
                    s.getChildren().add(im1);
                }
            }
        }
    }
    public void loadEngimon(){
        invent.getChildren().clear();
        Image engi = new Image("application/assets/114-1146777_pixel-art-agumon-pixel-art.png");
        for(int i = 0; i < p.getInventoryEngimon().getInventory().size();i++){
            Button b = new Button();
            b.setMaxSize(18,18);
            ImageView im = new ImageView(engi);
            im.setFitWidth(15);
            im.setFitHeight(15);
            b.setGraphic(im);
            if(p.getInventoryEngimon().getInventory(i).get_engimon_elements().contains(new Element("Fire"))){
                b.setStyle("-fx-border-color: red");
            }else if(p.getInventoryEngimon().getInventory(i).get_engimon_elements().contains(new Element("Water"))){
                b.setStyle("-fx-border-color: blue");
            }else if(p.getInventoryEngimon().getInventory(i).get_engimon_elements().contains(new Element("Electric"))){
                b.setStyle("-fx-border-color: yellow");
            }else if(p.getInventoryEngimon().getInventory(i).get_engimon_elements().contains(new Element("Ground"))){
                b.setStyle("-fx-border-color: brown");
            }else if(p.getInventoryEngimon().getInventory(i).get_engimon_elements().contains(new Element("Ice"))){
                b.setStyle("-fx-border-color: white");
            }
            invent.getChildren().add(b);
        }
    }
    public void loadSkill(){
        invent.getChildren().clear();
        Image item = new Image("application/assets/1254995-middle.png");
        for(int i = 0; i < p.getInventorySkill().getInventory().size();i++){
            Button b = new Button();
            b.setMaxSize(18,18);
            ImageView im = new ImageView(item);
            im.setFitWidth(15);
            im.setFitHeight(15);
            b.setGraphic(im);
            invent.getChildren().add(b);
        }
    }

//    public void callPopupWindow(String name, String title)
//    {
//        String path = "window/";
//        try
//        {
//            Parent pane = FXMLLoader.load(getClass().getResource( path + name));
//            Stage window = new Stage();
//            window.setTitle(title); // Pasang judul
//
//            Scene scene = new Scene(pane);
//            window.setScene(scene);
//            window.show();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }
}
