package Models.Repositories;

import Models.Vehicule;
import Models.Sexe;
import Models.Vehicule;

import java.time.LocalDate;
import java.util.ArrayList;

public class VehiculeRepository implements IAutoEcoleRepository<Vehicule>{
    ArrayList<Vehicule> vehicules;


    public VehiculeRepository(){
        vehicules=new ArrayList<>();
        Vehicule c1=new Vehicule("azerty","honda", LocalDate.of(2000,01,15), LocalDate.of(2021,01,21));
        Vehicule c2=new Vehicule("uiopqs","ford", LocalDate.of(2015,05,17) ,LocalDate.of(2021,05,1));
        Vehicule c3=new Vehicule("dfghjk","dacia", LocalDate.of(2020,9,5), LocalDate.of(2021,03,15));
        vehicules.add(c1);
        vehicules.add(c2);
        vehicules.add(c3);
    }


    @Override
    public void Add(Vehicule vehicule) {
        vehicules.add(vehicule);
    }

    @Override
    public void Delete(String num_matricule) {
        Vehicule vehicule=Find(num_matricule);
        vehicules.remove(vehicule);
    }

    private void Modify(Vehicule old_Vehicule, Vehicule new_Vehicule){
        old_Vehicule.setMarque(new_Vehicule.getMarque());
        old_Vehicule.setDate_assurance(new_Vehicule.getDate_assurance());
        old_Vehicule.setDate_visite_technique(new_Vehicule.getDate_visite_technique());
    }

    @Override
    public void Update(Vehicule Entity) {
        Vehicule vehicule=Find(Entity.getNum_matricule());
        if(vehicule!=null) Modify(vehicule,Entity);
    }

    @Override
    public Vehicule Find(String num_matricule) {
        Vehicule v;
        for(int i=0; i<vehicules.size(); i++){
            v=vehicules.get(i);
            if(v.getNum_matricule().equals(num_matricule)){
                return v;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Vehicule> List() {
        return vehicules;
    }
}
