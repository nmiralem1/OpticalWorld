package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.exceptions.GlassesException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class GlassesDaoSQLImpl extends AbstractDao<Glasses> implements GlassesDao {
    private static GlassesDaoSQLImpl instance = null;
    private GlassesDaoSQLImpl() {
        super("rooms");
    }


    public static GlassesDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new GlassesDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Glasses row2object(ResultSet rs) throws GlassesException {
        try {
            Glasses glasses = new Glasses();
            glasses.setId(rs.getInt("id"));
            glasses.setCategory(rs.getString("category"));
            glasses.setPrice(rs.getInt("price"));
            return glasses;
        } catch (Exception e) {
            throw new GlassesException(e.getMessage(), e);
        }
    }


    @Override
    public Map<String, Object> object2row(Glasses object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("category", object.getCategory());
        item.put("pricePerNight", object.getPrice());
        return item;
    }

    public int totalGlasses() throws SQLException {
        int total = 0;
        String query = "SELECT count(id) AS total_rooms FROM rooms";
        try (PreparedStatement st = AbstractDao.getConnection().prepareStatement(query)) {
            ResultSet result = st.executeQuery();
            if (result.next()) total = result.getInt("total_rooms");
        }
        return total;
    }


}