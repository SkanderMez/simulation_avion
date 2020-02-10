package simulation;

import javax.swing.SwingWorker;

public class Environnement extends SwingWorker<Object, String> {
	public static boolean actif = true;
	private static final int DELAI = 5;
	private int tour = 0;
	
	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			tour++;

				firePropertyChange("Tour",tour-1 , tour);

		}
		return null;
	}

}