package Enemigos;
import Graficas.*;
import java.util.*;
import Mapa.Casilla;

/**
 * 
 * Clase logica enemigo Altair
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class Altair extends Enemigo {
	/**
	 * Constructor, inicializa el enemigo con 20 de puntaje y 1 de velocidad
	 */
	public Altair() {
		super(20, 1);
		super.EnemyGraphics = new AltairGrafica(1);
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