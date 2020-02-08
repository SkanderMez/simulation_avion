package simulation;

import config.MetadonneeConfig;
import config.SimulationConfig;
import org.xml.sax.SAXException;
import reseau.TypeComposant;
import reseau.UsineAvecEntree;
import reseau.UsineProduction;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;

public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation";
	private static final Dimension DIMENSION = new Dimension(700, 700);
	private List<UsineSimulation> usineSimulationList;

	private PanneauPrincipal panneauPrincipal;

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

			for(UsineSimulation usineSimulation:usineSimulationList){
				if (usineSimulation.getUsine() instanceof UsineAvecEntree) {
					UsineAvecEntree usineAvecEntree = (UsineAvecEntree) usineSimulation.getUsine();
					if (usineSimulation.veriferValiditeStock()) {

						//Gestion des icones
						usineSimulation.decrementerTempsRestantPourProduction();
						usineSimulation.modifierIconeCourante();

						if (usineSimulation.getTempsRestantPourProduction() == 0){
							//panneauPrincipal.getComposantList().add(((UsineAvecEntree) usineSimulation.getUsine()).getTypeComposantSortie());
						}
					}
				}
				else{
					//Gestion des icones
					usineSimulation.decrementerTempsRestantPourProduction();
					usineSimulation.modifierIconeCourante();
					if (usineSimulation.getUsine() instanceof UsineProduction){
						if (usineSimulation.getTempsRestantPourProduction() == 0){
							ComposantSortie composantSortie = new ComposantSortie();
							UsineProduction usineProduction = (UsineProduction) usineSimulation.getUsine();
							composantSortie.setTypeComposant(usineProduction.getTypeComposantSortie());
							composantSortie.setPosition(new Point(usineSimulation.getPosition()));

							UsineSimulation arrive = getUsineArriveFromUsineDepart(usineSimulation);

							//Set Vitesse

							composantSortie.setVitesse(getVitesse(usineSimulation,arrive));
							panneauPrincipal.getComposantList().put(composantSortie,arrive);

						}
					}
				}



			}

			//Set usineSimulationList in the Graphic*/
			//panneauPrincipal.setUsineSimulationList(usineSimulationList);
			repaint();
			System.out.println(evt.getNewValue());
		}
	}

	public Point getVitesse(UsineSimulation depart,UsineSimulation arrive){
		Point vitesse = new Point();
		int xDepart = (int)depart.getPosition().getX();
		int xArrive = (int)arrive.getPosition().getX();
		int yDepart = (int)depart.getPosition().getY();
		int yArrive = (int)arrive.getPosition().getY();
		if (xDepart == xArrive){
			vitesse.x=0;
			vitesse.y=1;
		}
		if (yDepart == yArrive){
			vitesse.x=1;
			vitesse.y=0;
		}

		if (xDepart > xArrive && yDepart>yArrive){
			vitesse.x=-1;
			vitesse.y=-1;
		}
		if (xDepart > xArrive && yDepart<yArrive){
			vitesse.x=-1;
			vitesse.y=1;
		}
		if (xDepart < xArrive && yDepart<yArrive){
			vitesse.x=1;
			vitesse.y=1;
		}
		if (xDepart < xArrive && yDepart>yArrive){
			vitesse.x=1;
			vitesse.y=-1;
		}

		return vitesse;

	}

	public UsineSimulation getUsineArriveFromUsineDepart(UsineSimulation depart) {
		SimulationConfig simulationConfig = new SimulationConfig(new MetadonneeConfig());
		List<Chemin> cheminList;
		try {
			cheminList = simulationConfig.getCheminInstances();
			for (Chemin chemin:cheminList){
				if (chemin.getDepart().getId()==depart.getId()){
					System.out.println("returned "+chemin.getArrivee());
					return chemin.getArrivee();
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<UsineSimulation> getUsineSimulationList() {
		return usineSimulationList;
	}

	public void setUsineSimulationList(List<UsineSimulation> usineSimulationList) {
		this.usineSimulationList = usineSimulationList;
	}

	public PanneauPrincipal getPanneauPrincipal() {
		return panneauPrincipal;
	}

	public void setPanneauPrincipal(PanneauPrincipal panneauPrincipal) {
		this.panneauPrincipal = panneauPrincipal;
	}
}
