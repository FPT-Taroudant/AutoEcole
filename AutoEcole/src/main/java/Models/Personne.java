package Models;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.Date;

public class Personne {


    private String nom;
    private String prenom;
    private LocalDate date_naissance;
    private Sexe sexe;
    private String email;

    public Personne(String nom, String prenom,LocalDate date_naissance,Sexe sexe, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance=date_naissance;
        this.sexe=sexe;
        this.email=email;
    }

    public Personne(){}
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
