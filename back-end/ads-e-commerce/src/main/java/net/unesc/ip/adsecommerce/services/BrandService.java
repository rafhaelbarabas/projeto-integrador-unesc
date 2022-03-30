package net.unesc.ip.adsecommerce.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import net.unesc.ip.adsecommerce.entities.Brand;
import net.unesc.ip.adsecommerce.repositories.BrandRepository;
import net.unesc.ip.adsecommerce.utils.CSVHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class BrandService {

    private static final Logger LOG = LoggerFactory.getLogger(BrandService.class);

    private final BrandRepository brandRepository;
    private final CSVHelper csvHelper;

    public BrandService(BrandRepository brandRepository, CSVHelper csvHelper) {
        this.brandRepository = brandRepository;
        this.csvHelper = csvHelper;
    }

    public void fillDatabaseFromCSV() throws IOException {
        LOG.info("Buscando os dados do CSV de Marcas");

        try (CSVReader reader = csvHelper.getBrandsCSV()) {
            List<String[]> brandsCSV = reader.readAll();
            int csvSize = brandsCSV.size();
            LOG.info("Quantidade de registros encontrados: " + csvSize);
            LOG.info("Inserindo registros no banco de dados: ");
            int counter = 0;
            for (String[] line : brandsCSV) {
                if (line.length >= 1 && !line[0].isBlank()) {
                    ++counter;
                    LOG.info("Inserindo: " + counter + "/" + csvSize);
                    Long id = Long.valueOf(line[0]);
                    String description = line[1];
                    persist(id, description);
                }
            }
            LOG.info("Fim da inserção de marcas");

        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    public void persist(Long id, String description) {
        brandRepository.save(new Brand(id, description));
    }

    public long getDbCount() {
        return brandRepository.count();
    }

    public Brand findById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}