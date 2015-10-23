package Timers;
import Personaje.*;
import GUIandLOGIC.*;

/**
 * 
 * Clase Thread del bomberman, contorla su timer
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class BombermanThread extends Thread{
	
	/**
	 * referencia a la logica del bobmerman
	 */
	protected Bomberman BombermanLogica;
	/**
	 * referencia a la logica
	 */
	protected Logica MiLogica;
	/**
	 * referencia a la gui
	 */
	protected GUI miGUI;
	/**
	 * boolean auxiliar para determina si se puede mover
	 */
	protected volatile boolean stop = false;

	/**
	 * crea un nuevo thread que controla los tiempos del bomberman
	 * @param B bomberman
	 * @param L logica
	 * @param g gui
	 */
	public BombermanThread(Bomberman B, Logica L, GUI g)
	{
		BombermanLogica = B;
		MiLogica = L;
		miGUI = g;
	}
	
	public void run() {
		while(!stop){
				if(!miGUI.isLocked()){
				MiLogica.getBombermanTablero().mover(miGUI.getDireccion());
				miGUI.toggle();
			}
		}
	}
	
	/**
	 * detiene la ejecucion del hilo del bomberman
	 */
	public void destroy() {
		this.interrupt();
		stop = true;
	}

	/**
	 * retorna la gui asociada a la clase
	 * @return gui G
	 */
	public GUI getGUI()
	{
		return miGUI;
	}
	
	
}
