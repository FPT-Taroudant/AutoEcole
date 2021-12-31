package Models.Repositories;

import Models.Candidat;
import Models.Moniteur;
import Models.Sexe;
import Models.Type;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class CandidatRepository implements IAutoEcoleRepository<Candidat>{
    ArrayList<Candidat> candidats;

    public CandidatRepository(){
        candidats=new ArrayList<>();
        Candidat c1=new Candidat(Candidat.GererId("khalid","khalid"),"reda","reda",LocalDate.of(2000,01,15), Sexe.male,"reda@gmail.com", LocalDate.of(2022,01,1));
        Candidat c2=new Candidat(Candidat.GererId("omar","omar"),"omar","omar",LocalDate.of(1997,02,17), Sexe.male,"omar@gmail.com",LocalDate.of(2022,01,1));
        Candidat c3=new Candidat(Candidat.GererId("hamza","hamza"),"hamza","hamza",LocalDate.of(1999,8,17), Sexe.male,"hamza@gmail.com",LocalDate.of(2022,01,2));
        Candidat c4=new Candidat(Candidat.GererId("ali","ali"),"ali","ali",LocalDate.of(1996,9,5), Sexe.male,"ali@gmail.com",LocalDate.of(2022,01,2));
        candidats.add(c1);
        candidats.add(c2);
        candidats.add(c3);
        candidats.add(c4);
    }


    @Override
    public void Add(Candidat candidat) {
        candidats.add(candidat);
    }

    @Override
    public void Delete(String id) {
        Candidat candidat=Find(id);
        candidats.remove(candidat);
    }

    private void Modify(Candidat old_candidat, Candidat new_candidat){
        old_candidat.setDate_naissance(new_candidat.getDate_naissance());
        old_candidat.setEmail(new_candidat.getEmail());
        old_candidat.setNom(new_candidat.getNom());
        old_candidat.setPrenom(new_candidat.getPrenom());
        old_candidat.setSexe(new_candidat.getSexe());
        old_candidat.setDate_debut(new_candidat.getDate_debut());
        old_candidat.setSeances_pratique(new_candidat.getSeances_pratique());
        old_candidat.setSeances_thearique(new_candidat.getSeances_thearique());
    }
    @Override
    public void Update(Candidat Entity) {
        Candidat candidat=Find(Entity.getId_candidat());
        if(candidat!=null) Modify(candidat,Entity);
    }

    @Override
    public Candidat Find(String id) {
        Candidat c;
        for(int i=0; i<candidats.size(); i++){
            c=candidats.get(i);
            if(c.getId_candidat().equals(id)){
                return c;
            }
        }
        return null;
    }

    public ArrayList<Candidat> SearchByNom(String nom){
        ArrayList<Candidat> candidatsTrouver=new ArrayList<>();
        candidats.forEach(candidat -> {
            if(candidat.getNom().contains(nom)){candidatsTrouver.add(candidat);}
        });
        return candidatsTrouver;
    }

    public ArrayList<Candidat> SearchByPrenom(String prenom){
        ArrayList<Candidat> candidatsTrouver=new ArrayList<>();
        candidats.forEach(candidat -> {
            if(candidat.getPrenom().contains(prenom)){candidatsTrouver.add(candidat);}
        });
        return candidatsTrouver;
    }

    @Override
    public ArrayList<Candidat> List() {
        return candidats;
    }
}
