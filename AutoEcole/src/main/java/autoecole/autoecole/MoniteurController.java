package autoecole.autoecole;

import Models.Moniteur;
import Models.Type;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDate;

public class MoniteurController {
    @FXML
    TableView<Moniteur> moniteurs_table;

    @FXML
    TableColumn<Moniteur, String> nom_moniteur;

    @FXML
    TableColumn<Moniteur, String> prenom_moniteur;

    @FXML
    TableColumn<Moniteur, LocalDate> date_inscription_moniteur;

    @FXML
    TableColumn<Moniteur, Type> type_moniteur;

    @FXML
    TableColumn<Moniteur, String> email_moniteur;

    BorderPane borderPane;

    public void init(BorderPane borderPane){
        nom_moniteur.setCellValueFactory(new PropertyValueFactory<Moniteur, String>("nom"));
        prenom_moniteur.setCellValueFactory(new PropertyValueFactory<Moniteur, String>("prenom"));
        date_inscription_moniteur.setCellValueFactory(new PropertyValueFactory<Moniteur, LocalDate>("date_debut"));
        type_moniteur.setCellValueFactory(new PropertyValueFactory<Moniteur, Type>("type_moniteur"));
        email_moniteur.setCellValueFactory(new PropertyValueFactory<Moniteur, String>("email"));

        moniteurs_table.setItems(FXCollections.observableArrayList(LayoutController.moniteurRepository.List()));
        borderPane.setOnMouseClicked(event->{moniteurs_table.getSelectionModel().clearSelection();});
        this.borderPane=borderPane;
    }

    public void Ajouter(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("AddMoniteurView.fxml"));
        Pane view=fxmlLoader.load();
        AddMoniteurController addMoniteurController=fxmlLoader.getController();
        addMoniteurController.init(borderPane);
        borderPane.setCenter(view);
    }


    public void Delete(ActionEvent event){
        Moniteur c=moniteurs_table.getSelectionModel().getSelectedItem();
        if(c!=null){
            LayoutController.moniteurRepository.Delete(c.getId_moniteur());
            moniteurs_table.getItems().remove(c);
        }
    }

    public void Update(ActionEvent event) throws IOException {
        Moniteur m=moniteurs_table.getSelectionModel().getSelectedItem();
        if(m!=null){
            FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("UpdateMoniteur.fxml"));
            Pane view=fxmlLoader.load();
            UpdateMoniteurController updateMoniteurController=fxmlLoader.getController();
            updateMoniteurController.init(borderPane,m);
            borderPane.setCenter(view);
        }
    }
}
