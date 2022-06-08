package net.unesc.ip.adsecommerce.api.dto;

import net.unesc.ip.adsecommerce.entities.nosql.BrandNoSQL;
import net.unesc.ip.adsecommerce.entities.nosql.CategoryNoSQL;
import net.unesc.ip.adsecommerce.entities.nosql.ModelNoSQL;
import net.unesc.ip.adsecommerce.entities.nosql.ProductNoSQL;
import net.unesc.ip.adsecommerce.entities.sql.Product;

import java.math.BigDecimal;
import java.util.Random;

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

    public ProductDTO(ProductNoSQL productNoSQL, CategoryNoSQL categoryNoSQL,
                      BrandNoSQL brandNoSQL, ModelNoSQL modelNoSQL) {
        this.id = Long.valueOf(productNoSQL.getId());
        this.description = productNoSQL.getDescription();
        this.price = productNoSQL.getPrice();
        this.category = randomize("CAT");
        this.brand = randomize("BRA");
        this.model = randomize("MOD");
    }

    private String randomize(String option) {
        String[] defaults = {"Foo", "Bar", "Jar"};
        String[] categories = {"Coffee", "Flowers", "Dishware", "Cosmetics", "Poultry", "Condiments"};
        String[] brands = {"Mars", "Leadertech Consulting", "21st Century Fox", "ExxonMobil", "Areon Impex", "Mars"};
        String[] models = {"Charlotte", "Philadelphia", "Chicago", "Innsbruck", "Salem", "Minneapolis", "Lisbon"};
        switch (option) {
            case "CAT":
                return categories[new Random().nextInt(categories.length)];
            case "BRA":
                return brands[new Random().nextInt(brands.length)];
            case "MOD":
                return models[new Random().nextInt(models.length)];
        }
        int i = new Random().nextInt(defaults.length);
        return defaults[i];
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
