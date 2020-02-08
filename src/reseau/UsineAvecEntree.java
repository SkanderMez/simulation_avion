package reseau;


import simulation.Composant;

import java.util.ArrayList;
import java.util.List;

public class UsineAvecEntree extends UsineProduction {

    protected List<TypeComposant> typeComposantList = new ArrayList<>();

    protected List<ComposantUsine> composantUsineList = new ArrayList<>();

    public int getQuantiteByTypeComposant(TypeComposant typeComposant){
        for(ComposantUsine composantUsine : composantUsineList){
            if(composantUsine.getComposant().getType()==typeComposant.getType()) return composantUsine.getQuantité();
        }
         return 0;
    }

    public UsineAvecEntree(List<IconeUsine> iconesUsine, TypeComposant typeComposantSortie, int intervalProduction, String type, List<TypeComposant> typeComposantList) {
        super(iconesUsine, typeComposantSortie, intervalProduction, type);
        this.typeComposantList = typeComposantList;
        this.composantUsineList = new ArrayList<ComposantUsine>();
    }

    public List<TypeComposant> getTypeComposantList() {
        return typeComposantList;
    }

    public void setTypeComposantList(List<TypeComposant> typeComposantList) {
        this.typeComposantList = typeComposantList;
    }

    public List<ComposantUsine> getComposantUsineList() {
        return composantUsineList;
    }

    public void setComposantUsineList(List<ComposantUsine> composantUsineList) {
        this.composantUsineList = composantUsineList;
    }

    public void addComposantUsine(ComposantUsine composantUsine){this.composantUsineList.add(composantUsine);}

    @Override
    public String toString() {
        return "UsineAvecEntree{" +
                "typeComposantList=" + typeComposantList +
                ", composantUsineList=" + composantUsineList +
                ", typeComposantSortie=" + typeComposantSortie +
                ", intervalProduction=" + intervalProduction +
                ", type='" + type + '\'' +
                ", iconesUsine=" + iconesUsine +
                '}';
    }
}
