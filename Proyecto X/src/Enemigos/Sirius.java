package Enemigos;
import Graficas.*;

import java.util.*;

import Mapa.Casilla;

/**
 * 
 * Clase logica enemigo Sirius
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class Sirius extends Enemigo {

	/**
	 * Constructor, inicializa el enemigo con 50 de puntaje y 3 de velocidad.
	 *
	 */
	public Sirius() {
		super(50,3);
		super.EnemyGraphics = new SiriusGrafica(3);
	}


	public int getPuntaje() {
		return super.getPuntaje();
	}

	
	public void Mover(int indice)
	{
		
	}
	
	public boolean puedeMover(int indice)
	{
		return false;
	}
		
	

	
	public void Muere() {
		
	}

}