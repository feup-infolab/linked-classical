package models;

public class Birth {
    private String birthURI;
    private String label;
    private boolean approximate;
    private int date;
    private String birthUuid;

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

    public String getBirthURI() {
        return birthURI;
    }

    public void setBirthURI(String birthURI) {
        this.birthURI = birthURI;
    }

    public String getBirthUuid() {
        return birthUuid;
    }

    public void setBirthUuid(String birthUuid) {
        this.birthUuid = birthUuid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
