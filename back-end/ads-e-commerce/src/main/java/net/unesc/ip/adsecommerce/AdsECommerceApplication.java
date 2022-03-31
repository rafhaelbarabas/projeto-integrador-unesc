package net.unesc.ip.adsecommerce;

import net.unesc.ip.adsecommerce.repositories.nosql.ProductNoSQLRepository;
import net.unesc.ip.adsecommerce.repositories.sql.BrandRepository;
import net.unesc.ip.adsecommerce.repositories.sql.CategoryRepository;
import net.unesc.ip.adsecommerce.repositories.sql.ModelRepository;
import net.unesc.ip.adsecommerce.repositories.sql.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableJpaRepositories(basePackageClasses = {
        BrandRepository.class,
        CategoryRepository.class,
        ModelRepository.class,
        ProductRepository.class
})
@EnableMongoRepositories(basePackageClasses = ProductNoSQLRepository.class)
@SpringBootApplication
public class AdsECommerceApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(AdsECommerceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AdsECommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("ADS E-COMMERCE IS ONLINE.");
    }
}
