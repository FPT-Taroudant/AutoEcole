package Models.Repositories;

import Models.Seance_Pratique;

import java.util.ArrayList;

public class SeancePratiqueRepository implements IAutoEcoleRepository<Seance_Pratique> {

    ArrayList<Seance_Pratique> seances;

    public SeancePratiqueRepository(){
        seances=new ArrayList<>();
    }
    @Override
    public void Add(Seance_Pratique seance) {
        seances.add(seance);
    }

    @Override
    public void Delete(String id) {
        Seance_Pratique seance=Find(id);
        seances.remove(seance);
    }

    private void Modify(Seance_Pratique old_seance, Seance_Pratique new_seance){
        old_seance.setDate_seance(new_seance.getDate_seance());
        old_seance.setMoniteur(new_seance.getMoniteur());
        old_seance.setVehicule(new_seance.getVehicule());
        old_seance.setCandidats(new_seance.getCandidats());
    }

    @Override
    public void Update(Seance_Pratique Entity) {
        Seance_Pratique seance=Find(Entity.getId_seance());
        Modify(seance,Entity);
    }

    @Override
    public Seance_Pratique Find(String id) {
        Seance_Pratique s;
        for(int i=0; i<seances.size(); i++){
            s=seances.get(i);
            if(s.getId_seance().equals(id)){
                return s;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Seance_Pratique> List() {
        return seances;
    }
}
