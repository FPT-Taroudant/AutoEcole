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

public class CandidatController implements Initializable {

    @FXML
    TableView<Candidat> candidats_table;

    @FXML
    TableColumn<Candidat, String> nom_candidat;

    @FXML
    TableColumn<Candidat, String> prenom_candidat;

    @FXML
    TableColumn<Candidat, LocalDate> date_inscription_candidat;

    @FXML
    TableColumn<Candidat, String> email_candidat;

    @FXML
    TextField txtSearch;

    @FXML
    ComboBox<String> cbSearchChoice=new ComboBox<>();

    @FXML
    ComboBox<Etat_Examen> cbEtat=new ComboBox<>();

    @FXML
    ComboBox<Type> cbTypeExamen=new ComboBox<>();

    @FXML
    Label lblNom;

    @FXML
    DatePicker dpDate;


    BorderPane borderPane;

    public void init(BorderPane borderPane){
        nom_candidat.setCellValueFactory(new PropertyValueFactory<Candidat, String>("nom"));
        prenom_candidat.setCellValueFactory(new PropertyValueFactory<Candidat, String>("prenom"));
        date_inscription_candidat.setCellValueFactory(new PropertyValueFactory<Candidat, LocalDate>("date_debut"));
        email_candidat.setCellValueFactory(new PropertyValueFactory<Candidat, String>("email"));

        candidats_table.setItems(FXCollections.observableArrayList(LayoutController.candidatRepository.List()));
        this.borderPane=borderPane;
        borderPane.setOnMouseClicked(event->{candidats_table.getSelectionModel().clearSelection();lblNom.setText("");});
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbSearchChoice.getItems().addAll("Nom","Prenom");
        cbSearchChoice.setValue("Nom");
        cbEtat.getItems().addAll(Etat_Examen.values());
        cbTypeExamen.getItems().addAll(Type.values());
        txtSearch.setOnKeyReleased(event-> Rechercher());
        candidats_table.setOnMouseClicked(event->{
            if(candidats_table.getSelectionModel().getSelectedItem()!=null) lblNom.setText(candidats_table.getSelectionModel().getSelectedItem().getPrenom() + " " + candidats_table.getSelectionModel().getSelectedItem().getNom());
            else lblNom.setText("");
        });
    }


    public void Ajouter(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("AddCandidat.fxml"));
        Pane view=fxmlLoader.load();
        AddCandidatController addController=fxmlLoader.getController();
        addController.init(borderPane);
        borderPane.setCenter(view);
    }


    public void Delete(ActionEvent event){
        Candidat c=candidats_table.getSelectionModel().getSelectedItem();
        if(c!=null){
            LayoutController.candidatRepository.Delete(c.getId_candidat());
            candidats_table.getItems().remove(c);
            lblNom.setText("");
        }
    }

    public void Update(ActionEvent event) throws IOException {
        Candidat c=candidats_table.getSelectionModel().getSelectedItem();
        if(c!=null){
            FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("UpdateCandidat.fxml"));
            Pane view=fxmlLoader.load();
            UpdateCandidatController updateCandidatController=fxmlLoader.getController();
            updateCandidatController.init(borderPane, c);
            borderPane.setCenter(view);
        }
    }
    @FXML
    public void Rechercher(){
        ArrayList<Candidat> candidats;
        if(cbSearchChoice.getSelectionModel().getSelectedItem().equals("Nom")) candidats= LayoutController.candidatRepository.SearchByNom(txtSearch.getText());
        else candidats= LayoutController.candidatRepository.SearchByPrenom(txtSearch.getText());
        if(candidats.size()>0) candidats_table.setItems(FXCollections.observableArrayList(candidats));
        else candidats_table.setItems(FXCollections.observableArrayList(LayoutController.candidatRepository.List()));
    }

    public void AddExamen(){
        Candidat candidat=candidats_table.getSelectionModel().getSelectedItem();
        if(candidat==null) return;
        Examen examen=new Examen(Examen.GererId(candidat.getId_candidat(),dpDate.getValue()),dpDate.getValue(),
                cbTypeExamen.getValue(),cbEtat.getValue(),candidat);
        LayoutController.examenRepository.Add(examen);
        LayoutController.candidatRepository.Find(candidat.getId_candidat()).getExamens().add(examen);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Examen à été bien ajouter");
        alert.show();
    }

    @FXML
    public void Examens() throws IOException {
        Candidat candidat=candidats_table.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("ExamensView.fxml"));
        Pane view=fxmlLoader.load();
        ExamensController examensController=fxmlLoader.getController();
        examensController.init(borderPane,candidat);
        borderPane.setCenter(view);
    }

    public void SeanceTheorique() throws IOException {
        Candidat candidat=candidats_table.getSelectionModel().getSelectedItem();
        if(candidat==null) return;
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("SeanceTheoriqueView.fxml"));
        Pane view=fxmlLoader.load();
        SeanceTheoriqueController seanceTheoriqueController=fxmlLoader.getController();
        seanceTheoriqueController.init(borderPane,candidat);
        borderPane.setCenter(view);
    }

    public void SeancePratique() throws IOException {
        Candidat candidat=candidats_table.getSelectionModel().getSelectedItem();
        if(candidat==null) return;
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("SeancePratiqueView.fxml"));
        Pane view=fxmlLoader.load();
        SeancePratiqueController seancePratiqueController=fxmlLoader.getController();
        seancePratiqueController.init(borderPane,candidat);
        borderPane.setCenter(view);
    }

    public void Paiment() throws IOException {
        Candidat candidat=candidats_table.getSelectionModel().getSelectedItem();
        if(candidat==null) return;
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("PaimentView.fxml"));
        Pane view=fxmlLoader.load();
        PaimentController paimentController=fxmlLoader.getController();
        paimentController.init(borderPane,candidat);
        borderPane.setCenter(view);
    }

}
