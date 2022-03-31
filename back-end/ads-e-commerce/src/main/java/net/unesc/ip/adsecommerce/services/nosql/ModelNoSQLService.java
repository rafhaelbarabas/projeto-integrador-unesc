package net.unesc.ip.adsecommerce.services.nosql;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import net.unesc.ip.adsecommerce.entities.nosql.ModelNoSQL;
import net.unesc.ip.adsecommerce.repositories.nosql.ModelNoSQLRepository;
import net.unesc.ip.adsecommerce.utils.CSVHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ModelNoSQLService {

    private static final Logger LOG = LoggerFactory.getLogger(ModelNoSQLService.class);

    private final ModelNoSQLRepository modelNoSQLRepository;
    private final CSVHelper csvHelper;

    public ModelNoSQLService(ModelNoSQLRepository modelNoSQLRepository, CSVHelper csvHelper) {
        this.modelNoSQLRepository = modelNoSQLRepository;
        this.csvHelper = csvHelper;
    }

    public void fillDatabaseFromCSV() throws IOException {
        LOG.info("Buscando os dados do CSV de Modelos");
        try (CSVReader reader = csvHelper.getModelsCSV()) {
            List<String[]> modelsCSV = reader.readAll();
            int csvSize = modelsCSV.size();
            LOG.info("Quantidade de registros encontrados: " + csvSize);
            LOG.info("Inserindo registros no banco de dados: ");
            int counter = 0;
            for (String[] line : modelsCSV) {
                if (line.length >= 1 && !line[0].isBlank()) {
                    ++counter;
                    LOG.info("Inserindo: " + counter + "/" + csvSize);
                    String id = line[0];
                    String description = line[1];
                    persist(id, description);
                }
            }
            LOG.info("Fim da inserção de modelos");
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    public void persist(String id, String description) {
        modelNoSQLRepository.save(new ModelNoSQL(id, description));
    }

    public long getDbCount() {
        return modelNoSQLRepository.count();
    }

    public List<ModelNoSQL> findAll() {
        return modelNoSQLRepository.findAll();
    }


}