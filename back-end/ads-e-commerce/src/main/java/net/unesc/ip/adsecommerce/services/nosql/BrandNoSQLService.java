package net.unesc.ip.adsecommerce.services.nosql;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import net.unesc.ip.adsecommerce.entities.nosql.BrandNoSQL;
import net.unesc.ip.adsecommerce.repositories.nosql.BrandNoSQLRepository;
import net.unesc.ip.adsecommerce.utils.CSVHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BrandNoSQLService {

    private static final Logger LOG = LoggerFactory.getLogger(BrandNoSQLService.class);

    private final BrandNoSQLRepository brandNoSQLRepository;
    private final CSVHelper csvHelper;

    public BrandNoSQLService(BrandNoSQLRepository brandNoSQLRepository, CSVHelper csvHelper) {
        this.brandNoSQLRepository = brandNoSQLRepository;
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
                    String id = line[0];
                    String description = line[1];
                    persist(id, description);
                }
            }
            LOG.info("Fim da inserção de Marcas");
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    public void persist(String id, String description) {
        brandNoSQLRepository.save(new BrandNoSQL(id, description));
    }

    public long getDbCount() {
        return brandNoSQLRepository.count();
    }

    public List<BrandNoSQL> findAll() {
        return brandNoSQLRepository.findAll();
    }

    public BrandNoSQL findById(String id) {
        return brandNoSQLRepository.findById(id);
    }

}