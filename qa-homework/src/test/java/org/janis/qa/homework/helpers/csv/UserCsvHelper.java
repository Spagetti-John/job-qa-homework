package org.janis.qa.homework.helpers.csv;

import org.janis.qa.homework.config.Config;
import org.janis.qa.homework.model.csv.UserCsv;

import java.util.List;

import static org.janis.qa.homework.helpers.csv.CsvHelper.getRandomRowsFromCsv;

public class UserCsvHelper {
    public static List<UserCsv> getRandomUsersFromCsv(int count) {
        return getRandomRowsFromCsv(Config.getInstance().getUsersCsvFile(), count, UserCsv.class);
    }
}
