package net.unesc.ip.adsecommerce.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CSVHelper {

    private Reader buildReader(String path) throws IOException {
        return Files.newBufferedReader(Paths.get(path));
    }

    private CSVReader buildCSVReader(String path) throws IOException {
        return new CSVReaderBuilder(buildReader(path))
                .withSkipLines(1)
                .build();
    }

    private List<String[]> getCSVData(String path) throws IOException {
        return buildCSVReader(path)
                .readAll();
    }

    public List<String[]> getBrandsCSV() throws IOException {
        return getCSVData("csvFiles/Marcas.csv");
    }

    public List<String[]> getCategoriesCSV() throws IOException {
        return getCSVData("csvFiles/Categorias.csv");
    }

    public List<String[]> getModelsCSV() throws IOException {
        return getCSVData("csvFiles/Modelos.csv");
    }

    public List<String[]> getProductsCSV() throws IOException {
        return getCSVData("csvFiles/Produtos.csv");
    }
}
