package autoecole.autoecole;

import Models.Vehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddVehiculeController implements Initializable {

    @FXML
    TextField txtMatricule;

    @FXML
    TextField txtMarque;

    @FXML
    DatePicker dpDateAssurance;

    @FXML
    DatePicker dpDateVisitTechnique;

    @FXML
    Label lblError;

    BorderPane borderPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void init( BorderPane borderPane){
        this.borderPane=borderPane;
    }

    public void Save(ActionEvent event) throws IOException {
        if(!CheckFields()) {
            lblError.setVisible(true);
            return;
        }
        Vehicule vehicule=new Vehicule(txtMatricule.getText(),txtMarque.getText(),dpDateAssurance.getValue(),dpDateVisitTechnique.getValue());
        LayoutController.vehiculeRepository.Add(vehicule);
        SwitchVehicule(borderPane);
    }

    private void SwitchVehicule(BorderPane borderPane) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("VehiculeView.fxml"));
        Pane view=fxmlLoader.load();
        VehiculeController vehiculeController=fxmlLoader.getController();
        vehiculeController.init(borderPane);
        borderPane.setCenter(view);
    }

    public void Cancel() throws IOException {
        SwitchVehicule(borderPane);
    }

    private boolean CheckFields(){
        boolean b=true;
        if(txtMatricule.getText().trim()==""){
            txtMatricule.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
            b=false;
        }
        else {
            txtMatricule.setStyle(null);
        }

        if(txtMarque.getText().trim()==""){
            txtMarque.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
            b=false;
        }
        else {
            txtMarque.setStyle(null);
        }


        if(dpDateAssurance.getValue()==null){
            dpDateAssurance.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
            b=false;
        }
        else {
            dpDateAssurance.setStyle(null);
        }

        if(dpDateVisitTechnique.getValue()==null){
            dpDateVisitTechnique.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
            b=false;
        }
        else {
            dpDateVisitTechnique.setStyle(null);
        }

        return b;
    }
}
