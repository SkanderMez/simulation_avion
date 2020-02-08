package reseau;

import java.awt.*;
//Classe d'association Composant-Usine
public class ComposantUsine  {

    private UsineAvecEntree usine;
    private TypeComposant composant;
    private int quantité;

    public ComposantUsine(UsineAvecEntree usine, TypeComposant composant, int quantité) {
        this.usine = usine;
        this.composant = composant;
        this.quantité = quantité;
    }

    public UsineAvecEntree getUsine() {
        return usine;
    }

    public void setUsine(UsineAvecEntree usine) {
        this.usine = usine;
    }

    public TypeComposant getComposant() {
        return composant;
    }

    public void setComposant(TypeComposant composant) {
        this.composant = composant;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }
}
