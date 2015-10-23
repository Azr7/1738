package Graficas;

import javax.swing.JLabel;

/**
 * 
 * Clase grafica de PowerUp abstracta
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public abstract class PowerUpGrafica {
	/**
	 * Imagen del power up
	 */
	protected JLabel PowerLabel;
	
	/**
	 * Crea la grafica de un power up y guarda su imagen asociada
	 * @param PL imagen JLabel
	 */
	public PowerUpGrafica(JLabel PL)
	{
		PowerLabel = PL;
	}
	/**
	 * retorna la imagen asociada al power up
	 * @return imagen JLabel
	 */
	public JLabel getPowerLabel()
	{
		return PowerLabel;
	}
	
	/**
	 * Cambia la visibilidad de la imagen del powerup en la GUI , al explotar un rompible.
	 */
	public void visible()
	{
		PowerLabel.setVisible(true);
	}
}
