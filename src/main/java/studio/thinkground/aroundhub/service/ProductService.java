package studio.thinkground.aroundhub.service;

import studio.thinkground.aroundhub.dto.ProductDTO;

public interface ProductService {
    ProductDTO saveProduct(String productId, String productName, int productPrice, int productStock);
    ProductDTO getProductDTO(String productId);
}
