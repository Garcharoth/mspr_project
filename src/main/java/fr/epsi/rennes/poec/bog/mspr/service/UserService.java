package fr.epsi.rennes.poec.bog.mspr.service;

import fr.epsi.rennes.poec.bog.mspr.dao.UserDAO;
import fr.epsi.rennes.poec.bog.mspr.domain.User;
import fr.epsi.rennes.poec.bog.mspr.domain.UserRole;
import fr.epsi.rennes.poec.bog.mspr.exception.TechnicalException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LogManager.getLogger(UserService.class);
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDetails user = userDAO.getUserByMail(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found : " + username);
            }
            return user;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new TechnicalException(e);
        }
    }

    public void addEmployee(User user) {
        try {
            userDAO.setRole(UserRole.ROLE_EMPLOYEE.name());
            userDAO.addEmployee(user);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new TechnicalException(e);
        }
    }
    public void addSalesman(User user) {
        try {
            userDAO.setRole(UserRole.ROLE_SALESMAN.name());
            userDAO.addSalesman(user);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new TechnicalException(e);
        }
    }
}
