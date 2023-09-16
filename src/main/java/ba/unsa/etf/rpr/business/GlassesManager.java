package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.exceptions.GlassesException;

import java.sql.SQLException;
import java.util.List;

public class GlassesManager {
    public void delete(int RoomId) throws GlassesException {
        try {
            DaoFactory.glassesDao().delete(RoomId);
        } catch (GlassesException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new GlassesException("NO");
            }
            throw e;
        }

    }

    public static Glasses add(Glasses r) throws GlassesException {
        return DaoFactory.glassesDao().add(r);
    }

    public Glasses getById(int id) throws GlassesException {
        return DaoFactory.glassesDao().getById(id);
    }

    public Glasses update(Glasses r) throws GlassesException {
        return DaoFactory.glassesDao().update(r);
    }

    public static List<Glasses> getAll() throws GlassesException {
        return DaoFactory.glassesDao().getAll();
    }

    public int totalGlasses() throws SQLException{
        return DaoFactory.glassesDao().totalGlasses();
    }

    public List<Glasses> getAllByCategory(String category){
        return DaoFactory.glassesDao().getAllByCategory(category);
    }
}