package reseau;

import simulation.UsineSimulation;

import java.util.ArrayList;
import java.util.List;

public class Entrepot extends Usine {

    private List<UsineProduction> observers ;
    private TypeComposant typeComposant;
    private int capacite;


    public Entrepot() {
        super();
    }

    public Entrepot(List<IconeUsine> iconesUsine, List<UsineProduction> observers, TypeComposant typeComposant, int capacite) {
        super(iconesUsine);
        this.observers = observers;
        this.typeComposant = typeComposant;
        this.capacite = capacite;
    }

    public void notifierUsines() {

        for (int i=0;i<observers.size();i++){
            if (observers.get(i) instanceof UsineProduction){
                observers.get(i).update();
            }
        }

    }

    public void attach(UsineProduction observer){
        if (observers == null){
            observers = new ArrayList<>();
        }
        this.observers.add(observer);
    }


    public List<UsineProduction> getObservers() {
        return observers;
    }

    public void setObservers(List<UsineProduction> observers) {
        this.observers = observers;
    }

    public TypeComposant getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "Entrepot{" +
                "observers=" + observers +
                ", typeComposant=" + typeComposant +
                ", capacite=" + capacite +
                ", iconesUsine=" + iconesUsine +
                '}';
    }
}
