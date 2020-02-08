package reseau;

import java.awt.*;
import java.util.List;

public class Entrepot extends Usine {

    private List<UsineProduction> observers;
    private List<ComposantEntree> entree;


    public void notifierUsines(){
        //Notify Method
    }


    public Entrepot(List<IconeUsine> iconesUsine, List<UsineProduction> observers, List<ComposantEntree> entree) {
        super(iconesUsine);
        this.observers = observers;
        this.entree = entree;
    }

    public List<UsineProduction> getObservers() {
        return observers;
    }

    public void setObservers(List<UsineProduction> observers) {
        this.observers = observers;
    }

    public List<ComposantEntree> getEntree() {
        return entree;
    }

    public void setEntree(List<ComposantEntree> entree) {
        this.entree = entree;
    }

    @Override
    public String toString() {
        return "Entrepot{" +
                "observers=" + observers +
                ", entree=" + entree +
                ", iconesUsine=" + iconesUsine +
                '}';
    }
}
