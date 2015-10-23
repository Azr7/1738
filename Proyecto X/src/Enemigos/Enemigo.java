package Enemigos;
import Graficas.*;
import GUIandLOGIC.*;
import Mapa.*;
import Personaje.*;
import PowerUps.*;

import java.util.*;

/**
 * 
 * Clase abstracta logica Enemigo
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public abstract class Enemigo {

	/**
	 * Puntaje del enemigo, al morir
	 */
	protected int P;

	/**
	 * Velocidad de movimiento del enemigo
	 */
	protected int Vel;

	/**
	 *	Referencia al tablero
	 */
	protected Tablero MiTab;
	
	/**
	 * Referencia a la casilla actual del enemigo
	 */
	protected Casilla MiCasilla;
	
	/**
	 * Tamaño del tablero grafico
	 */
	protected int Alto = 31;
	protected int Ancho = 31;

	/**
	 * Graficas del enemigo
	 */
	protected EnemigoGrafica EnemyGraphics;	

	/**
	 * constructor de clase enemigo
	 * @param p puntaje
	 * @param v  velocidad
	 */
	protected Enemigo(int p, int v) {
		P = p;
		Vel = v;
		MiCasilla = null;
		MiTab = null;
		EnemyGraphics = null;
	}

	/**
	 * Retorna la grafica del enemigo que recibe el mensaje
	 * @return EnemigoGrafica g
	 */
	public EnemigoGrafica getEnemyGraphics()
	{
		return EnemyGraphics;
	}
	
	/**
	 * Setea una nueva celda al enemigo que recibe el mensaje
	 * @param c casilla nueva
	 */
	public void setCelda(Casilla c) {
		MiCasilla = c;		
	}

	/**
	 * devuelve la casilla actual donde el enemigo se encuentra
	 * @return casilla c actual
	 */
	public Casilla getCelda() {
		return MiCasilla;
	}

	/**
	 * retorna el tablero asociado al enemigo
	 * @return Tablero t
	 */
	public Tablero getTablero() {
		return MiTab;
	}

	/**
	 * Relaciona el tablero al enemigo
	 * @param T
	 */
	public void setTablero(Tablero T)
	{
		MiTab = T;
	}

	/**
	 * Devuelve el puntaje del enemigo que recibe el mensaje
	 * @return int puntaje
	 */
	public int getPuntaje()
	{
		return P;
	}

	/**
	 * Muerte del enemigo
	 */
	public abstract void Muere();

	/**
	 * Realiza un movimiento en la direccion especificada
	 * @param indice int direccion
	 */
	public abstract void Mover(int indice);
	
	/**
	 * Retorna V o F si puede o no moverse a la posicion indicada
	 * @param indice int 
	 * @return boolean verdadero o falso
	 */
	public abstract boolean puedeMover(int indice);


}