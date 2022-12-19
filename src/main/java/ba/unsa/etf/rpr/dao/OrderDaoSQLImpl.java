package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Order;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OrderDaoSQLImpl implements OrderDao {

    private Connection connection;

    public OrderDaoSQLImpl(){
        try{
            FileReader reader = new FileReader("src/main/resources/data.properties");
            Properties p = new Properties();
            p.load(reader);
            String s1 = p.getProperty("url");
            String s2 = p.getProperty("user");
            String s3 = p.getProperty("password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(s1,s2,s3);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public Order getById(int id) {
        String query = "SELECT * FROM order WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("userId"));
                order.setNote(rs.getString("note"));
                order.setTime(rs.getTime("time"));
                rs.close();
                return order;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    @Override
    public Order add(Order item) {
        String insert = "INSERT INTO order(name) VALUES(?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getNote());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order update(Order item) {
        String insert = "UPDATE categories SET name = ? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getId());
            stmt.setObject(2, item.getUserId());
            stmt.setObject(3, item.getTime());
            stmt.setObject(4, item.getNote());
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
    public List<Order> getAll() {
        String query = "SELECT * FROM categories";
        List<Order> categories = new ArrayList<Order>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("userID"));
                order.setTime(rs.getTime("time"));
                order.setNote(rs.getString("note"));
                categories.add(order);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return categories;
    }
}
