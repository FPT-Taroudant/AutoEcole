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

public class SeanceTheoriqueController implements Initializable {

    @FXML
    private ComboBox<Moniteur> cbMoniteur=new ComboBox<>();

    @FXML
    private TextField txtSalle;

    @FXML
    private TableColumn<Seance_Theorique, LocalDate> date_seance;

    @FXML
    private DatePicker dpDateSeance;

    @FXML
    private TableColumn<Seance_Theorique, Moniteur> moniteur;

    @FXML
    private TableColumn<Seance_Theorique, String> salle;

    @FXML
    private TableView<Seance_Theorique> theorique_table;


    BorderPane borderPane;
    Candidat selectedCandidat;


    public void init(BorderPane borderPane,Candidat selectedCandidat){
        date_seance.setCellValueFactory(new PropertyValueFactory<Seance_Theorique, LocalDate>("date_seance"));
        moniteur.setCellValueFactory(new PropertyValueFactory<Seance_Theorique, Moniteur>("moniteur"));
        salle.setCellValueFactory(new PropertyValueFactory<Seance_Theorique, String>("salle"));
        theorique_table.setItems(FXCollections.observableArrayList(selectedCandidat.getSeances_thearique()));
        this.borderPane=borderPane;
        this.selectedCandidat=selectedCandidat;
        borderPane.setOnMouseClicked(event->{ClearSelection();});
    }



    private void ClearSelection(){
        theorique_table.getSelectionModel().clearSelection();
        dpDateSeance.setValue(LocalDate.now());
        txtSalle.setText("");
        cbMoniteur.getSelectionModel().clearSelection();
    }

    public void Update(){
        Seance_Theorique selectedSeance=theorique_table.getSelectionModel().getSelectedItem();
        if(selectedSeance==null) return;
        Seance_Theorique seance_theorique=new Seance_Theorique(selectedSeance.getId_seance(),dpDateSeance.getValue(),cbMoniteur.getValue(),txtSalle.getText());
        LayoutController.seanceTheoriqueRepository.Update(seance_theorique);
        LayoutController.candidatRepository.Find(selectedCandidat.getId_candidat()).setSeances_thearique(LayoutController.seanceTheoriqueRepository.List());
        theorique_table.getItems().clear();
        theorique_table.setItems(FXCollections.observableArrayList(selectedCandidat.getSeances_thearique()));
        ClearSelection();
    }

    public void Delete(ActionEvent event){
        Seance_Theorique seance_theorique=theorique_table.getSelectionModel().getSelectedItem();
        if(seance_theorique!=null){
            LayoutController.seanceTheoriqueRepository.Delete(seance_theorique.getId_seance());
            LayoutController.candidatRepository.Find(selectedCandidat.getId_candidat()).getSeances_thearique().remove(seance_theorique);
            theorique_table.getItems().remove(seance_theorique);
            ClearSelection();
        }
    }

    public void AddSeance(){
        if(selectedCandidat==null) return;
        Seance_Theorique seance_theorique=new Seance_Theorique(Seance_Theorique.GererId(dpDateSeance.getValue(), cbMoniteur.getValue()),
                dpDateSeance.getValue(),cbMoniteur.getValue(),txtSalle.getText());
        LayoutController.seanceTheoriqueRepository.Add(seance_theorique);
        LayoutController.candidatRepository.Find(selectedCandidat.getId_candidat()).getSeances_thearique().add(seance_theorique);
        theorique_table.getItems().add(seance_theorique);
        ClearSelection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       cbMoniteur.getItems().addAll(LayoutController.moniteurRepository.List());
       dpDateSeance.setValue(LocalDate.now());
        theorique_table.setOnMouseClicked(event->{
            Seance_Theorique selectedSeance=theorique_table.getSelectionModel().getSelectedItem();
            if(selectedSeance!=null){
                dpDateSeance.setValue(selectedSeance.getDate_seance());
                cbMoniteur.setValue(selectedSeance.getMoniteur());
                txtSalle.setText(selectedSeance.getSalle());
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
