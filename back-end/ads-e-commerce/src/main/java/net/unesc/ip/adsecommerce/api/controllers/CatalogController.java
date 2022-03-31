package net.unesc.ip.adsecommerce.api.controllers;

import net.unesc.ip.adsecommerce.api.dto.ProductDTO;
import net.unesc.ip.adsecommerce.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    private final ProductService productService;

    public CatalogController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/relational-db/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(
            @RequestParam(value = "brands", required = false) List<String> brands,
            @RequestParam(value = "models", required = false) List<String> models,
            @RequestParam(value = "categories", required = false) List<String> categories) {
        List<ProductDTO> products = productService.find(brands, models, categories);
        return ResponseEntity
                .ok()
                .body(products);
    }

}
