package simulation;

import config.SimulationConfig;
import reseau.Entrepot;
import reseau.TypeComposant;
import reseau.UsineAvecEntree;
import reseau.UsineProduction;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

    private static final long serialVersionUID = 1L;
    private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation";
    private static final Dimension DIMENSION = new Dimension(700, 700);

    private PanneauPrincipal panneauPrincipal;

    private List<UsineSimulation> usineSimulationList = SimulationConfig.getUniqueUsinesSimulationInstances();

    public FenetrePrincipale() {
        //PanneauPrincipal panneauPrincipal = new PanneauPrincipal();
        panneauPrincipal = new PanneauPrincipal();
        MenuFenetre menuFenetre = new MenuFenetre();
        add(panneauPrincipal);
        add(menuFenetre, BorderLayout.NORTH);
        // Faire en sorte que le X de la fenêtre ferme la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(TITRE_FENETRE);
        setSize(DIMENSION);
        // Rendre la fenêtre visible
        setVisible(true);
        // Mettre la fenêtre au centre de l'écran
        setLocationRelativeTo(null);
        // Empêcher la redimension de la fenêtre
        setResizable(false);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Tour")) {
            Map<UsineSimulation, List<Composant>> composantToDeleteFromUsineStock = new HashMap<>();


            for (UsineSimulation usineSimulation : usineSimulationList) {

                //Si l'usine est une usine avec entrée
                if (usineSimulation.getUsine() instanceof UsineAvecEntree) {

                    List<Composant> composantToRemove = new ArrayList<>();

                    UsineAvecEntree usineAvecEntree = (UsineAvecEntree) usineSimulation.getUsine();
                    //Si elle a les composants nécessaires pour la production
                    if (usineSimulation.veriferValiditeStock()) {

                        //On va décrementer le temps restant à la production (qui est initialisé à l'interval de production) à chaque tours
                        usineSimulation.decrementerTempsRestantPourProduction();

                        //On modifier l'icone de l'usine par rapport au temps restant
                        usineSimulation.modifierIconeCourante();

                        //Si le temps restant est fini , l'usine peut produire son composant
                        if (usineSimulation.getTempsRestantPourProduction() == 0) {
                            //On instancie un composant selon l'usine qui l'a produit
                            ComposantSortie composantSortie = new ComposantSortie();
                            composantSortie.setTypeComposant(usineAvecEntree.getTypeComposantSortie());
                            composantSortie.setPosition(new Point(usineSimulation.getPosition()));
                            composantSortie.setDepart(usineSimulation);
                            UsineSimulation arrive = getUsineArriveFromUsineDepart(usineSimulation);
                            composantSortie.setDestination(arrive);
                            //Set Vitesse
                            composantSortie.setVitesse(getVitesse(usineSimulation, arrive));

                            // Si le stock de l'usine est positif après sa production , on va supprimer les composants utilisés à cette production du stock
                            if (usineSimulation.getStock().size() > 0) {

                                UsineAvecEntree usine3 = (UsineAvecEntree) usineSimulation.getUsine();
                                List<TypeComposant> typeComposants = usine3.getTypeComposantList();
                                for (TypeComposant typecomposant : typeComposants) {
                                    int quantité = usine3.getQuantiteByTypeComposant(typecomposant);
                                    int compteur = 0;
                                    while (compteur < quantité) {
                                        for (Composant composant3 : usineSimulation.getStock()) {
                                            if (composant3.getTypeComposant().getType().equals(typecomposant.getType())) {
                                                //usineSimulation.getStock().remove(composant3);
                                                composantToRemove.add(composant3);
                                                compteur++;

                                            }
                                        }
                                    }


                                }

                                composantToDeleteFromUsineStock.put(usineSimulation, composantToRemove);


                            }


                            panneauPrincipal.getComposantList().put(composantSortie, arrive);

                        }
                    }
                } else {

                    //Si l'usine est une usine de production ( sans entrées )
                    if (usineSimulation.getUsine() instanceof UsineProduction) {

                        //decrémenter le temps restant pour production
                        usineSimulation.decrementerTempsRestantPourProduction();

                        //Gestion des icones par rapport au temps restant
                        usineSimulation.modifierIconeCourante();

                        //si le temps est achevé , on génère le composant
                        if (usineSimulation.getTempsRestantPourProduction() == 0) {
                            ComposantSortie composantSortie = new ComposantSortie();
                            UsineProduction usineProduction = (UsineProduction) usineSimulation.getUsine();
                            composantSortie.setTypeComposant(usineProduction.getTypeComposantSortie());
                            composantSortie.setPosition(new Point(usineSimulation.getPosition()));
                            UsineSimulation arrive = getUsineArriveFromUsineDepart(usineSimulation);
                            composantSortie.setDestination(arrive);
                            composantSortie.setDepart(usineSimulation);
                            //Set Vitesse
                            composantSortie.setVitesse(getVitesse(usineSimulation, arrive));
                            panneauPrincipal.getComposantList().put(composantSortie, arrive);

                        }
                    } else
                        // s'il s'agit d'un entreport
                        if (usineSimulation.getUsine() instanceof Entrepot) {
                            //On modifier son icone selon la CAPACITE
                        usineSimulation.modifierIconeCourante();

                        //S'il L'entrepot est plein , on bloque l'application
                        Entrepot entrepot = (Entrepot) usineSimulation.getUsine();
                        if (usineSimulation.getStock().size() == entrepot.getCapacite()) {
                            Environnement.actif = false;
                        }
                    }
                }


            }


            composantToDeleteFromUsineStock.forEach((usine, composantList) -> {
                for (Composant c : composantList) {
                    usine.getStock().remove(c);
                    usine.setIconeCourrante(usine.getUsine().getIconesUsine().get(0));
                }
            });

            //Set usineSimulationList in the Graphic*/
            repaint();
        }
    }

    public Point getVitesse(UsineSimulation depart, UsineSimulation arrive) {
        Point vitesse = new Point();
        int xDepart = (int) depart.getPosition().getX();
        int xArrive = (int) arrive.getPosition().getX();
        int yDepart = (int) depart.getPosition().getY();
        int yArrive = (int) arrive.getPosition().getY();
        if (xDepart == xArrive) {
            vitesse.x = 0;
            vitesse.y = 1;
        }
        if (yDepart == yArrive) {
            vitesse.x = 1;
            vitesse.y = 0;
        }

        if (xDepart > xArrive && yDepart > yArrive) {
            vitesse.x = -1;
            vitesse.y = -1;
        }
        if (xDepart > xArrive && yDepart < yArrive) {
            vitesse.x = -1;
            vitesse.y = 1;
        }
        if (xDepart < xArrive && yDepart < yArrive) {
            vitesse.x = 1;
            vitesse.y = 1;
        }
        if (xDepart < xArrive && yDepart > yArrive) {
            vitesse.x = 1;
            vitesse.y = -1;
        }

        return vitesse;

    }

    public UsineSimulation getUsineArriveFromUsineDepart(UsineSimulation depart) {

        List<Chemin> cheminList = SimulationConfig.getUniqueCheminsInstances();

        for (Chemin chemin : cheminList) {
            if (chemin.getDepart().getId() == depart.getId()) {
                //System.out.println("returned " + chemin.getArrivee());
                UsineSimulation arrive = chemin.getArrivee();
                for (UsineSimulation usineSimulation : this.usineSimulationList) {
                    if (usineSimulation.getId() == arrive.getId()) {
                        return usineSimulation;
                    }
                }
            }
        }

        return null;

    }

}
