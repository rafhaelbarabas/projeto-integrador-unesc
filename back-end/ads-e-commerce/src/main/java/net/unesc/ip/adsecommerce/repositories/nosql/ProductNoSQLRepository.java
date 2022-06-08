package net.unesc.ip.adsecommerce.repositories.nosql;

import net.unesc.ip.adsecommerce.entities.nosql.CategoryNoSQL;
import net.unesc.ip.adsecommerce.entities.nosql.ProductNoSQL;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductNoSQLRepository extends MongoRepository<ProductNoSQL, Long> {
    ProductNoSQL findById(String id);

    ProductNoSQL findByDescriptionContaining(String description);
}
