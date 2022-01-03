package studio.thinkground.aroundhub.controller;



import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.models.Response;
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
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO){
        
        //Validation Code Example 
        //잘 못된것은 아니지만 가독성이 떨어지기 때문에 지양하는것이 좋다
        //@Valid의 어노테이션으로 처리하자
        if(productDTO.getProductId().equals("") || productDTO.getProductId().isEmpty()){
            LOGGER.error("[createProduct] faild Response :: product is Empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productDTO);
        }
        
        String productId = productDTO.getProductId();
        String productName = productDTO.getProductName();
        int productPrice = productDTO.getProductPrice();
        int productStock = productDTO.getProductStock();

        ProductDTO response = productService.saveProduct(productId,productName,productPrice,productStock);

        LOGGER.info("[createProduct] Response >. productId: {}, productName: {}, productPrice: {}, productStock : {]", response.getProductId(), response.getProductName(), response.getProductPrice(), response.getProductStock());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value="/product/{productId}")
    public ProductDTO deleteProduct(@PathVariable String productId){return null;}

}
