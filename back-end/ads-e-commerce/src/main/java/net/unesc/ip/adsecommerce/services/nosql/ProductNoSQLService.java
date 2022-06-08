package net.unesc.ip.adsecommerce.services.nosql;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import net.unesc.ip.adsecommerce.api.dto.ProductDTO;
import net.unesc.ip.adsecommerce.entities.nosql.BrandNoSQL;
import net.unesc.ip.adsecommerce.entities.nosql.CategoryNoSQL;
import net.unesc.ip.adsecommerce.entities.nosql.ModelNoSQL;
import net.unesc.ip.adsecommerce.entities.nosql.ProductNoSQL;
import net.unesc.ip.adsecommerce.repositories.nosql.ProductNoSQLRepository;
import net.unesc.ip.adsecommerce.utils.CSVHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductNoSQLService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductNoSQLService.class);

    private final CSVHelper csvHelper;
    private final ProductNoSQLRepository productNoSQLRepository;
    private final CategoryNoSQLService categoryNoSQLService;
    private final ModelNoSQLService modelNoSQLService;
    private final BrandNoSQLService brandNoSQLService;

    public ProductNoSQLService(CSVHelper csvHelper, ProductNoSQLRepository productNoSQLRepository, CategoryNoSQLService categoryNoSQLService, ModelNoSQLService modelNoSQLService, BrandNoSQLService brandNoSQLService) {
        this.csvHelper = csvHelper;
        this.productNoSQLRepository = productNoSQLRepository;
        this.categoryNoSQLService = categoryNoSQLService;
        this.modelNoSQLService = modelNoSQLService;
        this.brandNoSQLService = brandNoSQLService;
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
            LOG.info("Fim da inserção de produtos");
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

    public Page<ProductNoSQL> findAll(Pageable pageable) {
        return productNoSQLRepository.findAll(pageable);
    }

    public List<ProductNoSQL> findAll() {
        return productNoSQLRepository.findAll();
    }

    public ProductDTO toProductDTO(ProductNoSQL productNoSQL) {
        // TODO: Arrumar consulta
        CategoryNoSQL categoryNoSQL = null; // categoryNoSQLService.findById(productNoSQL.getCategoryId());
        ModelNoSQL modelNoSQL = null;       // modelNoSQLService.findById(productNoSQL.getModelId());
        BrandNoSQL brandNoSQL = null;       // brandNoSQLService.findById(productNoSQL.getBrandId());
        return new ProductDTO(productNoSQL, categoryNoSQL, brandNoSQL, modelNoSQL);
    }

    public ProductNoSQL findById(Long id) {
        return productNoSQLRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    // TODO: Criar métodos para busca com parâmetros igual no relacional
}