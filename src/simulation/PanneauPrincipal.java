package simulation;

import java.awt.*;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	
	// Variables temporaires de la demonstration:
	private Point position = new Point(0,0);
	private Point vitesse = new Point(1,1);
	private int taille = 32;


	void drawLines(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawLine(120, 50, 360, 50);

		g2d.draw(new Line2D.Double(59.2d, 99.8d, 419.1d, 99.8d));

		g2d.draw(new Line2D.Float(21.50f, 132.50f, 459.50f, 132.50f));

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// On ajoute à la position le delta x et y de la vitesse
		System.out.println(position.toString());
		position.translate(vitesse.x, vitesse.y);
		//g.fillRect(position.x, position.y, taille, taille);
		try {
			g.drawImage(ImageIO.read(new File("src/ressources/avion.png")), position.x, position.y, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		drawLines(g);

	}

}