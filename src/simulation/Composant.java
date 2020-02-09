package simulation;

import reseau.TypeComposant;

public class Composant {
    private TypeComposant typeComposant;

    public Composant(){}

    public Composant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
    }

    public TypeComposant getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
    }

    @Override
    public String toString() {
        return "Composant{" +
                "typeComposant=" + typeComposant +
                '}';
    }
}
