package net.unesc.ip.adsecommerce.repositories.sql;

import net.unesc.ip.adsecommerce.entities.sql.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {

}
