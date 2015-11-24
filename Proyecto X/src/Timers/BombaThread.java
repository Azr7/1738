package Timers;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GUIandLOGIC.*;
import Graficas.FuegoGrafica;
import Mapa.Casilla;
import Personaje.*;
import Graficas.*;

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

	protected int Mv = 16;	
	protected Bomba B;
	protected BombaGrafica BG;

	/**
	 * construye un nuevo timer de bomba
	 * @param l logica asociada
	 * @param g gui asociada
	 */
	public BombaThread(Logica l, GUI g, Bomba b, BombaGrafica gb)
	{
		MiLogica = l;
		miGUI = g;
		B = b;
		BG = gb;
		start();
	}


	public void run()
	{		
		try{
			int x1 = B.getBomberman().getCelda().getX();
			int y1 = B.getBomberman().getCelda().getY();
			BG.colocarBombaGrafica(x1, y1);
			sleep(3000);
			BG.colocarBombaGrafica2(x1, y1);
			ArrayList<Integer> L = new ArrayList<Integer>();
			boolean Muere = B.getBomberman().explotarBombaAux(B,L);
			BG.recibirCoordenadasFuego(L);
			if(Muere) B.getBomberman().Muere();
			B.getBomberman().incBombas(); 
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
