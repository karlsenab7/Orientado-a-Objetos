package application.screen.window;

import application.classes.Engimon;
import application.classes.Breed;
import application.classes.GameManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class BreedController {
    // Data dummy untuk testing
    ArrayList<Engimon> dummy;

    // Untuk memodifikasi value di layar (misal menampilkan info)
    // Cocokkan nama variabel dengan fx:id dan tipenya
    @FXML private ChoiceBox Choice1;
    @FXML private ChoiceBox Choice2;
    @FXML private Label Label1;
    @FXML private Label Label2;

    // Untuk mengambil info dari text field
    // Cocokkan nama variabel dengan fx:id dan tipenya
    @FXML private TextField ChildName;

    // Untuk ganti scene
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Mencatat engimon yang terlibat
    private Engimon Engimon1 = null;
    private Engimon Engimon2 = null;
    private Engimon Child = null;

    // Proses saat awawl scene dibuka
    @FXML
    private void initialize(){

        resetState();

        if (GameManagement.player == null)
        {
            System.out.println("Player is null");
        }
        else
        {
            System.out.println("Set engimon dropdown in Breeding");
            // Memasukkan data ke choice box
            System.out.println("Inventory count : " + GameManagement.player.getInventoryEngimon().getInventory().size());
            for (Engimon x : GameManagement.player.getInventoryEngimon().getInventory()) {
                Choice1.getItems().add(x.get_engimon_name());
                Choice2.getItems().add(x.get_engimon_name());
            }
        }

        Choice1.setValue("Pilih Engimon 1");
        Choice2.setValue("Pilih Engimon 2");

        // Menampilkan informasi engimon
        Choice1.getSelectionModel().selectedItemProperty().addListener(
                (v, oldValue, newValue) -> setLabel(newValue, Label1)
        );
        Choice2.getSelectionModel().selectedItemProperty().addListener(
                (v, oldValue, newValue) -> setLabel(newValue, Label2)
        );
    }

    public void resetState()
    {
        Choice1.getSelectionModel().clearSelection();
        Choice1.getItems().clear();
        Choice2.getSelectionModel().clearSelection();
        Choice2.getItems().clear();
        Label1.setText("");
        Label2.setText("");
        Engimon1 = null;
        Engimon2 = null;
        Child = null;
        System.out.println("Reset Breeding Window");
    }

    // Untuk menampilkan informasi engimon
    private void setLabel(Object newValue, Label label) {
        String valStr = newValue.toString();

        for(Engimon x : GameManagement.player.getInventoryEngimon().getInventory()) {
            if (valStr == x.get_engimon_name()) {
                label.setText(
                        "Nama : " + x.get_engimon_name() +
                                "\nSpesies : " + x.get_engimon_species() +
                                "\nLevel : " + x.get_level() +
                                "\nElemen Utama : " + x.get_engimon_elements().get(0).get_element() +
                                "\nSkill Pertama : " + x.get_engimon_skills().get(0).getName()

                ); //setText

                if (label == Label1) {
                    Engimon1 = x;
                } else {
                    Engimon2 = x;
                }

            } //endif
        } //endfor
    } //endfuct

    // Tombol back
    public void handleCancelButton(ActionEvent event) throws IOException {
        // Untuk mengganti scene
        // Jika ingin digunakan merujuk ke scene lain, ganti isi getResource()
//        root = FXMLLoader.load(getClass().getResource("/Scenes/Overworld.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    // Tombol breed
    public void handleBreedButton(ActionEvent event) {
        // Mengambil data dari text field
        String childName = ChildName.getText();

        if (childName.length() == 0)
            return;

        try {
            // Panggil breed
            if (Engimon1 != null && Engimon2 != null && Engimon1.get_engimon_id() != Engimon2.get_engimon_id()){
                Breed br = new Breed(Engimon1, Engimon2, childName);
                Child = Engimon.clone(br.get_child());
//                if (Child == null)
//                    return;
                GameManagement.getPlayer().getInventoryEngimon().addInventory(Child);
                // Call Engimon Info
                // Close this window
                handleCancelButton(event);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
