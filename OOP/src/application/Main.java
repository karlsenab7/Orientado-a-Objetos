package application;

import application.classes.Database;
import application.classes.GameManagement;
import application.screen.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Database db = new Database();
        GameManagement gm = new GameManagement(false);

        Pane rootMainMenu = FXMLLoader.load(getClass().getResource("screen/MainMenu.fxml"));
        Pane rootGameOver = FXMLLoader.load(getClass().getResource("screen/GameOver.fxml"));
        Pane rootOption = FXMLLoader.load(getClass().getResource("screen/Option.fxml"));
        Pane rootMainDisplay = FXMLLoader.load(getClass().getResource("screen/Overworld.fxml"));

        Pane rootEngimonInventory = FXMLLoader.load(getClass().getResource("screen/window/EngimonInventory.fxml"));
        Pane rootBreedScene = FXMLLoader.load(getClass().getResource("screen/window/BreedScene.fxml"));
        Pane rootLegend = FXMLLoader.load(getClass().getResource("screen/window/Legend.fxml"));
        Pane ParentException = FXMLLoader.load(getClass().getResource("screen/window/ParentLevelException.fxml"));


        Scene mainScene = new Scene(rootMainMenu, 1024, 768);

        ScreenController sc = new ScreenController(mainScene);
        sc.addScreen("MainMenu", rootMainMenu);
        sc.addScreen("GameOver", rootGameOver);
        sc.addScreen("Option", rootOption);
        sc.addScreen("MainDisplay", rootMainDisplay);

        sc.addScreen("EngimonInventory", rootEngimonInventory);
        sc.addScreen("BreedScene", rootBreedScene);
        sc.addScreen("Legend", rootLegend);
        sc.addScreen("ParentLevelException", ParentException);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
