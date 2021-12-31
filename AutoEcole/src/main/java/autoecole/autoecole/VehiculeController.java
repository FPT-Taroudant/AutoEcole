package autoecole.autoecole;

import Models.Vehicule;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class VehiculeController implements Initializable {

    @FXML
    TableView<Vehicule> vehicules_table;

    @FXML
    TableColumn<Vehicule, String> num_matricule;

    @FXML
    TableColumn<Vehicule, String> marque;

    @FXML
    TableColumn<Vehicule, LocalDate> date_assurance;

    @FXML
    TableColumn<Vehicule, LocalDate> date_visite_technique;


    BorderPane borderPane;

    public void init(BorderPane borderPane){
        num_matricule.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("num_matricule"));
        marque.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("marque"));
        date_assurance.setCellValueFactory(new PropertyValueFactory<Vehicule, LocalDate>("date_assurance"));
        date_visite_technique.setCellValueFactory(new PropertyValueFactory<Vehicule, LocalDate>("date_visite_technique"));

        vehicules_table.setItems(FXCollections.observableArrayList(LayoutController.vehiculeRepository.List()));
        borderPane.setOnMouseClicked(event->{vehicules_table.getSelectionModel().clearSelection();});
        this.borderPane=borderPane;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void Ajouter(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("AddVehiculeView.fxml"));
        Pane view=fxmlLoader.load();
        AddVehiculeController addVehiculeController=fxmlLoader.getController();
        addVehiculeController.init(borderPane);
        borderPane.setCenter(view);
    }


    public void Delete(ActionEvent event){
        Vehicule v=vehicules_table.getSelectionModel().getSelectedItem();
        if(v!=null){
            LayoutController.vehiculeRepository.Delete(v.getNum_matricule());
            vehicules_table.getItems().remove(v);
        }
    }

    public void Update(ActionEvent event) throws IOException {
        Vehicule v=vehicules_table.getSelectionModel().getSelectedItem();
        if(v!=null){
            SwitchUpdateVehicule(borderPane,v);
        }
    }

    private void SwitchUpdateVehicule(BorderPane borderPane,Vehicule v) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("UpdateVehicule.fxml"));
        Pane view=fxmlLoader.load();
        UpdateVehiculeController updateVehiculeController=fxmlLoader.getController();
        updateVehiculeController.init(borderPane,v);
        borderPane.setCenter(view);
    }


}
