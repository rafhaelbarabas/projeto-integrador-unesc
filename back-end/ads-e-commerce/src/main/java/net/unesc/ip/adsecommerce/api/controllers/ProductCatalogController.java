package net.unesc.ip.adsecommerce.api.controllers;

import net.unesc.ip.adsecommerce.api.dto.ProductDTO;
import net.unesc.ip.adsecommerce.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product-catalog")
public class ProductCatalogController {

    private final ProductService productService;

    public ProductCatalogController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/relational/find-all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.findAll();
        return ResponseEntity
                .ok()
                .body(products);
    }

}
