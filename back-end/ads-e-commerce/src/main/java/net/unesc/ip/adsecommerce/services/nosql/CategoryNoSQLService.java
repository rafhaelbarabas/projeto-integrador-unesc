package net.unesc.ip.adsecommerce.services.nosql;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import net.unesc.ip.adsecommerce.entities.nosql.CategoryNoSQL;
import net.unesc.ip.adsecommerce.repositories.nosql.CategoryNoSQLRepository;
import net.unesc.ip.adsecommerce.utils.CSVHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CategoryNoSQLService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryNoSQLService.class);

    private final CategoryNoSQLRepository categoryNoSQLRepository;
    private final CSVHelper csvHelper;

    public CategoryNoSQLService(CategoryNoSQLRepository categoryNoSQLRepository, CSVHelper csvHelper) {
        this.categoryNoSQLRepository = categoryNoSQLRepository;
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
                    String id = line[0];
                    String description = line[1];
                    persist(id, description);
                }
            }
            LOG.info("Fim da inserção de categorias");
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    public void persist(String id, String description) {
        categoryNoSQLRepository.save(new CategoryNoSQL(id, description));
    }

    public long getDbCount() {
        return categoryNoSQLRepository.count();
    }

    public List<CategoryNoSQL> findAll() {
        return categoryNoSQLRepository.findAll();
    }

    public CategoryNoSQL findById(String id) {
        return categoryNoSQLRepository.findById(id);
    }
}