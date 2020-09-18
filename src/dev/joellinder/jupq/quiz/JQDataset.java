package dev.joellinder.jupq.quiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JQDataset {
    private String name;
    private ArrayList<JQRecord> records;

    public JQDataset(String name) {
        this.name = name;
    }

    public ArrayList<JQRecord> getRecords() {
        return records;
    }

    public boolean isLoaded() {
        return records != null;
    }

    public boolean loadData() {
        records = new ArrayList<JQRecord>();

        try {
            var fileReader = new FileReader(String.format("data/%s/items.txt", name));
            var dataReader = new BufferedReader(fileReader);

            String line = null;
            var itemCount = dataReader.lines().count();

            while (((line = dataReader.readLine()) != null)) {
                var parts = line.split("-");

                if (parts.length != 2) {
                    System.out.println("Format Error: missing \"-\"");
                    break;
                }

                var itemPart = parts[0];

                if (itemPart.indexOf(",", 0) == -1) {
                    break;
                }

                var names = itemPart.split(",");
                var imageName = parts[1];

                records.add(new JQRecord(names, imageName));
            }

            dataReader.close();
            fileReader.close();

            if (records.size() != itemCount) {
                System.out.println("Dataset Error: Failed to load all records");
                return false;
            }

            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
