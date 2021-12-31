package autoecole.autoecole;

import Models.Candidat;
import Models.Etat_Examen;
import Models.Examen;
import Models.Vehicule;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label CarInfo;

    @FXML
    private Pane alertPane;

    @FXML
    private PieChart pieChart = new PieChart();

    @FXML
    private NumberAxis axeX;

    @FXML
    private CategoryAxis axeY;

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    Label lblExpired;

    @FXML
    Label lbl7;

    @FXML
    Label lbl30;

    @FXML
    Label lbl90;

    BorderPane mainBorderPan;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int apte=0,redoublement=0,enAttent=0;
        int expired=0,remaining7=0,remaining30=0,remaining90=0;
        int i=1;
        LocalDate now=LocalDate.now();
        // Examens Remaining Time
        for (Examen examen: LayoutController.examenRepository.List()) {
            //check date examen
            if(now.compareTo(examen.getDate_examen())>=0)
                expired++;
            else if(now.plusDays(7).compareTo(examen.getDate_examen())>=0)
                remaining7++;
            else if(now.plusDays(30).compareTo(examen.getDate_examen())>=0)
                remaining30++;
            else if(now.minusDays(90).compareTo(examen.getDate_examen())<=0)
                remaining90++;
            //check Etat
            if(examen.getEtat_examen()== Etat_Examen.apte)
                apte++;
            else  if(examen.getEtat_examen()== Etat_Examen.redoublant)
                redoublement++;
            else  if(examen.getEtat_examen()== Etat_Examen.en_attend)
                enAttent++;
        }

        // Alert
        alertPane.setVisible(false);
        for (Vehicule vehicule: LayoutController.vehiculeRepository.List()) {

           if(now.plusDays(7).compareTo(vehicule.getDate_visite_technique())>=0  || now.plusDays(7).compareTo(vehicule.getDate_assurance())>=0) {
               CarInfo.setText(CarInfo.getText().concat("Vihicule " + i + " Marque : "+vehicule.getMarque() +" Mat: "+ vehicule.getNum_matricule() +"\n"));
           alertPane.setVisible(true);
           }


            i++;
        }

        //Pie Chart Data
        ObservableList<PieChart.Data> pieChartData=
                FXCollections.observableArrayList(

                        new PieChart.Data("Apte ", apte),
                        new PieChart.Data("Redoublement ",redoublement),
                        new PieChart.Data("En attent ",enAttent));

        pieChartData.forEach(data -> data.nameProperty().bind(Bindings.concat(
                data.getName()," ",data.pieValueProperty()
        )));
        pieChart.getData().addAll(pieChartData);

        lblExpired.setText(expired + "");
        lbl7.setText(remaining7 + "");
        lbl30.setText(remaining30 + "");
        lbl90.setText(remaining90 + "");


        //barchart Data
        int []months=new int[12];
        for (int m:months) {
            m=0;
        }
        for (Candidat candidat:LayoutController.candidatRepository.List()) {
            months[candidat.getDate_debut().getMonth().getValue()-1]++;
        }
        XYChart.Series<String , Integer> seriesCandidat = new XYChart.Series<>() ;
        seriesCandidat.getData().add(new XYChart.Data<>("Janv",months[0]));
        seriesCandidat.getData().add(new XYChart.Data<>("Févr",months[1]));
        seriesCandidat.getData().add(new XYChart.Data<>("Mars",months[2]));
        seriesCandidat.getData().add(new XYChart.Data<>("Avr",months[3]));
        seriesCandidat.getData().add(new XYChart.Data<>("Mai",months[4]));
        seriesCandidat.getData().add(new XYChart.Data<>("Juin",months[5]));
        seriesCandidat.getData().add(new XYChart.Data<>("Juil",months[6]));
        seriesCandidat.getData().add(new XYChart.Data<>("Août",months[7]));
        seriesCandidat.getData().add(new XYChart.Data<>("Sept",months[8]));
        seriesCandidat.getData().add(new XYChart.Data<>("Oct",months[9]));
        seriesCandidat.getData().add(new XYChart.Data<>("Nov",months[10]));
        seriesCandidat.getData().add(new XYChart.Data<>("Déc",months[11]));
        barChart.getData().addAll(seriesCandidat);

    }

    public void init(BorderPane mainBorderPan){
        this.mainBorderPan=mainBorderPan;
    }

    public void SwitchVehicule() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AutoEcoleApplication.class.getResource("VehiculeView.fxml"));
        Pane view=fxmlLoader.load();
        VehiculeController vehiculeController=fxmlLoader.getController();
        vehiculeController.init(mainBorderPan);
        mainBorderPan.setCenter(view);
    }

}







