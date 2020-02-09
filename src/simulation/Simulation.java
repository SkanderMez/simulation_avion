package simulation;

import config.MetadonneeConfig;
import config.SimulationConfig;
import org.xml.sax.SAXException;
import reseau.Usine;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Simulation {

	/**
	 * Cette classe représente l'application dans son ensemble.
	 */
	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
		MetadonneeConfig metadonneeConfig = new MetadonneeConfig();
		SimulationConfig c = new SimulationConfig(metadonneeConfig);
		System.out.println(c.getCheminInstances());
		try {
			List<UsineSimulation> usineSimulationList = c.getUsinesSimulationInstances();
			List<Chemin> cheminList = c.getCheminInstances();
			Environnement environnement = new Environnement();
			FenetrePrincipale fenetre = new FenetrePrincipale();
			fenetre.setUsineSimulationList(usineSimulationList);

			fenetre.getPanneauPrincipal().setUsineSimulationList(usineSimulationList);

			environnement.addPropertyChangeListener(fenetre);
			environnement.execute();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
