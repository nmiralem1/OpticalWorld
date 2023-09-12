package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Glasses;

import java.sql.SQLException;

public interface GlassesDao extends Dao<Glasses> {

    public int totalGlasses() throws SQLException;
}