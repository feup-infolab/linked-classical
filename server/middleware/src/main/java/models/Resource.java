package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Resource {
    private String URI;
    private String label;
    private String type;
    private ArrayList<HashMap<String, String>> incomingStatements = new ArrayList<>();
    private ArrayList<HashMap<String, String>> outgoingStatements = new ArrayList<>();

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<HashMap<String, String>> getIncomingStatements() {
        return incomingStatements;
    }

    public void setIncomingStatements(ArrayList<HashMap<String, String>> incomingStatements) {
        this.incomingStatements = incomingStatements;
    }

    public ArrayList<HashMap<String, String>> getOutgoingStatements() {
        return outgoingStatements;
    }

    public void setOutgoingStatements(ArrayList<HashMap<String, String>> outgoingStatements) {
        this.outgoingStatements = outgoingStatements;
    }
}
