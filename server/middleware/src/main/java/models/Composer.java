package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Composer {
    private String URI;
    private String label;
    private String name;

    private String period;
    private ArrayList<String> nationalities;
    private ArrayList<String> alias;
    private Birth birth;
    private Death death;
    private ArrayList<Location> locations;
    private ArrayList<String> sameAs;
    private ArrayList<String> pages;
    private String wikipediaPage;
    private ArrayList<HashMap<String, String>> hasInfluenced;
    private ArrayList<HashMap<String, String>> influencedBy;
    private ArrayList<StylePeriod> stylePeriods;

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getNationalities() {
        return nationalities;
    }

    public void setNationalities(ArrayList<String> nationalities) {
        this.nationalities = nationalities;
    }

    public ArrayList<String> getAlias() {
        return alias;
    }

    public void setAlias(ArrayList<String> alias) {
        this.alias = alias;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Birth getBirth() {
        return birth;
    }

    public void setBirth(Birth birth) {
        this.birth = birth;
    }

    public Death getDeath() {
        return death;
    }

    public void setDeath(Death death) {
        this.death = death;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public ArrayList<String> getSameAs() {
        return sameAs;
    }

    public void setSameAs(ArrayList<String> sameAs) {
        this.sameAs = sameAs;
    }

    public ArrayList<String> getPages() {
        return pages;
    }

    public void setPages(ArrayList<String> pages) {
        this.pages = pages;
    }

    public String getWikipediaPage() {
        return wikipediaPage;
    }

    public void setWikipediaPage(String wikipediaPage) {
        this.wikipediaPage = wikipediaPage;
    }

    public ArrayList<HashMap<String, String>> getHasInfluenced() {
        return hasInfluenced;
    }

    public void setHasInfluenced(ArrayList<HashMap<String, String>> hasInfluenced) {
        this.hasInfluenced = hasInfluenced;
    }

    public ArrayList<HashMap<String, String>> getInfluencedBy() {
        return influencedBy;
    }

    public void setInfluencedBy(ArrayList<HashMap<String, String>> influencedBy) {
        this.influencedBy = influencedBy;
    }

    public ArrayList<StylePeriod> getStylePeriods() {
        return stylePeriods;
    }

    public void setStylePeriods(ArrayList<StylePeriod> stylePeriods) {
        this.stylePeriods = stylePeriods;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
