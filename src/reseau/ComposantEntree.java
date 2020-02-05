package reseau;

import java.awt.*;

public class ComposantEntree extends Composant {

    private int quantiteOrCapacity;


    public ComposantEntree(int quantiteOrCapacity) {
        this.quantiteOrCapacity = quantiteOrCapacity;
    }

    public ComposantEntree(Point vitesse, Icone icone, int quantiteOrCapacity) {
        super(vitesse, icone);
        this.quantiteOrCapacity = quantiteOrCapacity;
    }

    public ComposantEntree(Point position, Point vitesse, Icone icone, int quantiteOrCapacity) {
        super(position, vitesse, icone);
        this.quantiteOrCapacity = quantiteOrCapacity;
    }

    public ComposantEntree() {

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
                '}';
    }
}
