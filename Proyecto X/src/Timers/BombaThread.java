package Timers;
import GUIandLOGIC.*;
import Personaje.*;


/**
 * 
 * Clase BombaThread, controla el timer de la bomba
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class BombaThread extends Thread{

	/**
	 * Referencia a la logica asociada al timer controlador de la bomba
	 */
	protected Logica MiLogica;
	/**
	 * referencia a la gui
	 */
	protected GUI miGUI;
	/**
	 * control auxiliar para colocar una bomba
	 */
	protected volatile boolean stop = false;

	/**
	 * construye un nuevo timer de bomba
	 * @param l logica asociada
	 * @param g gui asociada
	 */
	public BombaThread(Logica l, GUI g)
	{
		MiLogica = l;
		miGUI = g;		
	}

	
	public void run()
	{
		while(!stop){
			if(!miGUI.isLockedBomba()){
				MiLogica.getBombermanTablero().colocarBomba();
				miGUI.toggleBomba();
			}
		}
	}
	
	/**
	 * detiene el thread de bomba
	 */
	public void destroy() {
		this.interrupt();
		stop = true;
	}

	/**
	 * devuelve la gui asociada
	 * @return gui G
	 */
	public GUI getGUI()
	{
		return miGUI;
	}
	
}
