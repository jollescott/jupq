package dev.joellinder.jupq.quiz;

import java.io.File;

public class JQManager {

    private String[] _datasetNames;
    private JQDataset _dataset;

    public boolean init() {
        // Load datasets
        var dataDir = new File("data");
        _datasetNames = dataDir.list();

        // Fail if no data is found
        if (_datasetNames == null || _datasetNames.length <= 0)
            return false;

        return true;
    }

    public boolean loadDataset(String name) {
        var dataset = new JQDataset(name);

        if (!dataset.loadData())
            return false;

        _dataset = dataset;

        return true;
    }

    public String[] getDatasetNames() {
        return _datasetNames;
    }

    public JQDataset getDataset() {
        return _dataset;
    }

    // Singleton pattern.
    private static JQManager _instance;

    public static JQManager getInstance() {
        if (_instance == null)
            _instance = new JQManager();

        return _instance;
    }
}
