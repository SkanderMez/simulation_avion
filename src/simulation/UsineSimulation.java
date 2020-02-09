package simulation;

import reseau.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UsineSimulation {

    private int id;

    private Usine usine;

    private Point position;

    private Icone iconeCourrante;

    private int tempsRestantPourProduction = 0;

    private List<Composant> stock = new ArrayList();

    public void addToStock(Composant composant){this.stock.add(composant);}

    public int getStockByTypeComposant(TypeComposant typeComposant){
        int nb =0;
        for(Composant composant: stock){
            if(composant.getTypeComposant().getType()==typeComposant.getType())
                nb++;
        }
        return nb;
    }

    public boolean veriferValiditeStock(){
       boolean isValid = true;
       if(usine instanceof UsineAvecEntree) {
           UsineAvecEntree usineAvecEntree = (UsineAvecEntree)usine;
           List<TypeComposant> typeComposants = usineAvecEntree.getTypeComposantList();
           for(TypeComposant typeComposant:typeComposants) {
               int quantité = usineAvecEntree.getQuantiteByTypeComposant(typeComposant);
               int nb = getStockByTypeComposant(typeComposant);
//               System.out.println("*************************************************");
//               System.out.println("Quantity  :  " + quantité);
//               System.out.println("Stock  :  " + nb);
//               System.out.println("*************************************************");
               if (nb < quantité) {
                   isValid = false;
                   return isValid;
               }

           }
       }
        return isValid;
    }


  /*  public UsineSimulation(int id, Usine usine, Point position, Icone iconeCourrante) {
        this.id = id;
        this.usine = usine;
        this.position = position;
        this.iconeCourrante = iconeCourrante;
        if (usine instanceof UsineProduction) {
            UsineProduction usineProduction = (UsineProduction) usine;
            this.tempsRestantPourProduction = usineProduction.getIntervalProduction();
        }
    }*/

    public void modifierIconeCourante() {
        if (this.usine instanceof UsineAvecEntree) {
            //System.out.println("hahaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            UsineAvecEntree UsineAvecEntree = (UsineAvecEntree) this.usine;
            int intervalProduction = UsineAvecEntree.getIntervalProduction();
            double diff = intervalProduction - tempsRestantPourProduction;
            double div = diff / intervalProduction;
            double pourcentage = div * 100;
            System.out.println(pourcentage);
            verifierPourcentage(pourcentage);
        }
        else if (this.usine instanceof UsineProduction) {
            UsineProduction usineProduction = (UsineProduction) this.usine;
            int intervalProduction = usineProduction.getIntervalProduction();
            double diff = intervalProduction - tempsRestantPourProduction;
            double div = diff / intervalProduction;
            double pourcentage = div * 100;
            verifierPourcentage(pourcentage);
        } else if (this.usine instanceof Entrepot) {
            Entrepot entrepot = (Entrepot) this.usine;
            int capacite = entrepot.getCapacite();
            int stock = this.stock.size();
            double pourcentage = (stock / capacite) * 100;
            verifierPourcentage(pourcentage);
        }

    }

    private void verifierPourcentage(double pourcentage) {
        if (pourcentage >= Double.parseDouble("0") && pourcentage < Double.parseDouble("33")) {
            this.iconeCourrante = this.usine.getIconesUsine().get(0);
        }
        if (pourcentage >= Double.parseDouble("33") && pourcentage < Double.parseDouble("66")) {
            this.iconeCourrante = this.usine.getIconesUsine().get(1);
        }
        if (pourcentage >= Double.parseDouble("66") && pourcentage < Double.parseDouble("100")) {
            this.iconeCourrante = this.usine.getIconesUsine().get(2);
        }
        if (pourcentage == Double.parseDouble("100")) {
            this.iconeCourrante = this.usine.getIconesUsine().get(3);
        }
    }

    //cette methode va decrementer le temps restant ,retourne true si le temps est completement ecoulé sinon elle retourne false
    public boolean decrementerTempsRestantPourProduction() {
        if (this.tempsRestantPourProduction == 0) {
            if (this.usine instanceof UsineProduction) {
                UsineProduction usineProduction = (UsineProduction) usine;
                this.tempsRestantPourProduction = usineProduction.getIntervalProduction();
            }

            return true;
        }
        this.tempsRestantPourProduction--;
        return false;

    }

    public UsineSimulation(int id, Usine usine, Point position, Icone iconeCourrante) {
        this.id = id;
        this.usine = usine;
        this.position = position;
        this.iconeCourrante = iconeCourrante;
        this.tempsRestantPourProduction = 0;
        this.stock = new ArrayList<>();
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

    public int getTempsRestantPourProduction() {
        return tempsRestantPourProduction;
    }

    public void setTempsRestantPourProduction(int tempsRestantPourProduction) {
        this.tempsRestantPourProduction = tempsRestantPourProduction;
    }

    public List<Composant> getStock() {
        return stock;
    }

    public void setStock(List<Composant> stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "UsineSimulation{" +
                "id=" + id +
                ", usine=" + usine +
                ", position=" + position +
                ", iconeCourrante=" + iconeCourrante +
                ", tempsRestantPourProduction=" + tempsRestantPourProduction +
                ", stock=" + stock +
                '}';
    }
}
