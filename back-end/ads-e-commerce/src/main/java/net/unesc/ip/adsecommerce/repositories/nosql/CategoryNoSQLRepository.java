package net.unesc.ip.adsecommerce.repositories.nosql;

import net.unesc.ip.adsecommerce.entities.nosql.CategoryNoSQL;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryNoSQLRepository extends MongoRepository<CategoryNoSQL, Long> {
    CategoryNoSQL findById(String id);

    CategoryNoSQL findByDescriptionContaining(String description);
}
