package simulation;

import config.SimulationConfig;
import reseau.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PanneauPrincipal extends JPanel {

    private static final long serialVersionUID = 1L;

    // Variables temporaires de la demonstration:
    private Point position = new Point(0, 0);
    private Point vitesse = new Point(1, 1);
    private int taille = 32;

    private Map<ComposantSortie, UsineSimulation> composantList = new HashMap<>();


    //Dessiner tous le chemins
    private void dessinerChemins(Graphics g) {
        List<Chemin> cheminList = SimulationConfig.getUniqueCheminsInstances();

        Graphics2D g2d = (Graphics2D) g;
        for (Chemin chemin : cheminList) {
            int xDepart = (int) chemin.getDepart().getPosition().getX();
            int yDepart = (int) chemin.getDepart().getPosition().getY();
            int xArrivee = (int) chemin.getArrivee().getPosition().getX();
            int yArrivee = (int) chemin.getArrivee().getPosition().getY();
            g2d.drawLine(xDepart, yDepart, xArrivee, yArrivee);
        }


    }

    //Dessiner toutes les usines
    private void dessinerUsines(Graphics g) {
        List<UsineSimulation> usineSimulationList = SimulationConfig.getUniqueUsinesSimulationInstances();

        try {
            for (UsineSimulation usineSimulation : usineSimulationList) {
                String usineIconPath = usineSimulation.getIconeCourrante().getPath();
                Point usinePosition = usineSimulation.getPosition();
                g.drawImage(ImageIO.read(new File(usineIconPath)), usinePosition.x, usinePosition.y, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void dessinerComposant(Graphics g) {

        List<Composant> composantToRemove = new ArrayList<>();

        Map<ComposantSortie, UsineSimulation> composantList = this.composantList;

        // s'il y a des composant produits à dessiner
        if (composantList != null) {
            //On va dessiner chaque composant
            composantList.forEach((composant, arrive) -> {
                String composantIconPath = composant.getTypeComposant().getIcone().getPath();
                Point positionComposant = composant.getPosition();

                //Si le composant n'est pas arrivé à l'usine destination, on le fait translater
                if (composant.getPosition().getX() != arrive.getPosition().getX() || composant.getPosition().getY() != composant.getPosition().getY()) {
                    try {
                        g.drawImage(ImageIO.read(new File(composantIconPath)), positionComposant.x, positionComposant.y, this);
                        composant.getPosition().translate(composant.getVitesse().x, composant.getVitesse().y);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //Si le composant est arrivé à l'usine destination , on va l'ajouter au stock de cette usine
                else {
                    //ajouter le composant produit a la liste des composants de l'usine d'arrive
                    if (composant.getDestination() != null) {
                        if (composant.getDestination().getUsine() instanceof Entrepot) {
//                            System.out.println("Taille Stock avant"+composant.getDestination().getStock().size());
                        }

                        composant.getDestination().addToStock(new Composant(composant.getTypeComposant()));
                        if (composant.getDestination().getUsine() instanceof Entrepot) {
                            composant.getDestination().modifierIconeCourante();
                        }
                        //On va ajouter ce composant à une liste composant  et puis on va supprimer ces composants du dessin
                        composantToRemove.add(composant);
                        // System.out.println("After "+composant.getDestination().getStock().size());
                    }
                }

            });

            //Supprimer les composants arrivés à destination
            for (Composant c : composantToRemove) {
                composantList.remove(c);
            }

        }


    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        dessinerChemins(g);
        dessinerUsines(g);
        dessinerComposant(g);
    }


    public Map<ComposantSortie, UsineSimulation> getComposantList() {
        return composantList;
    }

    public void setComposantList(Map<ComposantSortie, UsineSimulation> composantList) {
        this.composantList = composantList;
    }
}