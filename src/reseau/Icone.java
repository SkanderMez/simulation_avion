package reseau;

public class Icone {

    protected String path;

    public Icone(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Icone{" +
                "path='" + path + '\'' +
                '}';
    }
}
