package application;

import application.classes.Database;
import application.classes.GameManagement;
import application.screen.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.xml.namespace.QName;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane rootMainMenu = FXMLLoader.load(getClass().getResource("screen/MainMenu.fxml"));
        Pane rootOption = FXMLLoader.load(getClass().getResource("screen/Option.fxml"));

        Scene mainScene = new Scene(rootMainMenu, 1024, 768);

        ScreenController sc = new ScreenController(mainScene);
        sc.addScreen("MainMenu", rootMainMenu);
        sc.addScreen("Option", rootOption);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Gokemon GO!!");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
