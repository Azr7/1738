package Graficas;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * Clase grafica de Altair
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class AltairGrafica extends EnemigoGrafica{

	public AltairGrafica(int i) {
		super(i);
		super.EnemigoLabel = new JLabel();
		super.EnemigoLabel.setIcon(new ImageIcon("Images/altair1.png"));
		IniciarImagenes();
	}
	
	/**
	 * Inicializa las imagenes de movimiento
	 */
	private void IniciarImagenes()
	{
		super.EnemigoArriba = new ImageIcon[3];
		super.EnemigoAbajo = new ImageIcon[3];
		super.EnemigoDerecha = new ImageIcon[3];
		super.EnemigoIzquierda = new ImageIcon[3];
		super.EnemigoMuere = new ImageIcon[1];
		
		// Arriba e Izq son iguales
		EnemigoArriba[0] = new ImageIcon("Images/altair1.png");
		EnemigoArriba[1] = new ImageIcon("Images/altair2.png");
		EnemigoArriba[2] = new ImageIcon("Images/altair3.png");
		EnemigoIzquierda[0] = new ImageIcon("Images/altair1.png");
		EnemigoIzquierda[1] = new ImageIcon("Images/altair2.png");
		EnemigoIzquierda[2] = new ImageIcon("Images/altair3.png");
		
		// Abajo y Der son iguales		
		EnemigoAbajo[0] = new ImageIcon("Images/altair-1.png");
		EnemigoAbajo[1] = new ImageIcon("Images/altair-2.png");
		EnemigoAbajo[2] = new ImageIcon("Images/altair-3.png");
		EnemigoDerecha[0] = new ImageIcon("Images/altair-1.png");
		EnemigoDerecha[1] = new ImageIcon("Images/altair-2.png");
		EnemigoDerecha[2] = new ImageIcon("Images/altair-3.png");
		
		//
		
		EnemigoMuere[0] = new ImageIcon("Images/altairmuere.png");
	}

}
