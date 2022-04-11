package net.unesc.ip.adsecommerce.api.controllers.nosql;

import io.micrometer.core.annotation.Timed;
import net.unesc.ip.adsecommerce.api.dto.ProductDTO;
import net.unesc.ip.adsecommerce.services.nosql.ProductNoSQLService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Timed
@RestController
@RequestMapping("/non-relational-db")
public class NonRelationalDbController {
    private final ProductNoSQLService productNoSQLService;

    public NonRelationalDbController(ProductNoSQLService productNoSQLService) {
        this.productNoSQLService = productNoSQLService;
    }

    @GetMapping("/products")
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
