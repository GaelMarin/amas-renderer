package fr.irit.smac.amasrenderer.service;

import java.util.Map;

import fr.irit.smac.amasrenderer.model.ToolModel;
import javafx.collections.ObservableList;

public class ToolService {

    private ToolModel model;

    private static ToolService instance = new ToolService();

    private ToolService() {
        model = new ToolModel();
    }

    public static ToolService getInstance() {
        return instance;
    }

    public ObservableList<String> getTools() {
        return this.model.getTools();
    }

    public void setTools(ObservableList<String> items) {
        this.model.setTools(items);
    }

    public void createServicesFromMap(Map<String, Object> map) {

        for (Map.Entry<String, Object> pair : map.entrySet()) {
            if (pair.getKey() != "className") {
                this.model.addTool(pair.getKey());
            }
        }
    }

}
