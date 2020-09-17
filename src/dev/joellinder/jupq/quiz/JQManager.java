package dev.joellinder.jupq.quiz;

import java.io.File;
import java.util.ArrayList;

public class JQManager {

    private String[] datasetNames;
    private JQDataset dataset;
    private JQState state;
    private ArrayList<JQStateListener> stateListeners;

    public JQManager() {
        stateListeners = new ArrayList<JQStateListener>();
    }

    public boolean init() {
        // Load datasets
        var dataDir = new File("data");
        datasetNames = dataDir.list();

        // Fail if no data is found
        if (datasetNames == null || datasetNames.length <= 0)
            return false;

        return true;
    }

    public JQState getState() {
        return state;
    }

    public void setState(JQState _state) {
        this.state = _state;

        for (var listener : stateListeners) {
            listener.stateChanged(_state);
        }
    }

    public void addStateListener(JQStateListener listener) {
        this.stateListeners.add(listener);
    }

    public boolean loadDataset(String name) {
        var dataset = new JQDataset(name);

        if (!dataset.loadData())
            return false;

        this.dataset = dataset;

        return true;
    }

    public String[] getDatasetNames() {
        return datasetNames;
    }

    public JQDataset getDataset() {
        return dataset;
    }

    // Singleton pattern.
    private static JQManager _instance;

    public static JQManager getInstance() {
        if (_instance == null)
            _instance = new JQManager();

        return _instance;
    }
}
