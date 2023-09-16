package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;

import java.sql.SQLException;
/**
 * Dao interface for User domain bean
 *
 * @author nmiralem1
 */
public interface UserDao extends Dao<User> {
    User findUsername(String usernameField) throws GlassesException;
    int totalUsers() throws SQLException;
}