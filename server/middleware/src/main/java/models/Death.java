package models;

public class Death {
    private String deathURI;
    private String label;
    private boolean approximate;
    private int date;
    private String deathUuid;

    public boolean isApproximate() {
        return approximate;
    }

    public void setApproximate(boolean approximate) {
        this.approximate = approximate;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getDeathURI() {
        return deathURI;
    }

    public void setDeathURI(String deathURI) {
        this.deathURI = deathURI;
    }

    public String getDeathUuid() {
        return deathUuid;
    }

    public void setDeathUuid(String deathUuid) {
        this.deathUuid = deathUuid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
