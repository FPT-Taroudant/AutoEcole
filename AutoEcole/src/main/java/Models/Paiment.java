package Models;

import java.time.LocalDate;
import java.util.Date;

public class Paiment {
    private String id_paiment;

    public Paiment(String id_paiment, Double montant, LocalDate date_paiment,Candidat candidat) {
        this.id_paiment = id_paiment;
        this.montant = montant;
        this.date_paiment = date_paiment;
        this.candidat = candidat;
    }

    public Paiment(){}

    private Double montant;
    private LocalDate date_paiment;
    private Candidat candidat;

    public String getId_paiment() {
        return id_paiment;
    }

    public void setId_paiment(String id_paiment) {
        this.id_paiment = id_paiment;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public LocalDate getDate_paiment() {
        return date_paiment;
    }

    public void setDate_paiment(LocalDate date_paiment) {
        this.date_paiment = date_paiment;
    }

    public static String GererId(LocalDate localDate, Double montant){
        return ((localDate + "" +montant ).hashCode() & 0xfffffff) + "";
    }
}
