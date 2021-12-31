package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Seance_Theorique {
    private String id_seance;
    private LocalDate date_seance;
    private Moniteur moniteur;
    private String salle;
    private ArrayList<Candidat> candidats;

    public Seance_Theorique(String id_seance, LocalDate date_seance, Moniteur moniteur, String salle) {
        this.id_seance = id_seance;
        this.date_seance = date_seance;
        this.moniteur = moniteur;
        this.salle = salle;
    }

    public Seance_Theorique(){}

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

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
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
