package application.screen.window;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

//import javax.print.DocFlavor;
import java.io.IOException;
import java.util.ResourceBundle;

public class LearnSkillController {
    // Untuk ganti scene
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ResourceBundle resources;

//    @FXML
//    private DocFlavor.URL location;

    @FXML
    private ImageView ImgSkill;

    @FXML
    private ComboBox<String> slotSkill;

    @FXML
    private TextArea detailSkill;

    @FXML
    private Button cancel;

    @FXML
    private Button learnSkill;

    @FXML
    void initialize() {
        assert ImgSkill != null : "fx:id=\"ImgSkill\" was not injected: check your FXML file 'LearnSkill.fxml'.";
        assert slotSkill != null : "fx:id=\"slotSkill\" was not injected: check your FXML file 'LearnSkill.fxml'.";
        assert detailSkill != null : "fx:id=\"detailSkill\" was not injected: check your FXML file 'LearnSkill.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'LearnSkill.fxml'.";
        assert learnSkill != null : "fx:id=\"learnSkill\" was not injected: check your FXML file 'LearnSkill.fxml'.";
        resetState();
    }

    public void resetState() {
        slotSkill.getSelectionModel().clearSelection();
        slotSkill.getItems().clear();

        slotSkill.getItems().add("Slot 1");
        slotSkill.getItems().add("Slot 2");
        slotSkill.getItems().add("Slot 3");
        slotSkill.getItems().add("Slot 4");
    }

    public void handleButtonCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        resetState();
        stage.close();
    }

}
