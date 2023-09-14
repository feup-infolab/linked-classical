package models;

public class Composition {
    private int date;
    private String composerURI;
    private String composerUuid;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getComposerURI() {
        return composerURI;
    }

    public void setComposerURI(String composerURI) {
        this.composerURI = composerURI;
    }

    public String getComposerUuid() {
        return composerUuid;
    }

    public void setComposerUuid(String composerUuid) {
        this.composerUuid = composerUuid;
    }
}
