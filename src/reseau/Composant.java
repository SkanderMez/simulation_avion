package reseau;

import java.awt.*;

public class Composant extends Element {

    protected Point vitesse;
    protected Icone icone;

    public Composant() {
    }

    public Composant(Point vitesse, Icone icone) {
        this.vitesse = vitesse;
        this.icone = icone;
    }

    public Composant(Point position, Point vitesse, Icone icone) {
        super(position);
        this.vitesse = vitesse;
        this.icone = icone;
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

    @Override
    public String toString() {
        return "Composant{" +
                "vitesse=" + vitesse +
                ", icone=" + icone +
                '}';
    }
}
