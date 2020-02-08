package reseau;

import java.awt.*;

public class Composant extends Element {

    protected Point vitesse;
    protected Point position;
    protected Icone icone;
    protected String type;

    public Composant(){
    }

    public Composant(Point vitesse, Point position, Icone icone, String type) {
        this.vitesse = vitesse;
        this.position = position;
        this.icone = icone;
        this.type = type;
    }

    public Point getVitesse() {
        return vitesse;
    }

    public void setVitesse(Point vitesse) {
        this.vitesse = vitesse;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
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
                ", position=" + position +
                ", icone=" + icone +
                ", type='" + type + '\'' +
                '}';
    }
}
