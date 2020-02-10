package reseau;

import java.util.List;

public class Entrepot extends Usine {

    private List<UsineProduction> observers;
    private TypeComposant typeComposant;
    private int capacite;


    public void notifierUsines(){
        //Notify Method

        if ()
    }

    public Entrepot(List<IconeUsine> iconesUsine, List<UsineProduction> observers, TypeComposant typeComposant, int capacite) {
        super(iconesUsine);
        this.observers = observers;
        this.typeComposant = typeComposant;
        this.capacite = capacite;
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
