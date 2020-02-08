package reseau;


import java.util.List;

public class UsineAvecEntree extends UsineProduction {

    protected List<List<ComposantEntree>> composantsEntreeList;


    public boolean veriferValiditeStock(){

        boolean isValid = true;

        for(List<ComposantEntree> composantEntree: composantsEntreeList){
            for (ComposantEntree composant : composantEntree){
                if(composant.getQuantiteOrCapacity() < composantEntree.size()){
                    isValid = false;
                    return isValid;
                }
            }
        }

        return isValid;
    }

    public UsineAvecEntree(List<IconeUsine> iconesUsine, Composant composantSortie, int intervalProduction, String type, List<List<ComposantEntree>> composantsEntreeList) {
        super(iconesUsine, composantSortie, intervalProduction, type);
        this.composantsEntreeList = composantsEntreeList;
    }

    public List<List<ComposantEntree>> getComposantsEntreeList() {
        return composantsEntreeList;
    }

    public void setComposantsEntreeList(List<List<ComposantEntree>> composantsEntreeList) {
        this.composantsEntreeList = composantsEntreeList;
    }

    @Override
    public String toString() {
        return "UsineAvecEntree{" +
                "composantsEntreeList=" + composantsEntreeList +
                ", composantSortie=" + composantSortie +
                ", intervalProduction=" + intervalProduction +
                ", type='" + type + '\'' +
                ", iconesUsine=" + iconesUsine +
                '}';
    }
}
