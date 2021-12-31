package autoecole.autoecole;

import Models.Moniteur;
import Models.Sexe;
import Models.Type;
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

public class UpdateMoniteurController implements Initializable {

    @FXML
    TextField txtNom;

    @FXML
    TextField txtPrenom;

    @FXML
    DatePicker dpDate_Naissance;

    @FXML
    ComboBox<Sexe> cbSexe=new ComboBox<>();

    @FXML
    ComboBox<Type> cbType=new ComboBox<>();

    @FXML
    TextField txtEmail;

    @FXML
    Label lblError;

    @FXML
    Label lblAge;

    BorderPane borderPane;
    Moniteur selectedMoniteur;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbType.getItems().addAll(Type.values());
        cbSexe.getItems().addAll(Sexe.values());
    }

    public void init(BorderPane borderPane, Moniteur selectedMoniteur ){
        this.borderPane=borderPane;
        this.selectedMoniteur=selectedMoniteur;
        txtNom.setText(selectedMoniteur.getNom());
        txtPrenom.setText(selectedMoniteur.getPrenom());
        txtEmail.setText(selectedMoniteur.getEmail());
        dpDate_Naissance.setValue(selectedMoniteur.getDate_naissance());
        cbSexe.setValue(selectedMoniteur.getSexe());
        cbType.setValue(selectedMoniteur.getType_moniteur());
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

        Moniteur moniteur=new Moniteur(selectedMoniteur.getId_moniteur(),txtNom.getText(),txtPrenom.getText(),dpDate_Naissance.getValue(),cbSexe.getValue(),txtEmail.getText(), LocalDate.now(), cbType.getValue());
        LayoutController.moniteurRepository.Update(moniteur);
        SwitchMoniteur(borderPane);
    }

    private void SwitchMoniteur(BorderPane borderPane) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("MoniteurView.fxml"));
        Pane view=fxmlLoader.load();
        MoniteurController moniteurController=fxmlLoader.getController();
        moniteurController.init(borderPane);
        borderPane.setCenter(view);
    }

    public void Cancel() throws IOException {
        SwitchMoniteur(borderPane);
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

        if(cbType.getValue()==null){
            cbType.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
            b=false;
        }
        else {
            cbType.setStyle(null);
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
