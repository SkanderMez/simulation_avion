package reseau;

public class QuantiteDTO {

    private TypeComposant typeComposant;
    private int quantiteOuCapacite;

    public QuantiteDTO(TypeComposant typeComposant, int quantiteOuCapacite) {
        this.typeComposant = typeComposant;
        this.quantiteOuCapacite = quantiteOuCapacite;
    }


    public TypeComposant getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
    }

    public int getQuantiteOuCapacite() {
        return quantiteOuCapacite;
    }

    public void setQuantiteOuCapacite(int quantiteOuCapacite) {
        this.quantiteOuCapacite = quantiteOuCapacite;
    }
}
