package fr.epsi.rennes.poec.bog.mspr.controller;

import fr.epsi.rennes.poec.bog.mspr.domain.Customer;
import fr.epsi.rennes.poec.bog.mspr.domain.Response;
import fr.epsi.rennes.poec.bog.mspr.exception.BusinessException;
import fr.epsi.rennes.poec.bog.mspr.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/salesman/add_customer")
    public Response<Void> createCustomer(@RequestBody Customer customer)
            throws BusinessException {
        customerService.createCustomer(customer);
        return new Response<>();
    }

    /*    @GetMapping("/salesman/add_customer")
        public Response<List<Customer>> getAllNameCustomer() throws SQLException {
            List<Customer> customers = customerService.getAllNameCustomer();

            Response<List<Customer>> response = new Response<>();
            response.setData(customers);

            return response;
        }*/
    @GetMapping("/salesman/add_customer")
    public Response<List<Customer>> getAllInfoCustomer()
            throws SQLException {
        List<Customer> customers = customerService.getAllInfoCustomer();

        Response<List<Customer>> response = new Response<>();
        response.setData(customers);

        return response;
    }

    @PutMapping("/salesman/update_customer")
    public Response<Customer> updateCustomer(@RequestBody Customer update_customer) throws SQLException {
        customerService.updateCustomer(update_customer);

        return new Response<>();
    }
}
