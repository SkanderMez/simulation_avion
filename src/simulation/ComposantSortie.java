package simulation;

import reseau.TypeComposant;
import reseau.Usine;

import java.awt.*;

public class ComposantSortie extends Composant {

    public Point position;
    public Point vitesse;
    public Usine destination;

    public ComposantSortie(){}

    public ComposantSortie(TypeComposant typeComposant) {super(typeComposant); }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getVitesse() {
        return vitesse;
    }

    public void setVitesse(Point vitesse) {
        this.vitesse = vitesse;
    }

    public Usine getDestination() {
        return destination;
    }

    public void setDestination(Usine destination) {
        this.destination = destination;
    }
}
