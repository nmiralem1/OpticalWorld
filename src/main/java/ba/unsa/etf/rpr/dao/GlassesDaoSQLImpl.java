package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.exceptions.GlassesException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GlassesDaoSQLImpl extends AbstractDao<Glasses> implements GlassesDao {
    private static GlassesDaoSQLImpl instance = null;
    private GlassesDaoSQLImpl() {
        super("glasses");
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
            glasses.setName(rs.getString("name"));
            glasses.setCategory(rs.getString("category"));
            glasses.setPrice(rs.getInt("price"));
            glasses.setImage(rs.getString("image"));
            return glasses;
        } catch (Exception e) {
            throw new GlassesException(e.getMessage(), e);
        }
    }


    @Override
    public Map<String, Object> object2row(Glasses object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("name",object.getName());
        item.put("category", object.getCategory());
        item.put("price", object.getPrice());
        item.put("image",object.getImage());
        return item;
    }

    public int totalGlasses() throws SQLException {
        int total = 0;
        String query = "SELECT count(id) AS total_glasses FROM glasses";
        try (PreparedStatement st = AbstractDao.getConnection().prepareStatement(query)) {
            ResultSet result = st.executeQuery();
            if (result.next()) total = result.getInt("total_glasses");
        }
        return total;
    }

    public List<Glasses> getAllByCategory(String category) {
        List<Glasses> glasses = new ArrayList<>();

        // Connect to the database
        try {
            // Prepare a statement to execute the query
            String query = "SELECT * FROM glasses WHERE category = ?";
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setObject(1, category);

            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set and add each pair of glasses to the list
            while (resultSet.next()) {
                Glasses g = new Glasses();
                g.setId(resultSet.getInt("id"));
                g.setName(resultSet.getString("name"));
                g.setCategory(resultSet.getString("category"));
                g.setImage(resultSet.getString("image"));
                g.setPrice(resultSet.getDouble("price"));
                glasses.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return the list of glasses
        return glasses;
    }


}