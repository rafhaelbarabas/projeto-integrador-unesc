package net.unesc.ip.adsecommerce.repositories.nosql;

import net.unesc.ip.adsecommerce.entities.nosql.ModelNoSQL;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ModelNoSQLRepository extends MongoRepository<ModelNoSQL, Long> {
}
