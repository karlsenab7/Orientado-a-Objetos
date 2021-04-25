package application.screen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ScreenController {
    private static final HashMap<String, Pane> screenMap = new HashMap<>();
    private static Scene mainScene;

    public ScreenController(Scene mainSceneTemp) {
        mainScene = mainSceneTemp;
    }

    public void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    public void removeScreen(String name){
        screenMap.remove(name);
    }

    public static void activate(String name){
        mainScene.setRoot(screenMap.get(name));
    }
    public static Pane getWindow(String name)
    {
        return  screenMap.get(name);
    }
}