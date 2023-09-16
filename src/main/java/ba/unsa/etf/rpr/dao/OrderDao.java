package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.User;

import java.sql.SQLException;
import java.util.List;
/**
 * Dao interface for Reservation domain bean
 *
 * @author nmiralem1
 */
public interface OrderDao extends Dao<Order> {
    int totalIncome() throws SQLException;
    List<Order> getAllForUser(User user) throws SQLException;
}