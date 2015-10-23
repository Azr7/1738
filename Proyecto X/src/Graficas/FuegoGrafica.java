package Graficas;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * 
 * Clase grafica de Fuego
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class FuegoGrafica {
	
	/**
	 * Animacion de las explociones y fuego
	 */
	protected Icon fuego[];
	
	/**
	 * Inicia la grafica del fuego
	 */
	public FuegoGrafica()
	{
		fuego = new ImageIcon[5];
		fuego[0] = new ImageIcon("Images/f1.png");
		fuego[1] = new ImageIcon("Images/f2.png");
		fuego[2] = new ImageIcon("Images/f3.png");
		fuego[3] = new ImageIcon("Images/f4.png");
		fuego[4] = new ImageIcon("Images/f5.png");
	}
	
	/**
	 * devuelve una imagen asociada a un argumento
	 * @param r int argumento
	 * @return imagen
	 */
	public Icon getFuego(int r)
	{
		return fuego[r];
	}
}
