package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {
    private static UserDaoSQLImpl instance = null;
    private UserDaoSQLImpl() {
        super("users");
    }
    public static UserDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new UserDaoSQLImpl();
        return instance;
    }
    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }
    @Override
    public User row2object(ResultSet rs) throws GlassesException{
        try {
            User person = new User();
            person.setId(rs.getInt("id"));
            person.setUsername(rs.getString("username"));
            person.setFirstName(rs.getString("firstName"));
            person.setLastName(rs.getString("lastName"));
            person.setEmail(rs.getString("email"));
            person.setRole(rs.getInt("isAdministrator"));
            person.setPassword(rs.getString("password"));
            return person;
        } catch (Exception e) {
            throw new GlassesException(e.getMessage(), e);
        }
    }

    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(User object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("username", object.getUsername());
        item.put("firstName", object.getFirstName());
        item.put("lastName", object.getLastName());
        item.put("email", object.getEmail());
        item.put("isAdministrator", object.getRole());
        item.put("password", object.getPassword());
        return item;
    }

    @Override
    public User findUsername(String usernameField) throws GlassesException{
        String find = "SELECT id from users where username='" + usernameField +"'";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(find, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) { // result set is iterator.
                return getById(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int totalUsers() throws SQLException{
        int total = 0;
        String query = "SELECT count(username) AS total_users FROM users";
        try (PreparedStatement st = AbstractDao.getConnection().prepareStatement(query)) {
            ResultSet result = st.executeQuery();
            if (result.next()) total = result.getInt("total_users");
        }
        return total;
    }
}