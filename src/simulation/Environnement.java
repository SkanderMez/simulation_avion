package simulation;

import javax.swing.SwingWorker;

public class Environnement extends SwingWorker<Object, String> {
	public static boolean actif = true;
	public  boolean paused = false;
	private static final int DELAI = 30;
	private int tour = 0;
	private static Environnement uniqueInstance;

	public static Environnement getInstance(){
		if (uniqueInstance == null){
			uniqueInstance = new Environnement();
		}
		return uniqueInstance;
	}
	@Override
	protected Object doInBackground() throws Exception {
		while(!isCancelled()) {
			if (!paused){
				Thread.sleep(DELAI);
				tour++;

				firePropertyChange("Tour",tour-1 , tour);
			}

		}
		return null;
	}

}