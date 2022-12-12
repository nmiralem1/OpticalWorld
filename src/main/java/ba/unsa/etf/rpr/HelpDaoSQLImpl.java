package ba.unsa.etf.rpr;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelpDaoSQLImpl implements HelpDao{
    private Connection connection;

    public HelpDaoSQLImpl(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7582891", "sql7582891", "K5kVjGguVJ");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Help getById(int id) {
        String query = "SELECT * FROM categories WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Help help = new Help();
                help.setOrderId(rs.getInt("orderId"));
                help.setProductId(rs.getInt("ProductId"));
                rs.close();
                return help;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    @Override
    public Help add(Help item) {
        String insert = "INSERT INTO categories(name) VALUES(?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setProductId(rs.getInt(1)); //set id to return it back
            item.setOrderId(rs.getInt(2));
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Help update(Help item) {
        String insert = "UPDATE categories SET name = ? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getOrderId());
            stmt.setObject(2, item.getProductId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM categories WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Help> getAll() {
        String query = "SELECT * FROM categories";
        List<Help> Help = new ArrayList<Help>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Help help = new Help();
                help.setProductId(rs.getInt("productId"));
                help.setOrderId(rs.getInt("orderId"));
                Help.add(help);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return Help;
    }

}
