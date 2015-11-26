package Graficas;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * Clase grafica de Rugulos
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class RugulosGrafica extends EnemigoGrafica{

	/**
	 * Crea la grafica asociada al enemigo rugulos
	 * @param i velocidad del enemigo
	 */
	public RugulosGrafica(int i){
		super(i);
		super.EnemigoLabel = new JLabel();
		super.EnemigoLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rugulos1.png")));
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
		super.EnemigoMuere = new ImageIcon[5];
		
		// Arriba e Izq son iguales
		EnemigoArriba[0] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rugulos1.png"));
		EnemigoArriba[1] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rugulos2.png"));
		EnemigoArriba[2] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rugulos3.png"));
		EnemigoIzquierda[0] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rugulos1.png"));
		EnemigoIzquierda[1] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rugulos2.png"));
		EnemigoIzquierda[2] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rugulos3.png"));
		
		// Abajo y Der son iguales		
		EnemigoAbajo[0] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rugulos-1.png"));
		EnemigoAbajo[1] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rugulos-2.png"));
		EnemigoAbajo[2] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rugulos-3.png"));
		EnemigoDerecha[0] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rugulos-1.png"));
		EnemigoDerecha[1] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rugulos-2.png"));
		EnemigoDerecha[2] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rugulos-3.png"));
		
		//
		
		EnemigoMuere[0] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rmuere1.png"));
		EnemigoMuere[1] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rmuere2.png"));
		EnemigoMuere[2] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rmuere3.png"));
		EnemigoMuere[3] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rmuere4.png"));
		EnemigoMuere[4] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/Rmuere5.png"));		
	}
	
}
