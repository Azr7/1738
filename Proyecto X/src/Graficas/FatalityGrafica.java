package Graficas;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * Clase grafica de Fatality
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class FatalityGrafica extends PowerUpGrafica{

	/**
	 * Crea la grafica asociada al power up fatality
	 */
	public FatalityGrafica() {
		super(new JLabel());
		super.PowerLabel.setIcon(new ImageIcon("Images/fatality.png"));
	}

}
