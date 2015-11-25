package Graficas;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * Clase grafica de Bombality
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class BombalityGrafica extends PowerUpGrafica{

	/**
	 * Crea la grafica asociada al bombality
	 */
	public BombalityGrafica() {
			super(new JLabel());
			super.PowerLabel.setIcon(new ImageIcon("bin/Images/bombality.png"));
		}
}


