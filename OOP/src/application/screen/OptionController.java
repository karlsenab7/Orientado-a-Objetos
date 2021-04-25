package application.screen;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class OptionController implements Initializable {

    @FXML
    private TextArea optionContentTextArea;
    @FXML
    private Button helpButton;
    @FXML
    private Button legendButton;
    @FXML
    private Button creditsButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setContents("help");
    }

    public void helpButtonActionClick(ActionEvent event)
    {
        System.out.println("Help");
        setContents("help");
    }

    public void legendButtonActionClick(ActionEvent event)
    {
        System.out.println("Legend");
        optionContentTextArea.setText("Legend");
    }
    public void closeButtonActionClick(ActionEvent event)
    {
        System.out.println("Close Option");
        ScreenController.activate("MainMenu");
    }

    public void creditsButtonActionClick(ActionEvent event)
    {
        System.out.println("Credits");
        setContents("credits");
    }

    public void setContents(String name) {

        List<String> contents = getFileContents(name + ".txt");

        if (contents == null)
            return;

        if (contents.size() == 0)
            return;

        optionContentTextArea.clear();
        for (String content : contents) {
            optionContentTextArea.appendText(content + "\n");
        }
    }

    public List<String> getFileContents(String fileName)
    {
        try {
            Scanner sc = new Scanner(new File("src/application/" + fileName));
            List<String> contents = new ArrayList<>();
            while (sc.hasNext())
            {
                contents.add(sc.nextLine());
            }

            return contents;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
