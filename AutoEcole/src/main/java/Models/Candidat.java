package Models;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Candidat extends Personne{

    private String id_candidat;
    private LocalDate date_debut;
    private Double reste;

    private ArrayList<Seance_Pratique> seances_pratique;
    private ArrayList<Seance_Theorique> seances_thearique;
    private ArrayList<Examen> examens;
    private ArrayList<Paiment> paiments;

    public Candidat(String id_candidat,String nom, String prenom,LocalDate date_naissance,Sexe sexe, String email, LocalDate date_debut) {
        super(nom,prenom,date_naissance,sexe,email);
        this.id_candidat = id_candidat;
        this.date_debut=date_debut;
        this.reste=2400.00;
        examens=new ArrayList<>();
        seances_pratique=new ArrayList<>();
        seances_thearique=new ArrayList<>();
        paiments=new ArrayList<>();
    }
    public Candidat(){

    }

    public String getId_candidat() {
        return id_candidat;
    }

    public void setId_candidat(String id_candidat) {
        this.id_candidat = id_candidat;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public ArrayList<Seance_Pratique> getSeances_pratique() {
        return seances_pratique;
    }

    public void setSeances_pratique(ArrayList<Seance_Pratique> seances_pratique) {
        this.seances_pratique = seances_pratique;
    }

    public ArrayList<Seance_Theorique> getSeances_thearique() {
        return seances_thearique;
    }

    public void setSeances_thearique(ArrayList<Seance_Theorique> seances_thearique) {
        this.seances_thearique = seances_thearique;
    }

    public ArrayList<Examen> getExamens() {
        return examens;
    }

    public void setExamens(ArrayList<Examen> examens) {
        this.examens = examens;
    }

    public ArrayList<Paiment> getPaiments() {
        return paiments;
    }

    public void setPaiments(ArrayList<Paiment> paiments) {
        this.paiments = paiments;
    }

    public Double getReste() {
        return reste;
    }

    public Boolean minusReste(Double montant) {
        if(this.reste<montant){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Montant");
            alert.setHeaderText("Le montant est supérieur au reste");
            alert.setContentText("le reste est " + this.reste);
            alert.show();
            return false;
        }else{
            this.reste -=montant;
            return true;
        }
    }

    public void plusReste(Double montant) {
        this.reste +=montant;
    }

    public static String GererId(String nom, String prenom){
        return ((nom + prenom ).hashCode() & 0xfffffff) + "";
    }
}
