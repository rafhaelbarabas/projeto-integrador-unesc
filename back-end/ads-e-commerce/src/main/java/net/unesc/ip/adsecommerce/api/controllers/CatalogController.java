package net.unesc.ip.adsecommerce.api.controllers;

import net.unesc.ip.adsecommerce.api.dto.ProductDTO;
import net.unesc.ip.adsecommerce.services.nosql.ProductNoSQLService;
import net.unesc.ip.adsecommerce.services.sql.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    private final ProductService productService;
    private final ProductNoSQLService productNoSQLService;

    public CatalogController(ProductService productService, ProductNoSQLService productNoSQLService) {
        this.productService = productService;
        this.productNoSQLService = productNoSQLService;
    }

    @GetMapping("/relational-db/products")
    public ResponseEntity<List<ProductDTO>> getProductsFromRelationalDatabase(
            @RequestParam(value = "brands", required = false) List<String> brands,
            @RequestParam(value = "models", required = false) List<String> models,
            @RequestParam(value = "categories", required = false) List<String> categories) {
        List<ProductDTO> products = productService.find(brands, models, categories);
        return ResponseEntity
                .ok()
                .body(products);
    }

    @GetMapping("/non-relational-db/products")
    public ResponseEntity<List<ProductDTO>> getProductsFromNoSQLDatabase(
            @RequestParam(value = "brands", required = false) List<String> brands,
            @RequestParam(value = "models", required = false) List<String> models,
            @RequestParam(value = "categories", required = false) List<String> categories) {

        List<ProductDTO> products = productNoSQLService.findAll()
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(products);
    }
}
