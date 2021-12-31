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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaimentController implements Initializable {

    @FXML
    TableView<Paiment> paiments_table;

    @FXML
    TableColumn<Paiment, LocalDate> date_paiment;

    @FXML
    TableColumn<Paiment, Double> montant;

    @FXML
    DatePicker dpDatePaiment;

    @FXML
    Spinner<Double> spMontant;

    @FXML
    Label lblReste;

    BorderPane borderPane;
    Candidat selectedCandidat;

    public void init( BorderPane borderPane,Candidat selectedCandidat){
        date_paiment.setCellValueFactory(new PropertyValueFactory<Paiment, LocalDate>("date_paiment"));
        montant.setCellValueFactory(new PropertyValueFactory<Paiment, Double>("montant"));

        this.selectedCandidat=selectedCandidat;
        this.borderPane=borderPane;

        ArrayList<Paiment> paiment = selectedCandidat.getPaiments();
        paiments_table.setItems(FXCollections.observableArrayList(paiment));
        lblReste.setText(selectedCandidat.getReste() + "DH");
        SpinnerValueFactory<Double> spinnerValueFactory=new SpinnerValueFactory.DoubleSpinnerValueFactory(1,2400,4);
        spinnerValueFactory.setValue(selectedCandidat.getReste());
        spMontant.setValueFactory(spinnerValueFactory);
        borderPane.setOnMouseClicked(event->{paiments_table.getSelectionModel().clearSelection();});
    }

    public void Delete(ActionEvent event){
        Paiment paiment=paiments_table.getSelectionModel().getSelectedItem();
        if(paiment!=null){
            selectedCandidat.plusReste(paiment.getMontant());
            LayoutController.paimentRepository.Delete(paiment.getId_paiment());
            selectedCandidat.getPaiments().remove(paiment);
            paiments_table.getItems().remove(paiment);
            lblReste.setText(selectedCandidat.getReste() + "DH");
        }
    }

    public void AddPaiment(){
        if(selectedCandidat.getReste()==0 || spMontant.getValue()==null) return;
        if(!selectedCandidat.minusReste(spMontant.getValue())) return;
        Paiment paiment=new Paiment(Paiment.GererId(dpDatePaiment.getValue(),spMontant.getValue()),spMontant.getValue(),dpDatePaiment.getValue(),selectedCandidat);
        LayoutController.paimentRepository.Add(paiment);
        selectedCandidat.getPaiments().add(paiment);

        paiments_table.getItems().add(paiment);
        lblReste.setText(selectedCandidat.getReste() + "DH");
    }

    public void Back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("CandidatView.fxml"));
        Pane view=fxmlLoader.load();
        CandidatController candidatController=fxmlLoader.getController();
        candidatController.init(borderPane);
        borderPane.setCenter(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dpDatePaiment.setValue(LocalDate.now());
    }
}
