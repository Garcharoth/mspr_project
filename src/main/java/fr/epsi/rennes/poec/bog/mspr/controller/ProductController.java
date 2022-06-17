package fr.epsi.rennes.poec.bog.mspr.controller;

import fr.epsi.rennes.poec.bog.mspr.domain.Product;
import fr.epsi.rennes.poec.bog.mspr.domain.Response;
import fr.epsi.rennes.poec.bog.mspr.exception.BusinessException;
import fr.epsi.rennes.poec.bog.mspr.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public Response<List<Product>> getAllProducts() throws SQLException {

        List<Product> products = productService.getAllProducts();

        Response<List<Product>> response = new Response<>();
        response.setData(products);

        return response;

    }

    @GetMapping("/product")
    public Response<Product> getProductById(@RequestParam int productId) throws SQLException{
        Product product = productService.getProductById(productId);

        Response<Product> response = new Response<>();
        response.setData(product);

        return response;
    }

    @PostMapping("/products")
    public Response<Product> addProduct(@RequestBody Product product) throws BusinessException, SQLException {
        logger.info("Product creation");
        productService.addProduct(product);
        return new Response<>();
    }

    @PostMapping("/delete_products")
    public Response<Product> deleteProduct(@RequestBody Product product) throws BusinessException, SQLException {
        logger.info("Product suppression");
        productService.deleteProduct(product);
        return new Response<>();
    }

    @PostMapping("/update_products")
    public Response<Product> updateProduct(@RequestBody Product product) throws BusinessException,SQLException{
        logger.info("Product update");
        productService.updateProduct(product);
        return new Response<>();
    }
}
