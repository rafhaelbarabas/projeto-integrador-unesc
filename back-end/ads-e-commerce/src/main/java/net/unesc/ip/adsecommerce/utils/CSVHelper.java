package net.unesc.ip.adsecommerce.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;

@Component
public class CSVHelper {

    private FileReader buildFileReader(String path) throws IOException {
        return new FileReader(path);
    }

    private CSVReader buildCSVReader(String path) throws IOException {
        return new CSVReaderBuilder(buildFileReader(path))
                .withSkipLines(1)
                .build();
    }

    public CSVReader getBrandsCSV() throws IOException, CsvException {
        return buildCSVReader("target/classes/csv/Marcas.csv");
    }

    public CSVReader getCategoriesCSV() throws IOException, CsvException {
        return buildCSVReader("target/classes/csv/Categorias.csv");
    }

    public CSVReader getModelsCSV() throws IOException, CsvException {
        return buildCSVReader("target/classes/csv/Modelos.csv");
    }

    public CSVReader getProductsCSV() throws IOException {
        return buildCSVReader("target/classes/csv/Produtos.csv");
    }
}
