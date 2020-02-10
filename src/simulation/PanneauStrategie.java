package simulation;

import config.SimulationConfig;
import reseau.Entrepot;
import vente.VenteAvion;
import vente.VenteAvionAleatoire;
import vente.VenteAvionPersonnalisee;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class PanneauStrategie extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanneauStrategie() {

		ButtonGroup groupeBoutons = new ButtonGroup();
		//Vente Aleatoire
		JRadioButton strategie1 = new JRadioButton("Strat�gie 1");

		//Vente Personnalis�e
		JRadioButton strategie2 = new JRadioButton("Strat�gie 2");
		
		JButton boutonConfirmer = new JButton("Confirmer");

		boutonConfirmer.addActionListener((ActionEvent e) -> {
			// TODO - Appeler la  strat�gie

			//Vente Aleatoire
			if (getSelectedButtonText(groupeBoutons).equals("Strat�gie 1")){
				VenteAvion venteAvion = new VenteAvionAleatoire();
				vendreAvion(venteAvion);

			} else
				//Vente Personnalis�e
				if (getSelectedButtonText(groupeBoutons).equals("Strat�gie 2")){
				VenteAvion venteAvion = new VenteAvionPersonnalisee();
				vendreAvion(venteAvion);
			}
			System.out.println(getSelectedButtonText(groupeBoutons));
			// Fermer la fen�tre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		JButton boutonAnnuler = new JButton("Annuler");

		boutonAnnuler.addActionListener((ActionEvent e) -> {
			// Fermer la fen�tre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		groupeBoutons.add(strategie1);
		groupeBoutons.add(strategie2);		
		add(strategie1);
		add(strategie2);		
		add(boutonConfirmer);
		add(boutonAnnuler);

	}


	public void vendreAvion(VenteAvion venteAvion){
		venteAvion.vendreAvion();
	}

	/**
	 * Retourne le bouton s�lectionn� dans un groupe de boutons.
	 * @param groupeBoutons
	 * @return
	 */
	public String getSelectedButtonText(ButtonGroup groupeBoutons) {
		for (Enumeration<AbstractButton> boutons = groupeBoutons.getElements(); boutons.hasMoreElements();) {
			AbstractButton bouton = boutons.nextElement();
			if (bouton.isSelected()) {
				return bouton.getText();
			}
		}

		return null;
	}

}
