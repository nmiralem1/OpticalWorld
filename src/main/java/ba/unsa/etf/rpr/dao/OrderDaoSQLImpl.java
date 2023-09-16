package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.business.GlassesManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        super("orders");
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
            reservation.setUserID(DaoFactory.userDao().getById(rs.getInt("userID")));
            reservation.setGlassesID(DaoFactory.glassesDao().getById(rs.getInt("glassesID")));
            reservation.setTotal(rs.getInt("total"));
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
        item.put("userID", object.getUserID().getId());
        item.put("glassesID",object.getGlassesID().getId());
        item.put("total",object.getTotal());
        return item;
    }

    @Override
    public int totalIncome() throws SQLException {
        int totalIncome = 0;
        String query = "SELECT SUM(total) AS total_price FROM orders";
        try (PreparedStatement st = AbstractDao.getConnection().prepareStatement(query)) {
            ResultSet result = st.executeQuery();
            if (result.next()) totalIncome = result.getInt("total_price");
        }
        return totalIncome;
    }

    public List<Order> getAllForUser(User user) {
        List<Order> userReservations = new ArrayList<>();

        try {
            // Prepare a statement to execute the query
            String query = "SELECT glassesID, userID, total FROM orders WHERE userID = ?";
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setObject(1, user.getId());

            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int glassesId = resultSet.getInt("glassesID");
                int userId = resultSet.getInt("userID");
                int total = resultSet.getInt("total");

                // Create an Order object using the retrieved values
                Order reservation = new Order(r.getById(glassesId), u.getById(userId), total);
                userReservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (GlassesException e) {
            throw new RuntimeException(e);
        }

        // Return the list of orders
        return userReservations;
    }

}