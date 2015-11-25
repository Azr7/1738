package Graficas;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * Clase grafica de SpeedUp
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class SpeedUpGrafica extends PowerUpGrafica{

	/**
	 * Crea la grafica asociada al power up speed up
	 */
	public SpeedUpGrafica() {
		super(new JLabel());
		super.PowerLabel.setIcon(new ImageIcon("bin/Images/speedup.png"));
	}

}
