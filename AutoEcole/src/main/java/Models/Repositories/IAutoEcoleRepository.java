package Models.Repositories;

import java.util.ArrayList;

public interface IAutoEcoleRepository<TEntity> {
    public void Add(TEntity entity);
    public void Delete(String id);
    public void Update(TEntity Entity);
    public TEntity Find(String id);
    public ArrayList<TEntity> List();
}
