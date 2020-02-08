package simulation;

import config.MetadonneeConfig;
import config.SimulationConfig;
import org.xml.sax.SAXException;
import reseau.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;

public class PanneauPrincipal extends JPanel {

    private static final long serialVersionUID = 1L;

    // Variables temporaires de la demonstration:
    private Point position = new Point(0, 0);
    private Point vitesse = new Point(1, 1);
    private int taille = 32;

    private List<UsineSimulation> usineSimulationList;
    private Map<ComposantSortie, UsineSimulation> composantList = new HashMap<>();


    void dessinerChemins(Graphics g) throws IOException, SAXException, ParserConfigurationException {
        SimulationConfig simulationConfig = new SimulationConfig(new MetadonneeConfig());
        List<Chemin> cheminList = simulationConfig.getCheminInstances();

        Graphics2D g2d = (Graphics2D) g;
        for (Chemin chemin : cheminList) {
            int xDepart = (int) chemin.getDepart().getPosition().getX();
            int yDepart = (int) chemin.getDepart().getPosition().getY();
            int xArrivee = (int) chemin.getArrivee().getPosition().getX();
            int yArrivee = (int) chemin.getArrivee().getPosition().getY();
            g2d.drawLine(xDepart, yDepart, xArrivee, yArrivee);
        }


    }

    void dessinerUsines(Graphics g) throws IOException, SAXException, ParserConfigurationException {
        List<UsineSimulation> usineSimulationList = this.usineSimulationList;

        for (UsineSimulation usineSimulation : usineSimulationList) {
            String usineIconPath = usineSimulation.getIconeCourrante().getPath();
            Point usinePosition = usineSimulation.getPosition();
            g.drawImage(ImageIO.read(new File(usineIconPath)), usinePosition.x, usinePosition.y, this);

        }

    }

    void dessinerComposant(Graphics g) throws IOException {
        Map<ComposantSortie, UsineSimulation> composantList = this.composantList;

        if (composantList != null) {
            composantList.forEach((composant, arrive) -> {
                String composantIconPath = composant.getTypeComposant().getIcone().getPath();
                Point positionComposant = composant.getPosition();

                if (composant.getPosition().getX() != arrive.getPosition().getX() || composant.getPosition().getY() != composant.getPosition().getY()) {
                    try {
                        g.drawImage(ImageIO.read(new File(composantIconPath)), positionComposant.x, positionComposant.y, this);
                        composant.getPosition().translate(composant.getVitesse().x, composant.getVitesse().y);

                        //ajouter le composant a la liste des composants entree de l'usine / entrepot
                        for (UsineSimulation usineSimulation : this.usineSimulationList) {
                            if (usineSimulation.getId() == arrive.getId()) {
                                if (usineSimulation.getUsine() instanceof UsineAvecEntree) {
                                    usineSimulation.addToStock(new Composant(composant.getTypeComposant()));
                                }
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });
        }

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        try {
            dessinerChemins(g);
            dessinerUsines(g);
           // dessinerComposant(g);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    public List<UsineSimulation> getUsineSimulationList() {
        return usineSimulationList;
    }

    public void setUsineSimulationList(List<UsineSimulation> usineSimulationList) {
        this.usineSimulationList = usineSimulationList;
    }

    public Map<ComposantSortie, UsineSimulation> getComposantList() {
        return composantList;
    }

    public void setComposantList(Map<ComposantSortie, UsineSimulation> composantList) {
        this.composantList = composantList;
    }
}