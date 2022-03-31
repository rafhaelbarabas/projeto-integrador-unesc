package net.unesc.ip.adsecommerce.repositories.sql;

import net.unesc.ip.adsecommerce.entities.sql.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
