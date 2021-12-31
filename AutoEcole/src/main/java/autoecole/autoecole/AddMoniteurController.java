package autoecole.autoecole;

import Models.Moniteur;
import Models.Sexe;
import Models.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddMoniteurController implements Initializable {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbType.getItems().addAll(Type.values());
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
        Moniteur moniteur=new Moniteur(Moniteur.GererId(txtNom.getText(),txtPrenom.getText()),txtNom.getText(),txtPrenom.getText(),dpDate_Naissance.getValue(),cbSexe.getValue(),txtEmail.getText(),LocalDate.now(),cbType.getValue());
        LayoutController.moniteurRepository.Add(moniteur);

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

        if(cbType.getValue()==null){
            cbType.setStyle("-fx-border-color: red;-fx-border-width: 2px;");
            b=false;
        }
        else {
            cbType.setStyle(null);
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

    @FXML
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
