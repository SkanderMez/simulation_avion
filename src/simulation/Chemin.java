package simulation;

import reseau.Usine;

public class Chemin {

    private UsineSimulation depart;
    private UsineSimulation arrivee;

    public Chemin(UsineSimulation depart, UsineSimulation arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
    }

    public UsineSimulation getDepart() {
        return depart;
    }

    public void setDepart(UsineSimulation depart) {
        this.depart = depart;
    }

    public UsineSimulation getArrivee() {
        return arrivee;
    }

    public void setArrivee(UsineSimulation arrivee) {
        this.arrivee = arrivee;
    }

    @Override
    public String toString() {
        return "Chemin{" +
                "depart=" + depart +
                ", arrivee=" + arrivee +
                '}';
    }
}
