package core.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import core.models.LoginModel;

public class CsvHelper {
    public static <T> List<T> csvReader(String fileName, Class<T> type) {
        List<T> tList = new ArrayList<>();

        try (Reader reader = new FileReader(Constant.TEST_DATA_DIR + fileName)) {
            tList = new CsvToBeanBuilder<T>(reader).withType(type)
                    .build().parse();
            reader.close();
            return tList;
        } catch (IOException e) {
            System.out.println("Can't load file csv: " + fileName);
            System.out.println(e.getMessage());
        }
        return tList;
    }

    public static <T> void csvWriter(String fileName, List<T> listData, Class<T> type) {
        try (Writer writer = new FileWriter(Constant.TEST_DATA_DIR + fileName)) {
            new StatefulBeanToCsvBuilder<T>(writer)
                    .withApplyQuotesToAll(false)
                    .build()
                    .write(listData);
            writer.close();
        } catch (CsvRequiredFieldEmptyException | IOException | CsvDataTypeMismatchException e) {
            System.out.println("Can't write to csv file, error: " + e.getMessage());
        }
    }
}


