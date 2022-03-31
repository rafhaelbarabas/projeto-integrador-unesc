package net.unesc.ip.adsecommerce.entities.nosql;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("produtos")
public class ProductNoSQL {

    @Id
    private String id;

    private String description;

    private BigDecimal price;

    private String categoryId;

    private String brandId;

    private String modelId;

    public ProductNoSQL() {
    }

    public ProductNoSQL(String id, String description, BigDecimal price, String categoryId, String brandId, String modelId) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.modelId = modelId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}
