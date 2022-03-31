package net.unesc.ip.adsecommerce.config.initialization;

import net.unesc.ip.adsecommerce.services.BrandService;
import net.unesc.ip.adsecommerce.services.CategoryService;
import net.unesc.ip.adsecommerce.services.ModelService;
import net.unesc.ip.adsecommerce.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

@Configuration
public class DatabaseSeedingConfig {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseSeedingConfig.class);

    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ModelService modelService;
    private final ProductService productService;

    public DatabaseSeedingConfig(BrandService brandService, CategoryService categoryService, ModelService modelService, ProductService productService) {
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.modelService = modelService;
        this.productService = productService;
    }

    private boolean databaseIsFilled(long c1, long c2, long c3, long c4) {
        return c1 == 0 || c2 == 0 || c3 == 0 || c4 == 0;
    }

    private void insertProducts() throws IOException {
        // Faz uma nova contagem dos dados relacionados para garantir que já foram inseridos antes dos produtos.
        long brandsCount = brandService.getDbCount();
        long categoryCount = categoryService.getDbCount();
        long modelCount = modelService.getDbCount();

        if (brandsCount > 0 && categoryCount > 0 && modelCount > 0) {
            LOG.info("Inserindo dados na tabela 'produtos' ...");
            productService.fillDatabaseFromCSV();
        } else {
            LOG.warn("Não foi possível inserir os produtos.");

            Map<String, Long> conditions = Map.of("marcas", brandsCount, "categorias", categoryCount, "modelos", modelCount);

            for (Map.Entry<String, Long> entry : conditions.entrySet()) {
                if (entry.getValue() == 0) {
                    LOG.warn(String.format("A tabela %s ainda esta vazia, verifique.", entry.getKey()));
                }
            }
        }

    }

    @PostConstruct
    public void init() throws IOException {
        long brandsCount = brandService.getDbCount();
        long categoryCount = categoryService.getDbCount();
        long modelCount = modelService.getDbCount();
        long productCount = productService.getDbCount();

        if (databaseIsFilled(brandsCount, categoryCount, modelCount, productCount)) {
            LOG.info("Iniciando o processo de população do banco de dados.");

            if (brandsCount == 0) {
                LOG.info("Inserindo dados na tabela 'marcas' ...");
                brandService.fillDatabaseFromCSV();
            }

            if (categoryCount == 0) {
                LOG.info("Inserindo dados na tabela 'categorias' ...");
                categoryService.fillDatabaseFromCSV();
            }

            if (modelCount == 0) {
                LOG.info("Inserindo dados na tabela 'modelos' ...");
                modelService.fillDatabaseFromCSV();
            }

            if (productCount == 0) {
                insertProducts();
            }
            LOG.info("Finalizando o processo de população do banco de dados");
        } else {
            LOG.info("O banco de dados está populado.");
        }
    }
}
