package Models;

import java.time.LocalDate;
import java.util.Date;

public class Moniteur extends Personne{
    private String id_moniteur;
    private LocalDate date_debut;
    private Type type_moniteur;

    public Moniteur(String id_moniteur, String nom, String prenom, LocalDate date_naissance, Sexe sexe, String email, LocalDate date_debut, Type type_moniteur) {
        super(nom, prenom,date_naissance,sexe,email);
        this.id_moniteur = id_moniteur;
        this.date_debut=date_debut;
        this.type_moniteur=type_moniteur;
    }


    public String getId_moniteur() {
        return id_moniteur;
    }

    public void setId_moniteur(String id_moniteur) {
        this.id_moniteur = id_moniteur;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public Type getType_moniteur() {
        return type_moniteur;
    }

    public void setType_moniteur(Type type_moniteur) {
        this.type_moniteur = type_moniteur;
    }

    @Override
    public String toString() {
        return getNom();
    }

    public static String GererId(String nom, String prenom){
        return ((nom + prenom ).hashCode() & 0xfffffff) + "";
    }
}
