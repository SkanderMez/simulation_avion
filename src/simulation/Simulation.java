package simulation;


public class Simulation {

	/**
	 * Cette classe représente l'application dans son ensemble.
	 */
	public static void main(String[] args)  {
		Environnement environnement = Environnement.getInstance();
		FenetrePrincipale fenetre = new FenetrePrincipale();

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();
	}
}
