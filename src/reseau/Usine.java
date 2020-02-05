package reseau;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public abstract class Usine extends Element{

    protected int id;
    List<IconeUsine> iconesUsine = Arrays.asList(new IconeUsine[3]);


    public Usine(int id, List<IconeUsine> iconesUsine) {
        this.id = id;
        this.iconesUsine = iconesUsine;
    }

    public Usine(Point position, int id, List<IconeUsine> iconesUsine) {
        super(position);
        this.id = id;
        this.iconesUsine = iconesUsine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", iconesUsine=" + iconesUsine +
                '}';
    }
}

