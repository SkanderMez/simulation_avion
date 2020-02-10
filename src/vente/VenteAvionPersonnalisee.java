package vente;

import config.SimulationConfig;
import reseau.Entrepot;
import simulation.Environnement;
import simulation.UsineSimulation;

import java.util.List;
import java.util.Random;

import static javax.swing.JOptionPane.showMessageDialog;

public class VenteAvionPersonnalisee implements VenteAvion {


    @Override

    //Principe : Vendre 2 avion lorsque le nombre d'avion au stock est égale à 4
    public void vendreAvion() {

    int nombreAvionAuStock;

        List<UsineSimulation> usineSimulationList = SimulationConfig.getUniqueUsinesSimulationInstances();
        for (UsineSimulation usineSimulation : usineSimulationList) {
            if (usineSimulation.getUsine() instanceof Entrepot) {
                nombreAvionAuStock =usineSimulation.getStock().size();
                if (nombreAvionAuStock >= 4 ){
                    usineSimulation.getStock().remove(usineSimulation.getStock().size()-1);
                    usineSimulation.getStock().remove(usineSimulation.getStock().size()-1);
                    System.out.println("Vente Personnalisée : Le b d'avion au stock est 4 , on vend 2 avions");
                    showMessageDialog(null, "Vente Personnalisée : Le nombre d'avion au stock est 4 , on vend 2 avions, il reste "+usineSimulation.getStock().size()+ " avions");

                }
                 else
                {
                    System.out.println("La vente Personnalisée n'est réalisé que lorsqu'il y a plus que 4 avions");
                    showMessageDialog(null, "La vente Personnalisée n'est réalisé que lorsqu'il y a plus que 4 avions");

                }
            }
        }

        if (!Environnement.actif){
            Environnement.actif = true;
        }

    }
}
