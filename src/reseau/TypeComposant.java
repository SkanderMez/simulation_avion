package reseau;


import java.util.List;

public class TypeComposant{

    protected Icone icone;
    protected String type;

    public TypeComposant(){}

    public TypeComposant(Icone icone, String type) {
        this.icone = icone;
        this.type = type;
    }

    public Icone getIcone() {
        return icone;
    }

    public void setIcone(Icone icone) {
        this.icone = icone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "TypeComposant{" +
                "icone=" + icone +
                ", type='" + type + '\'' +
                '}';
    }
}
