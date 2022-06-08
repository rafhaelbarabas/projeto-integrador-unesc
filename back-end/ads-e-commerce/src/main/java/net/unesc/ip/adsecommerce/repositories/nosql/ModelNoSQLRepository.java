package net.unesc.ip.adsecommerce.repositories.nosql;

import net.unesc.ip.adsecommerce.entities.nosql.CategoryNoSQL;
import net.unesc.ip.adsecommerce.entities.nosql.ModelNoSQL;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ModelNoSQLRepository extends MongoRepository<ModelNoSQL, Long> {
    ModelNoSQL findById(String id);

    ModelNoSQL findByDescriptionContaining(String description);
}
