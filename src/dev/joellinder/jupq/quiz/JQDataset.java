package dev.joellinder.jupq.quiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class JQDataset {
    private String name;
    private ArrayList<String> records;

    public JQDataset(String name) {
        this.name = name;
    }

    public ArrayList<String> getRecords() {
        return records;
    }

    public boolean isLoaded() {
        return records != null;
    }

    public boolean loadData() {
        try {
            var fileReader = new FileReader(String.format("data/%s", name));
            var dataReader = new BufferedReader(fileReader);

            String line = null;
            while (((line = dataReader.readLine()) != null)) {

            }

            dataReader.close();
            fileReader.close();

            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
