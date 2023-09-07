package org.janis.qa.homework.helpers.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.janis.qa.homework.exceptions.TooFewEntriesInListException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class CsvHelper {
    protected static <T> List<T> getRandomRowsFromCsv(String fileName, int count, Class<T> clazz) {
        var shuffle = getCsvAsList(fileName, clazz);

        if (shuffle.size() < count) {
            throw new TooFewEntriesInListException();
        }

        Collections.shuffle(shuffle);

        return shuffle.subList(0, count);
    }

    //This can probably be improved by caching read files
    protected static <T> List<T> getCsvAsList(String fileName, Class<T> clazz)  {
        try {
            var path = Paths.get(ClassLoader.getSystemResource(fileName).toURI());

            return new CsvToBeanBuilder<T>(Files.newBufferedReader(path)).withType(clazz).build().parse();
        } catch (URISyntaxException | NullPointerException | IOException e) {
            throw new RuntimeException("Could not load file: " + fileName, e);
        }

    }
}
