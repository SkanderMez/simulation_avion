package reseau;

import config.SimulationConfig;
import simulation.Environnement;
import simulation.Simulation;
import simulation.UsineSimulation;

import java.util.List;

public  class UsineProduction extends Usine {

    protected TypeComposant typeComposantSortie;
    protected int intervalProduction;
    protected String type;


    public  void update(){

        //on bloque l'app lorsque l'entrepot est plein
        Environnement.getInstance().paused=true;
    }


    public UsineProduction(List<IconeUsine> iconesUsine, TypeComposant typeComposantSortie, int intervalProduction, String type) {
        super(iconesUsine);
        this.typeComposantSortie = typeComposantSortie;
        this.intervalProduction = intervalProduction;
        this.type = type;


    }

    public TypeComposant getTypeComposantSortie() {
        return typeComposantSortie;
    }

    public void setTypeComposantSortie(TypeComposant typeComposantSortie) {
        this.typeComposantSortie = typeComposantSortie;
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
                "typeComposantSortie=" + typeComposantSortie +
                ", intervalProduction=" + intervalProduction +
                ", type='" + type + '\'' +
                ", iconesUsine=" + iconesUsine +
                '}';
    }
}
