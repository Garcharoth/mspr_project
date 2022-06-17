package fr.epsi.rennes.poec.bog.mspr.dao;


import fr.epsi.rennes.poec.bog.mspr.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class UserDAO {
    @Autowired
    private DataSource ds;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserByMail(String mail) throws SQLException {
        String sql = "select mail, password, role " +
                "from user " +
                "where mail = '" + mail + "'";
        Statement stmt = ds.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        User user;
        if (rs.next()) {
            user = new User();
            user.setMail(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setRole(rs.getString(3));
            return user;
        } else {
            return null;
        }
    }

    public void addEmployee(User user) throws SQLException {
        String password = passwordEncoder.encode(user.getPassword());
        String sql = "insert into user (mail, password, role) " + "values ('"
                + user.getMail() + "', '" + password + "','ROLE_EMPLOYEE');";
        Statement stmt = ds.getConnection().createStatement();
        stmt.executeUpdate(sql);
    }

    public void addSalesman(User user) throws SQLException {
        String password = passwordEncoder.encode(user.getPassword());
        String sql = "insert into user (mail, password, role) " + "values ('"
                + user.getMail() + "', '" + password + "','ROLE_SALESMAN');";
        System.out.println(sql);
        Statement stmt = ds.getConnection().createStatement();
        stmt.executeUpdate(sql);
    }

    public void setRole(String name) {
    }
}

