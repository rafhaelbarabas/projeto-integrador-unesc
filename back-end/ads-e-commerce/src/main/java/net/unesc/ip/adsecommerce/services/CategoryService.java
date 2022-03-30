package net.unesc.ip.adsecommerce.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import net.unesc.ip.adsecommerce.entities.Category;
import net.unesc.ip.adsecommerce.repositories.CategoryRepository;
import net.unesc.ip.adsecommerce.utils.CSVHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;
    private final CSVHelper csvHelper;

    public CategoryService(CategoryRepository categoryRepository, CSVHelper csvHelper) {
        this.categoryRepository = categoryRepository;
        this.csvHelper = csvHelper;
    }

    public void fillDatabaseFromCSV() throws IOException {
        LOG.info("Buscando os dados do CSV de Categorias");

        try (CSVReader reader = csvHelper.getCategoriesCSV()) {
            List<String[]> categoriesCSV = reader.readAll();
            int csvSize = categoriesCSV.size();
            LOG.info("Quantidade de registros encontrados: " + csvSize);
            LOG.info("Inserindo registros no banco de dados: ");
            int counter = 0;
            for (String[] line : categoriesCSV) {
                if (line.length >= 1 && !line[0].isBlank()) {
                    ++counter;
                    LOG.info("Inserindo: " + counter + "/" + csvSize);
                    Long id = Long.valueOf(line[0]);
                    String description = line[1];
                    persist(id, description);
                }
            }
            LOG.info("Fim da inserção de categorias");
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    public void persist(Long id, String description) {
        categoryRepository.save(new Category(id, description));
    }

    public long getDbCount() {
        return categoryRepository.count();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}