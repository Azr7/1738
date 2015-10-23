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
	protected boolean pM;
	/**
	 * boolean que controla si aun existe el enemigo
	 */
	protected boolean muerto = false;

	/**
	 * crea un nuevo hilo para un enemigo
	 * @param eg EnemigoGrafica
	 * @param ene Enemigo
	 */
	public EnemigoThread(EnemigoGrafica eg, Enemigo ene)
	{
		EG = eg;
		Ene = ene;
		pM = true;
		EG.setEnemigoThread(this);
	}


	public void run()
	{
		while(pMover())
		{
			int x = ran.nextInt(4);
			if(Ene.puedeMover(x) && !muerto)
			{			
				EG.movimientoGrafico(x);
				Ene.Mover(x);
			}
		}
	}

	/**
	 * consulta que determina si el enemigo puede moverse o no
	 * @return boolean v o f
	 */
	public boolean pMover()
	{
		return pM;
	}

	/**
	 * setea si el enemigo se puede o no mover
	 * @param b boolean
	 */
	public void setpMover(boolean b)
	{
		pM = b;
	}

	/**
	 * detiene el hilo
	 */
	public void destroy()
	{
		this.interrupt();
		pM = false;
	}

	/**
	 * mata al enemigo
	 */
	public void toggleDeath()
	{
		muerto = !muerto;
	}
}



