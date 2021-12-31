package autoecole.autoecole;

import Models.Candidat;
import Models.Etat_Examen;
import Models.Examen;
import Models.Type;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.ArrayList;

public class ExamensController {

    @FXML
    TableView<Examen> examens_table;

    @FXML
    TableColumn<Examen, Type> type;

    @FXML
    TableColumn<Examen, Etat_Examen> etat;

    @FXML
    TableColumn<Examen, LocalDate> date_examen;

    @FXML
    TableColumn<Examen, String> nom_candidat;

    BorderPane borderPane;

    Candidat selectedCandidat;

    public void init( BorderPane borderPane,Candidat selectedCandidat){
        nom_candidat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCandidat().getNom()));
        etat.setCellValueFactory(new PropertyValueFactory<Examen, Etat_Examen>("etat_examen"));
        date_examen.setCellValueFactory(new PropertyValueFactory<Examen, LocalDate>("date_examen"));
        type.setCellValueFactory(new PropertyValueFactory<Examen, Type>("type_examen"));

        this.selectedCandidat=selectedCandidat;
        this.borderPane=borderPane;
        if(selectedCandidat!=null) {
            ArrayList<Examen> examen = LayoutController.candidatRepository.Find(selectedCandidat.getId_candidat()).getExamens();
            examens_table.setItems(FXCollections.observableArrayList(examen));
        }
        else {
            examens_table.setItems(FXCollections.observableArrayList(LayoutController.examenRepository.List()));
        }
        borderPane.setOnMouseClicked(event->{examens_table.getSelectionModel().clearSelection();});
    }

    public void Delete(ActionEvent event){
        Examen examen=examens_table.getSelectionModel().getSelectedItem();
        if(examen!=null){
            LayoutController.examenRepository.Delete(examen.getId_examen());
            LayoutController.candidatRepository.Find(examen.getCandidat().getId_candidat()).getExamens().remove(examen);
            examens_table.getItems().remove(examen);
        }
    }

    public void Update(){
        Examen examen=examens_table.getSelectionModel().getSelectedItem();
        if(examen!=null){
            LayoutController.examenRepository.Delete(examen.getId_examen());
            LayoutController.candidatRepository.Find(examen.getCandidat().getId_candidat()).getExamens().remove(examen);
            examens_table.getItems().remove(examen);
        }
    }

    public void Back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("CandidatView.fxml"));
        Pane view=fxmlLoader.load();
        CandidatController candidatController=fxmlLoader.getController();
        candidatController.init(borderPane);
        borderPane.setCenter(view);
    }

}
