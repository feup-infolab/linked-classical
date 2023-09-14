package models;

import java.util.ArrayList;
import java.util.HashMap;

public class MusicalWork {
    private String URI;
    private String label;
    private ArrayList<String> alias;
    private String title;
    private String remarks;
    private String key;
    private String number;
    private String opus;
    private String catalogue;
    private HashMap<String, String> isPartOf = new HashMap<>();
    private Composition composition;
    private ArrayList<Document> documents;

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public ArrayList<String> getAlias() {
        return alias;
    }

    public void setAlias(ArrayList<String> alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
    }

    public HashMap<String, String> getIsPartOf() {
        return isPartOf;
    }

    public void setIsPartOf(HashMap<String, String> isPartOf) {
        this.isPartOf = isPartOf;
    }

    public String getOpus() {
        return opus;
    }

    public void setOpus(String opus) {
        this.opus = opus;
    }

    public Composition getComposition() {
        return composition;
    }

    public void setComposition(Composition composition) {
        this.composition = composition;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> document) {
        this.documents = document;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
