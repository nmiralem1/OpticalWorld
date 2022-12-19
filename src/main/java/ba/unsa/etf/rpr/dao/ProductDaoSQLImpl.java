package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Product;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDaoSQLImpl implements ProductDao {
    private Connection connection;

    public ProductDaoSQLImpl(){
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
    public Product getById(int id) {
        String query = "SELECT * FROM product WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setSize(rs.getInt("size"));
                product.setQuantity(rs.getInt("quantity"));
                rs.close();
                return product;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    @Override
    public Product add(Product item) {
        String insert = "INSERT INTO product(name) VALUES(?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getCategory());
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
    public Product update(Product item) {
        String insert = "UPDATE product SET name = ? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getId());
            stmt.setObject(2, item.getCategory());
            stmt.setObject(3, item.getPrice());
            stmt.setObject(4, item.getSize());
            stmt.setObject(5,item.getQuantity());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM product WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM product";
        List<Product> products = new ArrayList<Product>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setSize(rs.getInt("size"));
                product.setQuantity(rs.getInt("quantity"));
                products.add(product);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return products;
    }
}
