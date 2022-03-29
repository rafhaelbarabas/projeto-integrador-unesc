package net.unesc.ip.adsecommerce.services;

import net.unesc.ip.adsecommerce.entities.Product;
import net.unesc.ip.adsecommerce.repositories.ProductRepository;
import net.unesc.ip.adsecommerce.utils.CSVHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ModelService modelService;
    private final ProductRepository productRepository;
    private final CSVHelper csvHelper;

    public ProductService(BrandService brandService, CategoryService categoryService, ModelService modelService, ProductRepository productRepository, CSVHelper csvHelper) {
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.modelService = modelService;
        this.productRepository = productRepository;
        this.csvHelper = csvHelper;
    }

    public void fillDatabaseFromCSV() throws IOException {
        LOG.info("Buscando os dados do CSV de Produtos");
        List<String[]> productsCSV = csvHelper.getProductsCSV();
        int csvSize = productsCSV.size();
        LOG.info("Quantidade de registros encontrados: " + csvSize);
        LOG.info("Inserindo registros no banco de dados: ");
        int counter = 0;
        for (String[] column : productsCSV) {
            ++counter;
            LOG.info("Inserindo: " + counter + "/" + csvSize);
            Long id = Long.valueOf(column[0]);
            String description = column[1];
            BigDecimal price = new BigDecimal(column[2]);
            Long categoryId = Long.valueOf(column[3]);
            Long brandId = Long.valueOf(column[4]);
            Long modelId = Long.valueOf(column[5]);
            persist(id, description, price, categoryId, brandId, modelId);
        }
        LOG.info("Fim da inserção de modelos");
    }

    public void persist(Long id, String description, BigDecimal price, Long categoryId, Long brandId, Long modelId) {
        Product product = new Product();
        product.setId(id);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(categoryService.findById(categoryId));
        product.setBrand(brandService.findById(brandId));
        product.setModel(modelService.findById(modelId));
        productRepository.save(product);
    }

    public long getDbCount() {
        return productRepository.count();
    }
}