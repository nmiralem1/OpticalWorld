package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class UserManager {
    public void delete(int UserId) throws GlassesException {
        try {
            DaoFactory.userDao().delete(UserId);
        } catch (GlassesException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new GlassesException("NO");
            }
            throw e;
        }

    }

    public static User add(User r) throws GlassesException {
        return DaoFactory.userDao().add(r);
    }

    public User getById(int id) throws GlassesException {
        return DaoFactory.userDao().getById(id);
    }

    public User update(User r) throws GlassesException {
        return DaoFactory.userDao().update(r);
    }

    public static List<User> getAll() throws GlassesException{
        return DaoFactory.userDao().getAll();
    }

    public User findUsername(String usernameField) throws GlassesException {
        return DaoFactory.userDao().findUsername(usernameField);
    }

    public int totalUsers() throws SQLException{
        return DaoFactory.userDao().totalUsers();
    }
}