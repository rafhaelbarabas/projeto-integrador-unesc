package net.unesc.ip.adsecommerce.repositories;

import net.unesc.ip.adsecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
