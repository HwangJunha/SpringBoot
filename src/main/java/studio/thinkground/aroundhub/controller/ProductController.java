package studio.thinkground.aroundhub.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import studio.thinkground.aroundhub.dto.ProductDTO;
import studio.thinkground.aroundhub.service.ProductService;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){this.productService=productService;}

    @GetMapping(value = "/product/{productId}")
    public ProductDTO getProduct(@PathVariable String productId){
        
        long startTime = System.currentTimeMillis();
        LOGGER.info("[ProductController] perform {} of Around Hub API.", "getPorduct");
        ProductDTO productDto=productService.getProductDTO(productId);
        LOGGER.info("[ProductController] Response :: productId = {}, productName = {}, productPirce = {}, productStock = {}, Response Time = {}ms", 
        productDto.getProductId(), productDto.getProductName(), productDto.getProductPrice(), productDto.getProductStock(), (System.currentTimeMillis()-startTime));
        return productDto;
    }

    @PostMapping(value="/product")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        String productId = productDTO.getProductId();
        String productName = productDTO.getProductName();
        int productPrice = productDTO.getProductPrice();
        int productStock = productDTO.getProductStock();

        return productService.saveProduct(productId,productName,productPrice,productStock);
    }

    @DeleteMapping(value="/product/{productId}")
    public ProductDTO deleteProduct(@PathVariable String productId){return null;}

}
