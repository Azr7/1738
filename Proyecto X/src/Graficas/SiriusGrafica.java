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
		super.EnemigoLabel.setIcon(new ImageIcon("Images/siriusfrente1.png"));
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
		EnemigoArriba[0] = new ImageIcon("Images/siriusarriba1.png");
		EnemigoArriba[1] = new ImageIcon("Images/siriusarriba2.png");
		EnemigoArriba[2] = new ImageIcon("Images/siriusarriba3.png");
				
		EnemigoIzquierda[0] = new ImageIcon("Images/siriusizq1.png");
		EnemigoIzquierda[1] = new ImageIcon("Images/siriusizq2.png");
		EnemigoIzquierda[2] = new ImageIcon("Images/siriusizq3.png");

		// Abajo y Der	
		EnemigoAbajo[0] = new ImageIcon("Images/siriusfrente1.png");
		EnemigoAbajo[1] = new ImageIcon("Images/siriusfrente2.png");
		EnemigoAbajo[2] = new ImageIcon("Images/siriusfrente3.png");
		
		EnemigoDerecha[0] = new ImageIcon("Images/siriusder1.png");
		EnemigoDerecha[1] = new ImageIcon("Images/siriusder2.png");
		EnemigoDerecha[2] = new ImageIcon("Images/siriusder3.png");

		//

		EnemigoMuere[0] = new ImageIcon("Images/siriusmuere1.png");
		EnemigoMuere[1] = new ImageIcon("Images/siriusmuere2.png");
		EnemigoMuere[2] = new ImageIcon("Images/siriusmuere3.png");
		EnemigoMuere[3] = new ImageIcon("Images/siriusmuere4.png");
		EnemigoMuere[4] = new ImageIcon("Images/siriusmuere5.png");
		EnemigoMuere[5] = new ImageIcon("Images/siriusmuere6.png");

	}
}