package fr.irit.smac.amasrenderer.model;

import java.util.Map;

import javafx.collections.ObservableList;

/**
 * The Class InfrastructureModel.
 */
public class InfrastructureModel {

    private ObservableList<String> infrastructure;
	private String infrastructureClassname;

	/**
     * Sets the infrastructure.
     *
     * @param label
     *            the new infrastructure
     */
    public void setInfrastructure(ObservableList<String> label) {
        this.infrastructure = label;
    }

    /**
     * Gets the infrastructure.
     *
     * @return the infrastructure
     */
    public ObservableList<String> getInfrastructure() {
        return infrastructure;
    }

    /**
     * Edits the infrastructure.
     *
     * @param name
     *            the name
     */
    public void editInfrastructure(String name) {
//      infrastructure.clear();
        infrastructure.add(0, name);
    }

	public String getInfrastureClassname() {

		return this.infrastructureClassname;
	}
	
    public void setInfrastructureClassname(String infrastructureClassname) {
		this.infrastructureClassname = infrastructureClassname;
	}
}
