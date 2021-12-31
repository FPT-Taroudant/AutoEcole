package Models;

import java.time.LocalDate;
import java.util.Date;

public class Examen {
    private String id_examen;
    private LocalDate date_examen;
    private Type type_examen;

    public Examen(String id_examen, LocalDate date_examen, Type type_examen, Etat_Examen etat_examen, Candidat candidat) {
        this.id_examen = id_examen;
        this.date_examen = date_examen;
        this.type_examen = type_examen;
        this.etat_examen = etat_examen;
        this.candidat = candidat;
    }

    public Examen(){}

    private Etat_Examen etat_examen;
    private Candidat candidat;

    public String getId_examen() {
        return id_examen;
    }

    public void setId_examen(String id_examen) {
        this.id_examen = id_examen;
    }

    public LocalDate getDate_examen() {
        return date_examen;
    }

    public void setDate_examen(LocalDate date_examen) {
        this.date_examen = date_examen;
    }

    public Type getType_examen() {
        return type_examen;
    }

    public void setType_examen(Type type_examen) {
        this.type_examen = type_examen;
    }

    public Etat_Examen getEtat_examen() {
        return etat_examen;
    }

    public void setEtat_examen(Etat_Examen etat_examen) {
        this.etat_examen = etat_examen;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public static String GererId(String id_candidat, LocalDate dateInscription){
        return ((id_candidat + dateInscription ).hashCode() & 0xfffffff) + "";
    }
}
