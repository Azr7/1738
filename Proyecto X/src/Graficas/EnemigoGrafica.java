package Graficas;

import javax.swing.Icon;

import Timers.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * Clase grafica de Enemigo abstracta
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public abstract class EnemigoGrafica{
	/**
	 * Imagen del enemigo quieto
	 */
	protected JLabel EnemigoLabel;
	/**
	 * Animacion movimiento abajo
	 */
	protected Icon EnemigoAbajo[];
	/**
	 * Animacion movimiento arriba
	 */
	protected Icon EnemigoArriba[];
	/**
	 * Animacion movimiento derecha
	 */
	protected Icon EnemigoDerecha[];
	/**
	 * Animacion movimiento izquierda
	 */
	protected Icon EnemigoIzquierda[];
	/**
	 * Animacion movimiento muerte del enemigo
	 */
	protected Icon EnemigoMuere[];
	/**
	 * Tamaño en pixeles de una casilla
	 */
	protected int MovPix = 16;
	/**
	 * velocidad en pixeles de movimiento del enemigo
	 */
	protected int velocidad;
	/**
	 * Thread a cargo de controlar los timers del enemigo
	 */
	protected EnemigoThread EThread;
	/**
	 * 
	 */
	protected int indice;
	/**
	 * Crea la grafica asociada a un tipo de enemigo
	 * @param v velocidad int
	 */
	protected EnemigoGrafica(int v)
	{
		EnemigoLabel = new JLabel();
		velocidad = v;
	}

	/**
	 * devuelve la imagen asociada al enemigo quieto
	 * @return JLabel imagenE
	 */
	public JLabel getEnemyLabel()
	{
		return EnemigoLabel;
	}
	/**
	 * Reliaza las acciones correspondientes al movimiento grafico del enemigo
	 * @param indice int direccion
	 */
	public void movimientoGrafico(int indice)
	{
		
		try {
			if(indice == 0) //arriba
			{	
				for(int i=0,arg =0; i<MovPix; i++, arg = (arg+1)%3) // Con arg no me caigo del arreglo de imagenes.
				{
					EnemigoLabel.setLocation(EnemigoLabel.getX(),EnemigoLabel.getY() - 1);
					EnemigoLabel.setIcon(EnemigoArriba[arg]);
					EThread.sleep(velocidad);
				}
			}

			else if(indice == 1) //abajo
			{
				for(int i=0,arg =0; i<MovPix; i++, arg = (arg+1)%3) // Con arg no me caigo del arreglo de imagenes.
				{
					EnemigoLabel.setLocation(EnemigoLabel.getX(),EnemigoLabel.getY() + 1);
					EnemigoLabel.setIcon(EnemigoAbajo[arg]);
					EThread.sleep(velocidad);
				}
			}
			else if(indice == 2) //izq
			{
				for(int i=0,arg =0; i<MovPix; i++, arg = (arg+1)%3) // Con arg no me caigo del arreglo de imagenes.
				{
					EnemigoLabel.setLocation(EnemigoLabel.getX() - 1,EnemigoLabel.getY());
					EnemigoLabel.setIcon(EnemigoIzquierda[arg]);
					EThread.sleep(velocidad);
				}
			}
			else if(indice == 3) //der
			{		
				for(int i=0,arg =0; i<MovPix; i++, arg = (arg+1)%3) // Con arg no me caigo del arreglo de imagenes.
				{
					EnemigoLabel.setLocation(EnemigoLabel.getX() + 1,EnemigoLabel.getY());
					EnemigoLabel.setIcon(EnemigoDerecha[arg]);
					EThread.sleep(velocidad);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Setea el thread a cargo de la clase
	 * @param t thread
	 */
	public void setEnemigoThread(EnemigoThread t)
	{
		EThread = t;
	}
	/**
	 * retorna el thread a cargo
	 * @return thread ET;
	 */
	public EnemigoThread getEnemigoThread()
	{
		return EThread;
	}

	/**
	 * Realiza los graficos correspondientes a la muerte de un enemigo.
	 * - Nota : Todavia no está probado -
	 */
	public void muereGrafico()
	{			
		EThread.toggleDeath();
	}
	/**
	 * 
	 * @param i
	 */
	public void setIndice(int i){
		indice = i;
	}
	/**
	 * 
	 * @return
	 */
	public int getIndice(){
		return indice;
	}
}
