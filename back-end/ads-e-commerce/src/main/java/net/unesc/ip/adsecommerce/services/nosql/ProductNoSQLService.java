package net.unesc.ip.adsecommerce.services.nosql;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import net.unesc.ip.adsecommerce.entities.nosql.ProductNoSQL;
import net.unesc.ip.adsecommerce.repositories.nosql.ProductNoSQLRepository;
import net.unesc.ip.adsecommerce.utils.CSVHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductNoSQLService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductNoSQLService.class);

    private final ProductNoSQLRepository productNoSQLRepository;
    private final CSVHelper csvHelper;

    public ProductNoSQLService(ProductNoSQLRepository productNoSQLRepository, CSVHelper csvHelper) {
        this.productNoSQLRepository = productNoSQLRepository;
        this.csvHelper = csvHelper;
    }

    public void fillDatabaseFromCSV() throws IOException {
        LOG.info("Buscando os dados do CSV de Produtos");
        try (CSVReader reader = csvHelper.getProductsCSV()) {
            List<String[]> productsCSV = reader.readAll();
            int csvSize = productsCSV.size();
            LOG.info("Quantidade de registros encontrados: " + csvSize);
            LOG.info("Inserindo registros no banco de dados: ");
            int counter = 0;
            for (String[] line : productsCSV) {
                if (line.length >= 1 && !line[0].isBlank()) {
                    ++counter;
                    LOG.info("Inserindo: " + counter + "/" + csvSize);
                    Long id = Long.valueOf(line[0]);
                    String description = line[1];
                    BigDecimal price = new BigDecimal(line[2]);
                    String categoryId = line[3];
                    String brandId = line[4];
                    String modelId = line[5];
                    persist(id, description, price, categoryId, brandId, modelId);
                }
            }
            LOG.info("Fim da inserção de modelos");
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    public void persist(Long id, String description, BigDecimal price, String categoryId, String brandId, String modelId) {
        ProductNoSQL product = new ProductNoSQL();
        product.setId(String.valueOf(id));
        product.setDescription(description);
        product.setPrice(price);
        product.setCategoryId(categoryId);
        product.setBrandId(brandId);
        product.setModelId(modelId);
        productNoSQLRepository.save(product);
    }

    public long getDbCount() {
        return productNoSQLRepository.count();
    }

    public List<ProductNoSQL> findAll() {
        return productNoSQLRepository.findAll();
    }
}