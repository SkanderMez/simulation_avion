package reseau;

import java.awt.*;
//Classe d'association Composant-Usine
public class ComposantUsine  {

    private UsineAvecEntree usine;
    private TypeComposant composant;
    private int quantit�;

    public ComposantUsine(UsineAvecEntree usine, TypeComposant composant, int quantit�) {
        this.usine = usine;
        this.composant = composant;
        this.quantit� = quantit�;
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

    public int getQuantit�() {
        return quantit�;
    }

    public void setQuantit�(int quantit�) {
        this.quantit� = quantit�;
    }
}
