package Mapa;
import Personaje.*;
import PowerUps.*;
import Enemigos.*;
import java.util.*;

/**
 * 
 * Clase Casilla
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class Casilla {
	/**
	 * Referencia al bomberman asociado a la casilla actual, puede o no ser nulo
	 */
	protected Bomberman MiB;
	/**
	 * Rerefencia al enemigo asociado a la casilla actual, puede o no ser nulo
	 */
	protected Enemigo Enemigo;
	/**
	 * Referencia a una pared, puede o no ser nula
	 */
	protected Pared MiPared;
	/**
	 * Referencia al powerUp,puede o no ser nula
	 */
	protected PowerUp MiPowerUp;
	/**
	 * Enteros i,j que marcan la posicion x,y de la casilla
	 */
	protected int i,j;
	/**
	 * puntaje de las paredes
	 */
	protected int puntajePared = 10;


	// Si casilla es nula, es Indestruible.
	// Sino, si pared NO es nula -> Es destruible
	// 		sino, es Caminable.

	/**
	 * Crea una nueva casilla en la posicion x e y recibidas
	 * @param x int pos
	 * @param y int pos
	 */
	public Casilla(int x, int y) {
		MiB = null;
		Enemigo = null;
		MiPowerUp = null;
		i = x;
		j = y;
	}

	/**
	 * devuelve la pared asociada a la casilla
	 * @return Pared P
	 */
	public Pared getPared() {
		return MiPared;
	}
	/**
	 * setea una nueva pared a la casilla
	 * @param P pared
	 */
	public void setPared(Pared P)
	{
		MiPared = P;
	}
	/**
	 * Setea un powerup a la casilla
	 * @param Pw powerup
	 */
	public void setPowerUp(PowerUp Pw)
	{
		MiPowerUp = Pw;
	}

	/**
	 * devuelve el power up asociado a la casilla actual
	 * @return PowerUp P
	 */
	public PowerUp getPowerUp()
	{
		return MiPowerUp;
	}
	
	/**
	 * chequea si en la casilla actual hay un enemigo
	 * @return boolean verdadero o falso
	 */
	public boolean hayMalo() {
		return Enemigo != null;
	}

	/**
	 * chequea si en la casilla actual, está bomberman 
	 * @return boolean verdadero o falso
	 */
	public boolean hayBomberman() {
		return MiB != null;
	}

	/**
	 * setea un nuevo malo a la casilla actual
	 * Si bomberman está en la casilla, hay colision y bomberman muere
	 * @param e enemigo
	 */
	public void setMalo(Enemigo e) {
		Enemigo = e;
		if(hayBomberman() && MiB != null)
		{
			if(MiB.vive()) MiB.Muere();
		}
	}

	/**
	 * Setea a bomberman a la casilla actual
	 * Si hay un malo en la misma, hay colision y bomberman muere
	 * @param b bomberman
	 */
	public void setBomber(Bomberman b) {
		MiB = b;
		if(hayMalo() && MiB != null)
		{
			if(MiB.vive())MiB.Muere();
		}
	}
	
	/**
	 * Retorna el objeto tipo Enemigo, en caso de que este exista en la casilla actual
	 * @return Enemigo e
	 */
	public Enemigo getMalo()
	{
		return Enemigo;
	}

	/**
	 * Retorna el objeto tipo Bomberman, en caso de que este exista en la casilla actual
	 * @return Bomberman b
	 */
	public Bomberman getBomber()
	{
		return MiB;
	}

	/**
	 * consulta para la coordenada x de la casilla
	 * @return x pos 
	 */
	public int getX(){return i;}
	/**
	 * consulta para la coordenada y de la casilla
	 * @return y pos
	 */
	public int getY(){return j;}
	
	public int getPuntaje(){return puntajePared;}

}