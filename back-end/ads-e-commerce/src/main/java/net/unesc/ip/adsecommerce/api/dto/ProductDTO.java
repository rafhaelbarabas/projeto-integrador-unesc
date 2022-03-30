package net.unesc.ip.adsecommerce.api.dto;

import net.unesc.ip.adsecommerce.entities.Product;

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
