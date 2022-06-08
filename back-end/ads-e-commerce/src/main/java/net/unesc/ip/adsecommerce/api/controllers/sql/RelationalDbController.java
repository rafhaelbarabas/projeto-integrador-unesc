package net.unesc.ip.adsecommerce.api.controllers.sql;

import io.micrometer.core.annotation.Timed;
import net.unesc.ip.adsecommerce.api.dto.ProductDTO;
import net.unesc.ip.adsecommerce.services.sql.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relational-db")
@Timed
public class RelationalDbController {
    private final ProductService productService;

    public RelationalDbController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    @Timed(value = "relational-db")
    public ResponseEntity<List<ProductDTO>> getProductsFromRelationalDatabase(
            @RequestParam(value = "brands", required = false) List<String> brands,
            @RequestParam(value = "models", required = false) List<String> models,
            @RequestParam(value = "categories", required = false) List<String> categories,
            Pageable pageable) {

        List<ProductDTO> products = productService.find(brands, models, categories).subList(0, 10);

        return ResponseEntity
                .ok()
                .body(products);
    }
}
