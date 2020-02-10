package vente;

import config.SimulationConfig;
import reseau.Entrepot;
import simulation.Environnement;
import simulation.UsineSimulation;

import java.util.List;
import java.util.Random;

import static javax.swing.JOptionPane.showMessageDialog;

public class VenteAvionAleatoire implements VenteAvion {
    @Override
    public void vendreAvion() {

        int nombreAvionAVendre=0;

        List<UsineSimulation> usineSimulationList = SimulationConfig.getUniqueUsinesSimulationInstances();
        for (UsineSimulation usineSimulation : usineSimulationList) {
            if (usineSimulation.getUsine() instanceof Entrepot) {
                int capaciteEntrepot = ((Entrepot) usineSimulation.getUsine()).getCapacite();


                //Generer un nombre d'avion a vendre aléatoire et inférieur aux avions disponibles au stock
                do {
                    nombreAvionAVendre = new Random().nextInt(capaciteEntrepot+1); // [0...capacite]

                } while (nombreAvionAVendre>usineSimulation.getStock().size()-1);

                //Vendre l'avion
                for (int compteur=0;compteur<nombreAvionAVendre;compteur++){
                    usineSimulation.getStock().remove(usineSimulation.getStock().size()-1);
                }

            }
        }


        System.out.println("Vente Aleatoire : Le nombre d'avion vendu est  : "+nombreAvionAVendre);
        showMessageDialog(null, "Vente Aleatoire : Le nombre d'avion vendu est  : "+nombreAvionAVendre);

        //Reprendre l'application après la vente si elle était bloquée
        if (!Environnement.actif){
            Environnement.actif = true;
            System.out.println("reprendre");
        }

    }
}
