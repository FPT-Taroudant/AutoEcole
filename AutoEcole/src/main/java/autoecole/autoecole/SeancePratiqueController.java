package autoecole.autoecole;

import Models.*;
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

public class SeancePratiqueController implements Initializable {

    @FXML
    private ComboBox<Moniteur> cbMoniteur=new ComboBox<>();

    @FXML
    private ComboBox<Vehicule> cbVehicule=new ComboBox<>();

    @FXML
    private TableColumn<Seance_Pratique, LocalDate> date_seance;

    @FXML
    private DatePicker dpDateSeance;

    @FXML
    private TableColumn<Seance_Pratique, Moniteur> moniteur;

    @FXML
    private TableColumn<Seance_Pratique, String> vehicule;

    @FXML
    private TableView<Seance_Pratique> pratique_table;


    BorderPane borderPane;
    Candidat selectedCandidat;


    public void init(BorderPane borderPane,Candidat selectedCandidat){
        date_seance.setCellValueFactory(new PropertyValueFactory<Seance_Pratique, LocalDate>("date_seance"));
        moniteur.setCellValueFactory(new PropertyValueFactory<Seance_Pratique, Moniteur>("moniteur"));
        vehicule.setCellValueFactory(new PropertyValueFactory<Seance_Pratique, String>("vehicule"));
        pratique_table.setItems(FXCollections.observableArrayList(selectedCandidat.getSeances_pratique()));
        this.borderPane=borderPane;
        this.selectedCandidat=selectedCandidat;
        borderPane.setOnMouseClicked(event->ClearSelection());
    }

    private void ClearSelection(){pratique_table.getSelectionModel().clearSelection();
        dpDateSeance.setValue(LocalDate.now());
        cbVehicule.getSelectionModel().clearSelection();
        cbMoniteur.getSelectionModel().clearSelection();
    }

    public void Delete(ActionEvent event){
        Seance_Pratique seance_pratique=pratique_table.getSelectionModel().getSelectedItem();
        if(seance_pratique!=null){
            LayoutController.seanceTheoriqueRepository.Delete(seance_pratique.getId_seance());
            LayoutController.candidatRepository.Find(selectedCandidat.getId_candidat()).getSeances_pratique().remove(seance_pratique);
            pratique_table.getItems().remove(seance_pratique);
            ClearSelection();
        }
    }

    public void AddSeance(){
        if(selectedCandidat==null) return;
        Seance_Pratique seance_pratique=new Seance_Pratique(Seance_Pratique.GererId(dpDateSeance.getValue(),cbMoniteur.getValue()),
                dpDateSeance.getValue(),cbMoniteur.getValue(),cbVehicule.getValue());
        LayoutController.seancePratiqueRepository.Add(seance_pratique);
        LayoutController.candidatRepository.Find(selectedCandidat.getId_candidat()).getSeances_pratique().add(seance_pratique);
        pratique_table.getItems().add(seance_pratique);
        ClearSelection();
    }

    public void Update(){
        Seance_Pratique selectedSeance=pratique_table.getSelectionModel().getSelectedItem();
        if(selectedSeance==null) return;
        Seance_Pratique seance_pratique=new Seance_Pratique(selectedSeance.getId_seance(),dpDateSeance.getValue(),cbMoniteur.getValue(),cbVehicule.getValue());
        LayoutController.seancePratiqueRepository.Update(seance_pratique);
        LayoutController.candidatRepository.Find(selectedCandidat.getId_candidat()).setSeances_pratique(LayoutController.seancePratiqueRepository.List());
        pratique_table.getItems().clear();
        pratique_table.setItems(FXCollections.observableArrayList(selectedCandidat.getSeances_pratique()));
        ClearSelection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       cbMoniteur.getItems().addAll(LayoutController.moniteurRepository.List());
       cbVehicule.getItems().addAll(LayoutController.vehiculeRepository.List());
       dpDateSeance.setValue(LocalDate.now());
        pratique_table.setOnMouseClicked(event->{
            Seance_Pratique selectedSeance=pratique_table.getSelectionModel().getSelectedItem();
            if(selectedSeance!=null){
                dpDateSeance.setValue(selectedSeance.getDate_seance());
                cbMoniteur.setValue(selectedSeance.getMoniteur());
                cbVehicule.setValue(selectedSeance.getVehicule());
            }
        });
    }

    public void Back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("CandidatView.fxml"));
        Pane view=fxmlLoader.load();
        CandidatController candidatController=fxmlLoader.getController();
        candidatController.init(borderPane);
        borderPane.setCenter(view);
    }
}
