package net.unesc.ip.adsecommerce.repositories.sql;

import net.unesc.ip.adsecommerce.entities.sql.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
