package reseau;

import java.awt.*;
import java.util.List;

public  class UsineProduction extends Usine {

    protected Composant composantSortie;
    protected int intervalProduction;
    protected String type;


    public  void arreterProduction(){
        // do something
    }
    public  void reprendreProduction(){
        // do something
    }


    public UsineProduction(List<IconeUsine> iconesUsine, Composant composantSortie, int intervalProduction, String type) {
        super(iconesUsine);
        this.composantSortie = composantSortie;
        this.intervalProduction = intervalProduction;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UsineProduction{" +
                "composantSortie=" + composantSortie +
                ", intervalProduction=" + intervalProduction +
                ", type='" + type + '\'' +
                ", iconesUsine=" + iconesUsine +
                '}';
    }
}
