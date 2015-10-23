package PowerUps;
import Personaje.*;
import java.util.*;
import Graficas.*;


/**
 * 
 * Clase Logica powerUp Abstracta
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public abstract class PowerUp {
	/**
	 * almacena el puntaje del powerup
	 */
	protected int P;
	/**
	 * almacena la grafica del power up, esto es, un icono
	 */
	protected PowerUpGrafica GPU;
	
	/**
	 * Crea el power up con su respectivo score
	 * @param p
	 */
	public PowerUp(int p) {
		P = p;
	}

	/**
	 * retorna el score del powerup
	 * @return int puntaje
	 */
	public int getPuntaje()
	{
		return P;
	}

	/**
	 * retorna la grafica asociada al powerup
	 * @return PowerUpGrafica G
	 */
	public PowerUpGrafica getGrafica()
	{
		return GPU;
	}
	
	/**
	 * Buff al bomberman , segun el powerup agarrado
	 * @param b bomberman
	 */
	public abstract void Buff(Bomberman b);

}