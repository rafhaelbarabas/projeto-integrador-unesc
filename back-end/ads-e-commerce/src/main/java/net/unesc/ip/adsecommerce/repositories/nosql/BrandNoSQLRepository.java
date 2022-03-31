package net.unesc.ip.adsecommerce.repositories.nosql;

import net.unesc.ip.adsecommerce.entities.nosql.BrandNoSQL;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BrandNoSQLRepository extends MongoRepository<BrandNoSQL, Long> {
}
