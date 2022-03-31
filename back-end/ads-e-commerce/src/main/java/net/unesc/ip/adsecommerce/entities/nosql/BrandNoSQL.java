package net.unesc.ip.adsecommerce.entities.nosql;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("marcas")
public class BrandNoSQL {

    @Id
    private String id;

    private String description;

    public BrandNoSQL() {
    }

    public BrandNoSQL(String id, String description) {
        this.id = id;
        this.description = description;
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
}
