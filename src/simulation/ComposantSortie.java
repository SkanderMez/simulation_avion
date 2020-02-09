package simulation;

import reseau.TypeComposant;
import reseau.Usine;

import java.awt.*;

public class ComposantSortie extends Composant {

    public Point position;
    public Point vitesse;
    public UsineSimulation destination;
    public UsineSimulation depart;

    public ComposantSortie(){}

    public ComposantSortie(Point position, Point vitesse, UsineSimulation destination) {
        this.position = position;
        this.vitesse = vitesse;
        this.destination = destination;
    }

    public ComposantSortie(TypeComposant typeComposant, Point position, Point vitesse, UsineSimulation destination) {
        super(typeComposant);
        this.position = position;
        this.vitesse = vitesse;
        this.destination = destination;
    }

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

    public UsineSimulation getDestination() {
        return destination;
    }

    public void setDestination(UsineSimulation destination) {
        this.destination = destination;
    }

    public UsineSimulation getDepart() {
        return depart;
    }

    public void setDepart(UsineSimulation depart) {
        this.depart = depart;
    }
}
