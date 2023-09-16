package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Glasses;

import java.sql.SQLException;
import java.util.List;

public interface GlassesDao extends Dao<Glasses> {

    int totalGlasses() throws SQLException;

    List<Glasses> getAllByCategory(String category);
}