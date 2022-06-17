package fr.epsi.rennes.poec.bog.mspr.dao;

import fr.epsi.rennes.poec.bog.mspr.domain.Customer;
import fr.epsi.rennes.poec.bog.mspr.exception.TechnicalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDAO {
    @Autowired
    private DataSource ds;

    public void createCustomer(String mail, String last_name, String first_name,
                               String street_name, String postal_code, String city) {
        String sql = "insert into customer" +
                "(mail, last_name, first_name, street_name, postal_code, city) " +
                "values (?, ?, ?, ?, ?, ?);";
        try (
                Connection conn = ds.getConnection();
                PreparedStatement ps = conn.prepareStatement(
                        sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, mail);
            ps.setString(2, last_name);
            ps.setString(3, first_name);
            ps.setString(4, street_name);
            ps.setString(5, postal_code);
            ps.setString(6, city);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                rs.getInt(1);
                return;
            }
        } catch (SQLException e) {
            throw new TechnicalException(e);
        }
        throw new TechnicalException(new SQLException("Customer create error"));
    }

    public List<Customer> getAllNameCustomer() throws SQLException {

        String sql = "select " +
                "customer.id as Id, " +
                "customer.last_name as LastName, " +
                "customer.first_name as FirstName " +
                "from customer;";
        try (
                Connection conn = ds.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            List<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setLast_name(rs.getString("lastname"));
                customer.setFirst_name(rs.getString("firstname"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            throw new TechnicalException(e);
        }

    }

    public List<Customer> getAllInfoCustomer() throws SQLException {
        String sql = "select " +
                "customer.id as Id, " +
                "customer.mail as Mail, " +
                "customer.last_name as LastName, " +
                "customer.first_name as FirstName, " +
                "customer.street_name as StreetName, " +
                "customer.postal_code as PostalCode, " +
                "customer.city as City " +
                "from customer;";
        try (
                Connection conn = ds.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("Id"));
                customer.setMail(rs.getString("Mail"));
                customer.setLast_name(rs.getString("LastName"));
                customer.setFirst_name(rs.getString("FirstName"));
                customer.setStreet_name(rs.getString("StreetName"));
                customer.setPostal_code(rs.getString("PostalCode"));
                customer.setCity(rs.getString("City"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            throw new TechnicalException(e);
        }

    }

    public void updateCustomer(Customer update_customer) {
        String sql = "UPDATE customer " +
                "SET mail = ?, " +
                "last_name = ?, " +
                "first_name = ?, " +
                "street_name = ?, " +
                "postal_code = ?, " +
                "city = ? " +
                "WHERE customer.id = ?;";
        try (
                Connection conn = ds.getConnection();
                PreparedStatement ps = conn.prepareStatement(
                        sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(7, update_customer.getId());
            ps.setString(1, update_customer.getMail());
            ps.setString(2, update_customer.getLast_name());
            ps.setString(3, update_customer.getFirst_name());
            ps.setString(4, update_customer.getStreet_name());
            ps.setString(5, update_customer.getPostal_code());
            ps.setString(6, update_customer.getCity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new TechnicalException(e);
        }
        throw new TechnicalException(new SQLException("Customer create error"));
    }
}

