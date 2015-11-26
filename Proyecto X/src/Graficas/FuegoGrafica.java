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
		fuego[0] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/f1.png"));
		fuego[1] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/f2.png"));
		fuego[2] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/f3.png"));
		fuego[3] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/f4.png"));
		fuego[4] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/f5.png"));
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
