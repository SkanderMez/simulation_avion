package reseau;

import java.awt.*;

public class ComposantEntree extends Composant {

    private int quantiteOrCapacity;


    public ComposantEntree(int quantiteOrCapacity) {
        this.quantiteOrCapacity = quantiteOrCapacity;
    }

    public ComposantEntree(Point vitesse, Point position, Icone icone, String type, int quantiteOrCapacity) {
        super(vitesse, position, icone, type);
        this.quantiteOrCapacity = quantiteOrCapacity;
    }

    public int getQuantiteOrCapacity() {
        return quantiteOrCapacity;
    }

    public void setQuantiteOrCapacity(int quantiteOrCapacity) {
        this.quantiteOrCapacity = quantiteOrCapacity;
    }

    @Override
    public String toString() {
        return "ComposantEntree{" +
                "quantiteOrCapacity=" + quantiteOrCapacity +
                ", vitesse=" + vitesse +
                ", position=" + position +
                ", icone=" + icone +
                ", type='" + type + '\'' +
                '}';
    }
}
