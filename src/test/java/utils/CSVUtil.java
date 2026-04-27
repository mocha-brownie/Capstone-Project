package utils;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.List;

public class CSVUtil {

    public static List<String[]> readCSV(String path) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(path));
        return reader.readAll();
    }
}