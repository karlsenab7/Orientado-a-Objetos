package application;

import application.screen.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane rootMainMenu = FXMLLoader.load(getClass().getResource("screen/MainMenu.fxml"));
        Pane rootGameOver = FXMLLoader.load(getClass().getResource("screen/GameOver.fxml"));
        Pane rootOption = FXMLLoader.load(getClass().getResource("screen/Option.fxml"));
        Pane rootMainDisplay = FXMLLoader.load(getClass().getResource("screen/Overworld.fxml"));

        Pane rootEngimonInventory = FXMLLoader.load(getClass().getResource("screen/window/EngimonInventory.fxml"));

        Scene mainScene = new Scene(rootMainMenu, 1024, 768);

        ScreenController sc = new ScreenController(mainScene);
        sc.addScreen("MainMenu", rootMainMenu);
        sc.addScreen("GameOver", rootGameOver);
        sc.addScreen("Option", rootOption);
        sc.addScreen("MainDisplay", rootMainDisplay);
        sc.addScreen("EngimonInventory", rootEngimonInventory);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
