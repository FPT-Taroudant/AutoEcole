package Models.Repositories;

import Models.Examen;
import Models.Moniteur;
import autoecole.autoecole.LayoutController;

import java.util.ArrayList;

public class ExamenRepository implements IAutoEcoleRepository<Examen>{

    ArrayList<Examen> examens;

    public ExamenRepository(){
        examens=new ArrayList<>();
    }

    @Override
    public void Add(Examen examen) {
        examens.add(examen);
    }

    @Override
    public void Delete(String id) {
        Examen examen=Find(id);
        examens.remove(examen);
    }

    private void Modify(Examen old_examen, Examen new_examen){
        old_examen.setDate_examen(new_examen.getDate_examen());
        old_examen.setEtat_examen(new_examen.getEtat_examen());
        old_examen.setCandidat(new_examen.getCandidat());
        old_examen.setType_examen(new_examen.getType_examen());
    }

    @Override
    public void Update(Examen Entity) {
        Examen examen=Find(Entity.getId_examen());
        Modify(examen,Entity);
    }

    @Override
    public Examen Find(String id) {
        Examen e;
        for(int i=0; i<examens.size(); i++){
            e=examens.get(i);
            if(e.getId_examen().equals(id)){
                return e;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Examen> List() {
        return examens;
    }
}
