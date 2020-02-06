package reseau;

import java.awt.*;

public class Composant extends Element {

    protected Point vitesse;
    protected Icone icone;
    protected String type;

    public Composant(){

    }

    public Composant(Point vitesse, Icone icone, String type) {
        this.vitesse = vitesse;
        this.icone = icone;
        this.type = type;
    }

    public Composant(Point position, Point vitesse, Icone icone, String type) {
        super(position);
        this.vitesse = vitesse;
        this.icone = icone;
        this.type = type;
    }

    public Point getVitesse() {
        return vitesse;
    }

    public void setVitesse(Point vitesse) {
        this.vitesse = vitesse;
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
        return "Composant{" +
                "vitesse=" + vitesse +
                ", icone=" + icone +
                ", type='" + type + '\'' +
                ", position=" + position +
                '}';
    }
}
