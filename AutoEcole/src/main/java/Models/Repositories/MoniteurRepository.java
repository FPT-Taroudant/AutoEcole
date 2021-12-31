package Models.Repositories;

import Models.Candidat;
import Models.Moniteur;
import Models.Sexe;
import Models.Type;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class MoniteurRepository implements IAutoEcoleRepository<Moniteur>{
    ArrayList<Moniteur> moniteurs;


    public MoniteurRepository() {
        moniteurs=new ArrayList<>();
        Moniteur m1=new Moniteur(Moniteur.GererId( "kamal","hamza"),"kamal","hamza",LocalDate.of(1997,01,15), Sexe.male,"hamza@gmail.com",LocalDate.of(2021,1,13), Type.pratique);
        Moniteur m2=new Moniteur(Moniteur.GererId( "bou","ahmed"),"bou","oussama",LocalDate.of(1995,05,20), Sexe.male,"oussama@gmail.com",LocalDate.of(2019,02,15), Type.pratique);
        Moniteur m3=new Moniteur(Moniteur.GererId( "dawd","fatima"),"dawd","fatima",LocalDate.of(1994,9,10), Sexe.femelle,"fatima@gmail.com",LocalDate.of(2020,01,15), Type.theorique);
        moniteurs.add(m1);
        moniteurs.add(m2);
        moniteurs.add(m3);
    }


    @Override
    public void Add(Moniteur moniteur) {
        moniteurs.add(moniteur);
    }

    @Override
    public void Delete(String id) {
        Moniteur moniteur=Find(id);
        moniteurs.remove(moniteur);
    }

    private void Modify(Moniteur old_moniteur, Moniteur new_moniteur){
        old_moniteur.setDate_naissance(new_moniteur.getDate_naissance());
        old_moniteur.setEmail(new_moniteur.getEmail());
        old_moniteur.setNom(new_moniteur.getNom());
        old_moniteur.setPrenom(new_moniteur.getPrenom());
        old_moniteur.setSexe(new_moniteur.getSexe());
        old_moniteur.setType_moniteur(new_moniteur.getType_moniteur());
    }

    @Override
    public void Update(Moniteur Entity) {
        Moniteur moniteur=Find(Entity.getId_moniteur());
        Modify(moniteur,Entity);
    }

    @Override
    public Moniteur Find(String id) {
        Moniteur m;
        for(int i=0; i<moniteurs.size(); i++){
            m=moniteurs.get(i);
            if(m.getId_moniteur().equals(id)){
                return m;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Moniteur> List() {
        return moniteurs;
    }
}
