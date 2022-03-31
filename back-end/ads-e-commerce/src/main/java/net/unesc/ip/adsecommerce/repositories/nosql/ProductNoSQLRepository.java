package net.unesc.ip.adsecommerce.repositories.nosql;

import net.unesc.ip.adsecommerce.entities.nosql.ProductNoSQL;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductNoSQLRepository extends MongoRepository<ProductNoSQL, Long> {
}
