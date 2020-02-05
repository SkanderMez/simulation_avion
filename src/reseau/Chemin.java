package reseau;

public class Chemin {

    private Usine depart;
    private Usine arrivee;

    public Chemin(Usine depart, Usine arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
    }

    public Usine getDepart() {
        return depart;
    }

    public void setDepart(Usine depart) {
        this.depart = depart;
    }

    public Usine getArrivee() {
        return arrivee;
    }

    public void setArrivee(Usine arrivee) {
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
