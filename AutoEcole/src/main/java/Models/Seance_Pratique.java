package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Seance_Pratique {
    private String id_seance;
    private LocalDate date_seance;
    private Moniteur moniteur;
    private Vehicule vehicule;
    private ArrayList<Candidat> candidats;

    public Seance_Pratique(String id_seance, LocalDate date_seance,Moniteur moniteur, Vehicule vehicule) {
        this.id_seance = id_seance;
        this.date_seance = date_seance;
        this.moniteur = moniteur;
        this.vehicule = vehicule;
    }

    public Seance_Pratique(){}

    public String getId_seance() {
        return id_seance;
    }

    public void setId_seance(String id_seance) {
        this.id_seance = id_seance;
    }

    public LocalDate getDate_seance() {
        return date_seance;
    }

    public void setDate_seance(LocalDate date_seance) {
        this.date_seance = date_seance;
    }

    public Moniteur getMoniteur() {
        return moniteur;
    }

    public void setMoniteur(Moniteur moniteur) {
        this.moniteur = moniteur;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public ArrayList<Candidat> getCandidats() {
        return candidats;
    }

    public void setCandidats(ArrayList<Candidat> candidats) {
        this.candidats = candidats;
    }

    public static String GererId(LocalDate date_seance, Moniteur moniteur){
        return ((date_seance + moniteur.getId_moniteur() ).hashCode() & 0xfffffff) + "";
    }
}
