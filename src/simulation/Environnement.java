package simulation;

import javax.swing.SwingWorker;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 25;
	private int tour = 0;
	
	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			tour++;
			/**
			 * C'est ici que vous aurez à faire la gestion de la notion de tour.
			 */
				firePropertyChange("Tour",tour-1 , tour);

		}
		return null;
	}

}