package studio.thinkground.aroundhub.data.handler;

import studio.thinkground.aroundhub.data.entity.ProductEntity;

public interface ProductDataHandler {
    ProductEntity saveProductEntity(String productId, String productName, int produtPrice, int productStock);
    ProductEntity getProductEntity(String productId);
}
