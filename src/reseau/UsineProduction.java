package reseau;

import java.awt.*;
import java.util.List;

public  class UsineProduction extends Usine {

    private Composant composantSortie;
    private int intervalProduction;


    public  void arreterProduction(){
        // do something
    }
    public  void reprendreProduction(){
        // do something
    }

    public UsineProduction(int id, List<IconeUsine> iconesUsine, Composant composantSortie, int intervalProduction) {
        super(id, iconesUsine);
        this.composantSortie = composantSortie;
        this.intervalProduction = intervalProduction;
    }

    public UsineProduction(Point position, int id, List<IconeUsine> iconesUsine, Composant composantSortie, int intervalProduction) {
        super(position, id, iconesUsine);
        this.composantSortie = composantSortie;
        this.intervalProduction = intervalProduction;
    }

    public Composant getComposantSortie() {
        return composantSortie;
    }

    public void setComposantSortie(Composant composantSortie) {
        this.composantSortie = composantSortie;
    }

    public int getIntervalProduction() {
        return intervalProduction;
    }

    public void setIntervalProduction(int intervalProduction) {
        this.intervalProduction = intervalProduction;
    }

    @Override
    public String toString() {
        return "UsineProduction{" +
                "composantSortie=" + composantSortie +
                ", intervalProduction=" + intervalProduction +
                ", id=" + id +
                ", iconesUsine=" + iconesUsine +
                ", position=" + position +
                '}';
    }
}
