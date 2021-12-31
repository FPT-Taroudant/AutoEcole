package Models.Repositories;

import Models.Paiment;
import Models.Paiment;

import java.util.ArrayList;

public class PaimentRepository implements IAutoEcoleRepository<Paiment> {
    ArrayList<Paiment> paiments;

    public PaimentRepository(){
        paiments=new ArrayList<>();
    }

    @Override
    public void Add(Paiment paiment) {
        paiments.add(paiment);
    }

    @Override
    public void Delete(String id) {
        Paiment paiment=Find(id);
        paiments.remove(paiment);
    }

    private void Modify(Paiment old_paiment, Paiment new_paiment){
        old_paiment.setDate_paiment(new_paiment.getDate_paiment());
        old_paiment.setId_paiment(new_paiment.getId_paiment());
        old_paiment.setCandidat(new_paiment.getCandidat());
        old_paiment.setMontant(new_paiment.getMontant());
    }

    @Override
    public void Update(Paiment Entity) {
        Paiment paiment=Find(Entity.getId_paiment());
        Modify(paiment,Entity);
    }

    @Override
    public Paiment Find(String id) {
        Paiment p;
        for(int i=0; i<paiments.size(); i++){
            p=paiments.get(i);
            if(p.getId_paiment().equals(id)){
                return p;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Paiment> List() {
        return paiments;
    }
}
