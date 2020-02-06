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
	public static void main(String[] args) {
		//Environnement environnement = new Environnement();
		//FenetrePrincipale fenetre = new FenetrePrincipale();

		//environnement.addPropertyChangeListener(fenetre);
		//environnement.execute();

		MetadonneeConfig metadonneeConfig = new MetadonneeConfig();
		SimulationConfig c = new SimulationConfig(metadonneeConfig);
		try {
			//List<Usine> usines = metadonneeConfig.getUsineInstances();
			//for (Usine usine:usines){
			//	System.out.println(usine);
			//}
			//c.getUsinesSimulationInstances();
			c.getCheminInstances();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
