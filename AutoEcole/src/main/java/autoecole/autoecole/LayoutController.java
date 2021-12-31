package autoecole.autoecole;


import Models.Repositories.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LayoutController implements Initializable{


    @FXML
    private BorderPane mainBorderPan;

    @FXML
    private Circle shpActive;


    static CandidatRepository candidatRepository=new CandidatRepository();
    static MoniteurRepository moniteurRepository=new MoniteurRepository();
    static VehiculeRepository vehiculeRepository=new VehiculeRepository();
    static ExamenRepository examenRepository=new ExamenRepository();
    static SeanceTheoriqueRepository seanceTheoriqueRepository=new SeanceTheoriqueRepository();
    static SeancePratiqueRepository seancePratiqueRepository=new SeancePratiqueRepository();
    static PaimentRepository paimentRepository=new PaimentRepository();


    @FXML
    public void SwitchHome() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("HomeView.fxml"));
        Pane view=fxmlLoader.load();
        HomeController homeController=fxmlLoader.getController();
        homeController.init(mainBorderPan);
        mainBorderPan.setCenter(view);
    }

    @FXML
    public void SwitchCandidat() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("CandidatView.fxml"));
        Pane view=fxmlLoader.load();
        CandidatController candidatController=fxmlLoader.getController();
        candidatController.init(mainBorderPan);
        mainBorderPan.setCenter(view);
    }

    @FXML
    public void SwitchMoniteur() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("MoniteurView.fxml"));
        Pane view=fxmlLoader.load();
        MoniteurController moniteurController=fxmlLoader.getController();
        moniteurController.init(mainBorderPan);
        mainBorderPan.setCenter(view);
    }

    @FXML
    public void SwitchVehicule() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("VehiculeView.fxml"));
        Pane view=fxmlLoader.load();
        VehiculeController vehiculeController=fxmlLoader.getController();
        vehiculeController.init(mainBorderPan);
        mainBorderPan.setCenter(view);
    }

    public void Exit(ActionEvent event){
        Stage stage=(Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            SwitchHome();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}