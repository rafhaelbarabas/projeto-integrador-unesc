package net.unesc.ip.adsecommerce.services.sql;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import net.unesc.ip.adsecommerce.api.dto.ProductDTO;
import net.unesc.ip.adsecommerce.entities.sql.Product;
import net.unesc.ip.adsecommerce.repositories.sql.ProductRepository;
import net.unesc.ip.adsecommerce.repositories.sql.dao.ProductDao;
import net.unesc.ip.adsecommerce.utils.CSVHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ModelService modelService;
    private final ProductRepository productRepository;
    private final CSVHelper csvHelper;
    private final ProductDao productDao;

    public ProductService(BrandService brandService, CategoryService categoryService, ModelService modelService, ProductRepository productRepository, CSVHelper csvHelper, ProductDao productDao) {
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.modelService = modelService;
        this.productRepository = productRepository;
        this.csvHelper = csvHelper;
        this.productDao = productDao;
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
                    Long categoryId = Long.valueOf(line[3]);
                    Long brandId = Long.valueOf(line[4]);
                    Long modelId = Long.valueOf(line[5]);
                    persist(id, description, price, categoryId, brandId, modelId);
                }
            }
            LOG.info("Fim da inserção de produtos");
        } catch (CsvException e) {
            e.printStackTrace();
        }
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

    public List<ProductDTO> findAll() {
        return productRepository
                .findAll()
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> find(List<String> brands, List<String> models, List<String> categories) {
        return productDao.findByFilters(brands, models, categories)
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> find(List<String> brands, List<String> models, List<String> categories, Pageable pageable) {
        return productDao.findByFilters(brands, models, categories, pageable)
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }
}