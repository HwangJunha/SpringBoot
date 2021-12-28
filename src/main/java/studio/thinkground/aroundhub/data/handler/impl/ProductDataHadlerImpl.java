package studio.thinkground.aroundhub.data.handler.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studio.thinkground.aroundhub.data.dao.ProductDAO;
import studio.thinkground.aroundhub.data.entity.ProductEntity;
import studio.thinkground.aroundhub.data.handler.ProductDataHandler;

@Service
@Transactional
public class ProductDataHadlerImpl implements ProductDataHandler {
    ProductDAO productDAO;
    
    @Autowired
    public ProductDataHadlerImpl(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Override
    public ProductEntity getProductEntity(String productId) {
        
        return productDAO.getProduct(productId);
    }

    @Override
    public ProductEntity saveProductEntity(String productId, String productName, int produtPrice, int productStock) {
        ProductEntity productEntity = new ProductEntity(productId, productName,produtPrice,productStock);

        return productDAO.saveProductEntity(productEntity);
    }
}
