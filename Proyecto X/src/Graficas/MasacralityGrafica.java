package Graficas;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * Clase grafica de Masacrality
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class MasacralityGrafica extends PowerUpGrafica{

	/**
	 * Crea la grafica asociada al power up masacrality
	 */
	public MasacralityGrafica() {
		super(new JLabel());
		super.PowerLabel.setIcon(new ImageIcon("bin/Images/masacrality.png"));
	}

}
