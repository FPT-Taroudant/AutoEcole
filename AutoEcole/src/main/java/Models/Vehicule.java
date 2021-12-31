package Models;

import java.time.LocalDate;
import java.util.Date;

public class Vehicule {
    private String num_matricule;
    private String marque;
    private LocalDate date_assurance;
    private LocalDate date_visite_technique;

    public Vehicule(String num_matricule, String marque, LocalDate date_assurance, LocalDate date_visite_technique) {
        this.num_matricule = num_matricule;
        this.marque = marque;
        this.date_assurance = date_assurance;
        this.date_visite_technique = date_visite_technique;
    }

    public Vehicule(){}

    public String getNum_matricule() {
        return num_matricule;
    }

    public void setNum_matricule(String num_matricule) {
        this.num_matricule = num_matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Override
    public String toString() {
        return num_matricule;
    }

    public LocalDate getDate_assurance() {
        return date_assurance;
    }

    public void setDate_assurance(LocalDate date_assurance) {
        this.date_assurance = date_assurance;
    }

    public LocalDate getDate_visite_technique() {
        return date_visite_technique;
    }

    public void setDate_visite_technique(LocalDate date_visite_technique) {
        this.date_visite_technique = date_visite_technique;
    }
}
