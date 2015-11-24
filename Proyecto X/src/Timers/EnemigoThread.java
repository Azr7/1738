package Timers;
import Enemigos.*;
import Graficas.*;

import java.util.Random;


/**
 * 
 * Clase Thread del enemigo, controla los timers del enemigo
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class EnemigoThread extends Thread{
	/**
	 * random utilizado para realizar los movimientos aleatoriamente
	 */
	protected Random ran = new Random();
	/**
	 * referencia al enemigo controlado por este hilo
	 */
	protected Enemigo Ene;
	/**
	 * referencia a la grafica del enemigo, controlada por este hilo
	 */
	protected EnemigoGrafica EG;
	/**
	 * boolean puedeMover, el cual controla si puede o no moverse
	 */
	protected volatile boolean pM = true;
	
	/**
	 * crea un nuevo hilo para un enemigo
	 * @param eg EnemigoGrafica
	 * @param ene Enemigo
	 */
	public EnemigoThread(EnemigoGrafica eg, Enemigo ene)
	{
		EG = eg;
		Ene = ene;
		EG.setEnemigoThread(this);
		start();
	}


	public void run()
	{
		while(pM && Ene.isAlive()) // Puede Mover
		{
			int x = ran.nextInt(4);
			if(Ene.puedeMover(x))
			{			
				EG.movimientoGrafico(x);
				Ene.Mover(x);
			}
		}
	}

	
	/**
	 * mata al enemigo
	 */
	public void toggleDeath()
	{
		this.stop();
		pM = false;
		Ene.getCelda().setMalo(null);
		Ene.getTablero().getLogic().getGUI().quitarEnemigo(EG.getIndice());
	}
}



