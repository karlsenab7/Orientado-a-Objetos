package application.screen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

public class ScreenController {
    private static final HashMap<String, Pane> screenMap = new HashMap<>();
    private static Scene mainScene;

    public ScreenController(){}

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

    public static Pane getPane(String name)
    {
        return screenMap.get(name);
    }

    public static void callPopupWindow(String name, String title)
    {
        String path = "window/";
        try
        {
//            Parent pane = FXMLLoader.load(getClass().getResource( path + name));
            Pane pane = new Pane(screenMap.get(name));
            Stage window = new Stage();
            window.setTitle(title); // Pasang judul
            
            Scene scene = new Scene(pane);
            window.setScene(scene);
            window.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}