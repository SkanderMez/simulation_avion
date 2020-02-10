package reseau;

import simulation.PanneauPrincipal;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public abstract class Usine {

    List<IconeUsine> iconesUsine = Arrays.asList(new IconeUsine[3]);


    public Usine() {
    }

    public Usine(List<IconeUsine> iconesUsine) {
        this.iconesUsine = iconesUsine;
    }

    public List<IconeUsine> getIconesUsine() {
        return iconesUsine;
    }

    public void setIconesUsine(List<IconeUsine> iconesUsine) {
        this.iconesUsine = iconesUsine;
    }

    @Override
    public String toString() {
        return "Usine{" +
                "iconesUsine=" + iconesUsine +
                '}';
    }
}

