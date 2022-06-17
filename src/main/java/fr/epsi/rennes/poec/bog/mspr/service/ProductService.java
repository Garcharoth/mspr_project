package fr.epsi.rennes.poec.bog.mspr.service;

import fr.epsi.rennes.poec.bog.mspr.dao.ProductDAO;
import fr.epsi.rennes.poec.bog.mspr.domain.Product;
import fr.epsi.rennes.poec.bog.mspr.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    public List<Product> getAllProducts() throws SQLException {

        List<Product> products = productDAO.getAllProducts();
        return products;
    }

    public Product getProductById(int productId) throws SQLException{
        Product product = productDAO.getProductById(productId);
        return product;
    }

    public void addProduct(Product product) throws BusinessException, SQLException {
        if (product.getModel() == null){
            throw new BusinessException("Product.model.null");
        }
        int productId = productDAO.addProduct(product.getModel(), product.getBrand(), product.getPrice(), product.getQuantity());
    }

    public void deleteProduct(Product product) throws BusinessException, SQLException {
        if (product.getModel() == null){
            throw new BusinessException("Product.model.null");
        }
        productDAO.deleteProduct(product.getId());
    }

    public void updateProduct(Product product) throws BusinessException, SQLException{
        System.out.println(product.getId());
        if (product.getModel() == null){
            throw new BusinessException("Product.model.null");
        }
        productDAO.updateProduct(product.getModel(), product.getBrand(), product.getPrice(), product.getQuantity(), product.getId());
    }

}
