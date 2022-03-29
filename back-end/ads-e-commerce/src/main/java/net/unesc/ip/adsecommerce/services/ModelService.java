package net.unesc.ip.adsecommerce.services;

import net.unesc.ip.adsecommerce.entities.Model;
import net.unesc.ip.adsecommerce.repositories.ModelRepository;
import net.unesc.ip.adsecommerce.utils.CSVHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class ModelService {

    private static final Logger LOG = LoggerFactory.getLogger(ModelService.class);

    private final ModelRepository modelRepository;
    private final CSVHelper csvHelper;

    public ModelService(ModelRepository modelRepository, CSVHelper csvHelper) {
        this.modelRepository = modelRepository;
        this.csvHelper = csvHelper;
    }

    public void fillDatabaseFromCSV() throws IOException {
        LOG.info("Buscando os dados do CSV de Modelos");
        List<String[]> modelsCSV = csvHelper.getModelsCSV();
        int csvSize = modelsCSV.size();
        LOG.info("Quantidade de registros encontrados: " + csvSize);
        LOG.info("Inserindo registros no banco de dados: ");
        int counter = 0;
        for (String[] line : modelsCSV) {
            ++counter;
            LOG.info("Inserindo: " + counter + "/" + csvSize);
            Long id = Long.valueOf(line[0]);
            String description = line[1];
            persist(id, description);
        }
        LOG.info("Fim da inserção de modelos");
    }

    public void persist(Long id, String description) {
        modelRepository.save(new Model(id, description));
    }

    public long getDbCount() {
        return modelRepository.count();
    }

    public Model findById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}