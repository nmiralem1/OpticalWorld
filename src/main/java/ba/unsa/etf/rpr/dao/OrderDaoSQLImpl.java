package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;
import ba.unsa.etf.rpr.business.GlassesManager;
import ba.unsa.etf.rpr.business.UserManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 */
public class OrderDaoSQLImpl extends AbstractDao<Order> implements OrderDao {

    private static OrderDaoSQLImpl instance = null;
    private final GlassesManager r = new GlassesManager();
    private final UserManager u = new UserManager();
    private OrderDaoSQLImpl() {
        super("reservations");
    }

    public static OrderDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new OrderDaoSQLImpl();
        return instance;
    }

    /**
     * Remove instance.
     */
    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Order row2object(ResultSet rs) throws GlassesException{
        try {
            Order reservation = new Order();
            reservation.setId(rs.getInt("id"));
            reservation.setTotal(rs.getInt("total"));
            reservation.setGlasses(DaoFactory.glassesDao().getById(rs.getInt("glassesID")));
            return reservation;
        } catch (Exception e) {
            throw new GlassesException(e.getMessage(), e);
        }
    }

    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(Order object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("userID", object.getUserId().getId());
        item.put("glassesID",object.getGlasses().getId());
        item.put("total",object.getTotal());
        return item;
    }

    @Override
    public int totalIncome() throws SQLException {
        int totalIncome = 0;
        String query = "SELECT SUM(total) AS total_price FROM reservations";
        try (PreparedStatement st = AbstractDao.getConnection().prepareStatement(query)) {
            ResultSet result = st.executeQuery();
            if (result.next()) totalIncome = result.getInt("total_price");
        }
        return totalIncome;
    }

    public List<Order> getAllForUser(User user){
        List<Order> userReservations = new ArrayList<>();
        // Connect to the database
        try{
            // Prepare a statement to execute the query
            String query = "SELECT * FROM reservations WHERE userID = ?";
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setObject(1, user.getId());
            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();
            // Iterate over the result set and add each hotel to the list
            while (resultSet.next()) {

                int user_id = resultSet.getInt("userID");
                int room_id = resultSet.getInt("roomID");
                Glasses glasses = r.getById(room_id);
                User user2 = u.getById(user_id);
                int total = resultSet.getInt("total");
                Order reservation = new Order(total, user2, glasses);
                userReservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (GlassesException e) {
            throw new RuntimeException(e);
        }
        // Return the list of hotels
        return userReservations;
    }
}