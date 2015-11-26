package Graficas;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * Clase grafica de Sirius
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class SiriusGrafica extends EnemigoGrafica{

	/**
	 * crea  la grafica asociada al sirius
	 * @param i velocidad int
	 */
	public SiriusGrafica(int i) {
		super(i);	
		super.EnemigoLabel = new JLabel();
		super.EnemigoLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusfrente1.png")));
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
		super.EnemigoMuere = new ImageIcon[6];

		// Arriba e Izq
		EnemigoArriba[0] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusarriba1.png"));
		EnemigoArriba[1] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusarriba2.png"));
		EnemigoArriba[2] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusarriba3.png"));
				
		EnemigoIzquierda[0] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusizq1.png"));
		EnemigoIzquierda[1] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusizq2.png"));
		EnemigoIzquierda[2] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusizq3.png"));

		// Abajo y Der	
		EnemigoAbajo[0] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusfrente1.png"));
		EnemigoAbajo[1] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusfrente2.png"));
		EnemigoAbajo[2] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusfrente3.png"));
		
		EnemigoDerecha[0] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusder1.png"));
		EnemigoDerecha[1] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusder2.png"));
		EnemigoDerecha[2] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusder3.png"));

		//

		EnemigoMuere[0] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusmuere1.png"));
		EnemigoMuere[1] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusmuere2.png"));
		EnemigoMuere[2] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusmuere3.png"));
		EnemigoMuere[3] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusmuere4.png"));
		EnemigoMuere[4] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusmuere5.png"));
		EnemigoMuere[5] = new ImageIcon(getClass().getClassLoader().getResource("Graficas/siriusmuere6.png"));

	}
}