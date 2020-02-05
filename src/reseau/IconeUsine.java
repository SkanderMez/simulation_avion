package reseau;

public class IconeUsine extends Icone {


    private String type;

    public IconeUsine(String path) {
        super(path);
    }

    public IconeUsine(String path, String type) {
        super(path);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "IconeUsine{" +
                "type='" + type + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
