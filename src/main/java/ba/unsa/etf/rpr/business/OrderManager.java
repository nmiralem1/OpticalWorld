package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.exceptions.GlassesException;

import java.sql.SQLException;
import java.util.List;

public class OrderManager {
    public void delete(int OrderId) throws GlassesException {
        try {
            DaoFactory.orderDao().delete(OrderId);
        } catch (GlassesException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new GlassesException("NO");
            }
            throw e;
        }

    }

    public Order add(Order r) throws GlassesException {
        return DaoFactory.orderDao().add(r);
    }

    public Order getById(int id) throws GlassesException {
        return DaoFactory.orderDao().getById(id);
    }

    public Order update(Order r) throws GlassesException {
        return DaoFactory.orderDao().update(r);
    }

    public static List<Order> getAll() throws GlassesException {
        return DaoFactory.orderDao().getAll();
    }
    public int totalIncome() throws SQLException{
        return DaoFactory.orderDao().totalIncome();
    }
    public List<Order> getAllForUser(User user) throws SQLException{
        return DaoFactory.orderDao().getAllForUser(user);
    }
}