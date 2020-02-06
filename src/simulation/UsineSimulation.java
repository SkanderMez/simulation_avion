package simulation;

import reseau.Icone;
import reseau.Usine;
import reseau.UsineProduction;

import java.awt.*;

public class UsineSimulation {

    private int id;

    private Usine usine;

    private Point position;

    private Icone iconeCourrante;

    public UsineSimulation(int id, Usine usine, Point position, Icone iconeCourrante) {
        this.id = id;
        this.usine = usine;
        this.position = position;
        this.iconeCourrante = iconeCourrante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usine getUsine() {
        return usine;
    }

    public void setUsine(Usine usine) {
        this.usine = usine;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Icone getIconeCourrante() {
        return iconeCourrante;
    }

    public void setIconeCourrante(Icone iconeCourrante) {
        this.iconeCourrante = iconeCourrante;
    }

    @Override
    public String toString() {
        return "UsineSimulation{" +
                "id=" + id +
                ", usine=" + usine +
                ", position=" + position +
                ", iconeCourrante=" + iconeCourrante +
                '}';
    }
}
