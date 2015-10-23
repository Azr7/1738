package Graficas;
import javax.swing.Icon;
import GUIandLOGIC.*;

import Timers.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * Clase grafica de Bomberman
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class BombermanGrafica{
	/**
	 * Thread asociado a la grafica del bomberman, que controla los timers de movimiento y muerte
	 */
	protected BombermanThread BT;
	/**
	 * Imagen del bomberman
	 */
	protected JLabel BomberLabel;
	/**
	 * Animacion de mov abajo
	 */
	protected Icon BombermanAbajo[];
	/**
	 * Animacion de mov arriba
	 */	
	protected Icon BombermanArriba[];
	/**
	 * Animacion de mov derecha
	 */
	protected Icon BombermanDerecha[];
	/**
	 * Animacion de mov izquierda
	 */
	protected Icon BombermanIzquierda[];
	/**
	 * Animacion de muerte bomberman
	 */
	protected Icon BombermanMuere[];
	/**
	 * Pixeles de movimiento por vez de bobmerman
	 */
	protected int MovBomb = 2;
	/**
	 * Referencia a la GRAFICA del juego
	 */
	protected GUI miGUI;
	/**
	 * Tama�o de un casillero en la grafica del juego
	 */
	protected int MovPix = 16;
	/**
	 * velocidad del timer para realizar movimientos
	 */
	protected int velocidadSleep;

	/**
	 * Crea una nueva grafica de bomberman
	 */
	public BombermanGrafica()
	{
		ImageIcon BL = new ImageIcon("Images/Frente1.png");
		BomberLabel = new JLabel();
		BomberLabel.setIcon(BL);
		IniImages();
		velocidadSleep = 40;
	}

	/**
	 * Asocia una GUI a la grafica del bomberman
	 * @param g grafica gui
	 */
	public void setGUI(GUI g)
	{
		miGUI = g;
	}
	
	/**
	 * Setea un nuevo Thread para controlar los timers de la grafica del bomberman
	 * @param bt thread bomberman
	 */
	public void setBT(BombermanThread bt)
	{
		BT = bt;
	}
	/**
	 * Retorna el thread a cargo de la grafica del bomberman
	 * @return Thread BT
	 */
	public BombermanThread getBT()
	{
		return BT;
	}

	/**
	 * Setea una nueva velocidad del sleep, es usada en el speed up
	 * @param vS int velocidad
	 */
	public void setVelocidadSleep(int vS)
	{
		velocidadSleep = vS;
	}
	/**
	 * devuelve la velocidad actual del thread a cargo de la grafica
	 * @return int velocidad sleep
	 */
	public int getVelocidadSleep()
	{
		return velocidadSleep;
	}
	
	/**
	 * Inicializa las imagenes de animacion
	 */
	private void IniImages()
	{
		BombermanArriba = new ImageIcon[3];
		BombermanAbajo = new ImageIcon[3];
		BombermanDerecha = new ImageIcon[3];
		BombermanIzquierda = new ImageIcon[3];
		BombermanMuere = new ImageIcon[6];
		

		BombermanArriba[0] = new ImageIcon("Images/Arriba1.png");
		BombermanArriba[1] = new ImageIcon("Images/Arriba2.png");
		BombermanArriba[2] = new ImageIcon("Images/Arriba3.png");
		BombermanDerecha[0] = new ImageIcon("Images/derecha1.png");
		BombermanDerecha[1] = new ImageIcon("Images/derecha2.png");
		BombermanDerecha[2] = new ImageIcon("Images/derecha3.png");
		BombermanIzquierda[0] = new ImageIcon("Images/izq1.png");
		BombermanIzquierda[1] = new ImageIcon("Images/izq2.png");
		BombermanIzquierda[2] = new ImageIcon("Images/izq3.png");
		BombermanAbajo[0] = new ImageIcon("Images/frente2.png");
		BombermanAbajo[1] = new ImageIcon("Images/frente3.png");
		BombermanAbajo[2] = new ImageIcon("Images/frente1.png");
		BombermanMuere[0] = new ImageIcon("Images/muere1.png");
		BombermanMuere[1] = new ImageIcon("Images/muere2.png");
		BombermanMuere[2] = new ImageIcon("Images/muere3.png");
		BombermanMuere[3] = new ImageIcon("Images/muere4.png");
		BombermanMuere[4] = new ImageIcon("Images/muere5.png");
		BombermanMuere[5] = new ImageIcon("Images/muere6.png");
	}

	/**
	 * devuelve la imagen asociada al bomberman quieto
	 * @return JLabel imagenB
	 */
	public JLabel getBomberLabel()
	{
		return BomberLabel;
	}

	/**
	 * Realiza el movimiento grafico del bomberman, dado una direccion
	 * @param indice int direccion
	 */
	public void movimientoGrafico(int indice)
	{
	int velo = MovPix / MovBomb;
		try{
			if(indice == 0) //arriba
			{	
				for(int i=0,arg = 0; i<velo; i++,arg = (arg+1)%3)
				{
					BomberLabel.setLocation(BomberLabel.getX(),BomberLabel.getY() - MovBomb);
					BomberLabel.setIcon(BombermanArriba[arg]);
					BT.sleep(velocidadSleep);
				}
			}

			else if(indice == 1) //abajo
			{
				for(int i=0,arg = 0; i<velo; i++,arg = (arg+1)%3)
				{
					BomberLabel.setLocation(BomberLabel.getX(),BomberLabel.getY() + MovBomb);
					BomberLabel.setIcon(BombermanAbajo[arg]);
					BT.sleep(velocidadSleep);
				}
			}
			else if(indice == 2) //izq
			{
				for(int i=0,arg = 0; i<velo; i++,arg = (arg+1)%3)
				{					
					BomberLabel.setLocation(BomberLabel.getX() - MovBomb,BomberLabel.getY());
					BomberLabel.setIcon(BombermanIzquierda[arg]);
					BT.sleep(velocidadSleep);
				}
			}
			else if(indice == 3) //der
			{			
				for(int i=0,arg = 0; i<velo; i++,arg = (arg+1)%3)
				{
					BomberLabel.setLocation(BomberLabel.getX() + MovBomb,BomberLabel.getY());
					BomberLabel.setIcon(BombermanDerecha[arg]);
					BT.sleep(velocidadSleep);
				}
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Realiza los graficos correspondientes a la muerte del bomberman
	 */
	public void muereGrafico()
	{
		for(int i=0; i<6; i++)
		{
			try {
				BT.sleep(250);
				BomberLabel.setIcon(BombermanMuere[i]);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		BomberLabel.setIcon(null);
	}

}