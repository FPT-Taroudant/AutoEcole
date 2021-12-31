package autoecole.autoecole;

import Models.Candidat;
import Models.Sexe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddCandidatController implements Initializable {

    @FXML
    TextField txtNom;

    @FXML
    TextField txtPrenom;

    @FXML
    DatePicker dpDate_Naissance;

    @FXML
    ComboBox<Sexe> cbSexe=new ComboBox<>();

    @FXML
    TextField txtEmail;

    BorderPane borderPane;

    @FXML
    Label lblError;

    @FXML
    Label lblAge;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbSexe.getItems().addAll(Sexe.values());
    }

    public void init(BorderPane borderPane){
        this.borderPane=borderPane;
    }

    public void Save(ActionEvent event) throws IOException {
        boolean check=true;
        if(!CheckFields()){
            lblError.setVisible(true);
            check=false;
        }
        if(!CheckAge()){
            check=false;
        }
        if(!check) return;
        lblError.setVisible(false);
        Candidat candidat=new Candidat(Candidat.GererId(txtNom.getText(),txtPrenom.getText()),txtNom.getText().trim(),txtPrenom.getText().trim(),dpDate_Naissance.getValue(),cbSexe.getValue(),txtEmail.getText().trim(),LocalDate.now());
        LayoutController.candidatRepository.Add(candidat);
        SwitchCandidat(borderPane);
    }

    private void SwitchCandidat(BorderPane borderPane) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("CandidatView.fxml"));
        Pane view=fxmlLoader.load();
        CandidatController candidatController=fxmlLoader.getController();
        candidatController.init(borderPane);
        borderPane.setCenter(view);
    }

    public void Cancel() throws IOException {
        SwitchCandidat(borderPane);
    }

    private boolean CheckFields(){
        boolean b=true;
        if(txtNom.getText().trim()==""){
            txtNom.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
            b=false;
        }
        else {
            txtNom.setStyle(null);
        }

        if(txtPrenom.getText().trim()==""){
            txtPrenom.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
            b=false;
        }
        else {
            txtPrenom.setStyle(null);
        }

        if(txtEmail.getText().trim()==""){
            txtEmail.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
            b=false;
        }
        else {
            txtEmail.setStyle(null);
        }


        if(dpDate_Naissance.getValue()==null){
            dpDate_Naissance.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
            b=false;
        }
        else {
            dpDate_Naissance.setStyle(null);
        }


        if(cbSexe.getValue()==null){
            cbSexe.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
            b=false;
        }
        else {
            cbSexe.setStyle(null);
        }

        return b;
    }

    public boolean CheckAge(){
        if(dpDate_Naissance.getValue()==null) return false;
        LocalDate now=LocalDate.now().minusYears(18);
        if(now.compareTo(dpDate_Naissance.getValue())<=0){
            dpDate_Naissance.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
            lblAge.setVisible(true);
            return false;
        }
        else {
            dpDate_Naissance.setStyle(null);
            lblAge.setVisible(false);
            return true;
        }
    }
}
