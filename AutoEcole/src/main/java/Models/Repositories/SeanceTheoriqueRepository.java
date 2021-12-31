package Models.Repositories;

import Models.Seance_Pratique;
import Models.Seance_Theorique;

import java.util.ArrayList;

public class SeanceTheoriqueRepository implements IAutoEcoleRepository<Seance_Theorique> {

    ArrayList<Seance_Theorique> seances;

    public SeanceTheoriqueRepository(){
        seances=new ArrayList<>();
    }
    @Override
    public void Add(Seance_Theorique seance) {
        seances.add(seance);
    }

    @Override
    public void Delete(String id) {
        Seance_Theorique seance=Find(id);
        seances.remove(seance);
    }

    private void Modify(Seance_Theorique old_seance, Seance_Theorique new_seance){
        old_seance.setDate_seance(new_seance.getDate_seance());
        old_seance.setMoniteur(new_seance.getMoniteur());
        old_seance.setSalle(new_seance.getSalle());
        old_seance.setCandidats(new_seance.getCandidats());
    }

    @Override
    public void Update(Seance_Theorique Entity) {
        Seance_Theorique seance=Find(Entity.getId_seance());
        Modify(seance,Entity);
    }

    @Override
    public Seance_Theorique Find(String id) {
        Seance_Theorique s;
        for(int i=0; i<seances.size(); i++){
            s=seances.get(i);
            if(s.getId_seance().equals(id)){
                return s;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Seance_Theorique> List() {
        return seances;
    }
}
