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

    private String category;

    private String brand;

    private String model;
}
