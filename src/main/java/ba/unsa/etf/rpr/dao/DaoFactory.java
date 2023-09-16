package ba.unsa.etf.rpr.dao;
/**
 * Factory method for singleton implementation of DAOs
 *
 * @author nmiralem1
 */
public class DaoFactory {

    private static final GlassesDao glassesDao = GlassesDaoSQLImpl.getInstance();
    private static final UserDao userDao = UserDaoSQLImpl.getInstance();
    private static final OrderDao orderDao = OrderDaoSQLImpl.getInstance();


    private DaoFactory(){
    }

    public static GlassesDao glassesDao(){
        return glassesDao;
    }
    public static OrderDao orderDao(){
        return orderDao;
    }
    public static UserDao userDao(){
        return userDao;
    }

}