package reseau;


import java.awt.*;
import java.util.List;

public class UsineAvecEntree extends UsineProduction {

    protected List<ComposantEntree> composantsEntree;

    public UsineAvecEntree(int id, List<IconeUsine> iconesUsine, Composant composantSortie, int intervalProduction, String type, List<ComposantEntree> composantsEntree) {
        super(id, iconesUsine, composantSortie, intervalProduction, type);
        this.composantsEntree = composantsEntree;
    }

    public UsineAvecEntree(Point position, int id, List<IconeUsine> iconesUsine, Composant composantSortie, int intervalProduction, String type, List<ComposantEntree> composantsEntree) {
        super(position, id, iconesUsine, composantSortie, intervalProduction, type);
        this.composantsEntree = composantsEntree;
    }

    public List<ComposantEntree> getComposantsEntree() {
        return composantsEntree;
    }

    public void setComposantsEntree(List<ComposantEntree> composantsEntree) {
        this.composantsEntree = composantsEntree;
    }

    @Override
    public String toString() {
        return "UsineAvecEntree{" +
                "Type= "+type+
                " composantsEntree=" + composantsEntree +
                ", id=" + id +
                ", iconesUsine=" + iconesUsine +
                ", position=" + position +
                '}';
    }
}
