package net.unesc.ip.adsecommerce.api.dto;

import net.unesc.ip.adsecommerce.entities.nosql.ProductNoSQL;
import net.unesc.ip.adsecommerce.entities.sql.Product;
import net.unesc.ip.adsecommerce.services.nosql.ProductNoSQLService;

import java.math.BigDecimal;

public class ProductDTO {

    private final Long id;

    private final String description;

    private final BigDecimal price;

    private final String category;

    private final String brand;

    private final String model;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = product.getCategory().getDescription();
        this.brand = product.getBrand().getDescription();
        this.model = product.getModel().getDescription();
    }

    // TODO: Criar um método no service para fazer essa conversão
    public ProductDTO(ProductNoSQL productNoSQL) {
        this.id = Long.valueOf(productNoSQL.getId());
        this.description = productNoSQL.getDescription();
        this.price = productNoSQL.getPrice();
        this.category = "TODO";
        this.brand = "TODO";
        this.model = "TODO";
    }



    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }
}
