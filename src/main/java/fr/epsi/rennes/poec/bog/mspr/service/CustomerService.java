package fr.epsi.rennes.poec.bog.mspr.service;

import fr.epsi.rennes.poec.bog.mspr.dao.CustomerDAO;
import fr.epsi.rennes.poec.bog.mspr.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    public List<Customer> getAllNameCustomer() throws SQLException {
        return customerDAO.getAllNameCustomer();
    }

    public List<Customer> getAllInfoCustomer() throws SQLException {
        return customerDAO.getAllInfoCustomer();
    }

    public void createCustomer(Customer customer) {
        customerDAO.createCustomer(customer.getMail(), customer.getLast_name(),
                customer.getFirst_name(), customer.getStreet_name(),
                customer.getPostal_code(), customer.getCity());
    }

    public void updateCustomer(Customer update_customer){
        customerDAO.updateCustomer(update_customer);
    }
}

