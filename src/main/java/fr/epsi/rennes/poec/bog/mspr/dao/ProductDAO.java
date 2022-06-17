package fr.epsi.rennes.poec.bog.mspr.dao;

import fr.epsi.rennes.poec.bog.mspr.domain.Product;
import fr.epsi.rennes.poec.bog.mspr.exception.TechnicalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO {

    @Autowired
    private DataSource ds;


    public List<Product> getAllProducts() throws SQLException {
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        try (Connection conn = ds.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){


            // attention au nom des colonnes de la table ainsi qu'à la quantité
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setBrand(rs.getString("brand"));
                product.setModel(rs.getString("model"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                products.add(product);

            }

        }catch (SQLException e){
            throw new TechnicalException(e);
        }
    return products;
    }

    public Product getProductById (int productId){
        String sql = "select * " +
                "from products " +
                "where products.id = ? ";

        try(Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setModel(rs.getString("model"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));

                return product;
            }
        }catch (SQLException e){
            throw new TechnicalException(e);
        }


        return null;
    }

    public int addProduct(String model, String brand, double price, int quantity) throws SQLException{
        String sql = "insert into products (model, brand, price, quantity) values (?, ?, ?, ?)";
        try (Connection conn = ds.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, model);
            ps.setString(2, brand);
            ps.setDouble(3, price);
            ps.setInt(4, quantity);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
            throw new TechnicalException(new SQLException("Error creating product"));
        }catch (SQLException e){
            throw new TechnicalException(e);
        }
    }

    public void deleteProduct(int id) throws  SQLException {
        String sql = "delete from products where id = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            throw new TechnicalException(e);
        }
    }

    public void updateProduct(String model, String brand, double price, int quantity, int id) throws SQLException{
        String sql = "Update products set model = ?, brand = ?, price = ?, quantity = ? where id = ?;";
        System.out.printf(sql);

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, model);
            ps.setString(2, brand);
            ps.setDouble(3, price);
            ps.setInt(4, quantity);
            ps.setInt(5, id);
            System.out.println(id);
            ps.executeUpdate();
        } catch (SQLException e){
            throw  new TechnicalException(e);
        }
    }
}
