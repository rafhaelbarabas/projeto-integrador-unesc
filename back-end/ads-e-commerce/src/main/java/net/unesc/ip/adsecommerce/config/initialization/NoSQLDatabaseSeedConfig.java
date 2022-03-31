package net.unesc.ip.adsecommerce.config.initialization;

import net.unesc.ip.adsecommerce.services.nosql.BrandNoSQLService;
import net.unesc.ip.adsecommerce.services.nosql.CategoryNoSQLService;
import net.unesc.ip.adsecommerce.services.nosql.ModelNoSQLService;
import net.unesc.ip.adsecommerce.services.nosql.ProductNoSQLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class NoSQLDatabaseSeedConfig {

    private static final Logger LOG = LoggerFactory.getLogger(NoSQLDatabaseSeedConfig.class);

    private final BrandNoSQLService brandNoSQLService;
    private final CategoryNoSQLService categoryNoSQLService;
    private final ModelNoSQLService modelNoSQLService;
    private final ProductNoSQLService productNoSQLService;

    public NoSQLDatabaseSeedConfig(BrandNoSQLService brandNoSQLService, CategoryNoSQLService categoryNoSQLService, ModelNoSQLService modelNoSQLService, ProductNoSQLService productNoSQLService) {
        this.brandNoSQLService = brandNoSQLService;
        this.categoryNoSQLService = categoryNoSQLService;
        this.modelNoSQLService = modelNoSQLService;
        this.productNoSQLService = productNoSQLService;
    }

    private boolean databaseIsFilled(long c1, long c2, long c3, long c4) {
        return c1 == 0 || c2 == 0 || c3 == 0 || c4 == 0;
    }

    @PostConstruct
    public void init() throws IOException {
        long brandsCount = brandNoSQLService.getDbCount();
        long categoryCount = categoryNoSQLService.getDbCount();
        long modelCount = modelNoSQLService.getDbCount();
        long productCount = productNoSQLService.getDbCount();

        LOG.info("Verificando dados no banco NoSQL.");

        if (databaseIsFilled(brandsCount, categoryCount, modelCount, productCount)) {
            LOG.info("Iniciando o processo de população do banco de dados NoSQL.");

            if (brandsCount == 0) {
                LOG.info("Inserindo dados no documento 'marcas' ...");
                brandNoSQLService.fillDatabaseFromCSV();
            }

            if (categoryCount == 0) {
                LOG.info("Inserindo dados no documento 'categorias' ...");
                categoryNoSQLService.fillDatabaseFromCSV();
            }

            if (modelCount == 0) {
                LOG.info("Inserindo dados no documento 'modelos' ...");
                modelNoSQLService.fillDatabaseFromCSV();
            }

            if (productCount == 0) {
                LOG.info("Inserindo dados no documento 'produtos' ...");
                productNoSQLService.fillDatabaseFromCSV();
            }
            LOG.info("Finalizando o processo de população do banco de dados NoSQL");
        } else {
            LOG.info("O banco NoSQL já está populado.");
        }
    }
}
