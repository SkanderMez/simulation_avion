package reseau;

import java.awt.*;
import java.util.List;

public class Entrepot extends Usine {

    private List<UsineProduction> observers;
    private Composant entree;


    public void notifierUsines(){
        //Notify Method
    }


    public Entrepot(Point position, int id, List<IconeUsine> iconesUsine, List<UsineProduction> observers, Composant entree) {
        super(position, id, iconesUsine);
        this.observers = observers;
        this.entree = entree;
    }

    public List<UsineProduction> getObservers() {
        return observers;
    }

    public void setObservers(List<UsineProduction> observers) {
        this.observers = observers;
    }

    public Composant getEntree() {
        return entree;
    }

    public void setEntree(Composant entree) {
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
