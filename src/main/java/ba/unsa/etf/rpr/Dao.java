package ba.unsa.etf.rpr;
import java.util.List;

public interface Dao<H> {
    H getById(int id);
    H add(H item);
    H update(H item);
    void delete(int id);
    List<H>getAll();
}
