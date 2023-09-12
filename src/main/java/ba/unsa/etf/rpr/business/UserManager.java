package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GlassesException;
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
    private static final String HASHING_ALGORITHM = "SHA-256";

    /**
     * Hash password string.
     *
     * @param password the password
     * @return the string
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM);
        messageDigest.update(password.getBytes());

        byte[] hashedPassword = messageDigest.digest();

        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashedPassword) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
    public int totalUsers() throws SQLException{
        return DaoFactory.userDao().totalUsers();
    }
}