package Personaje;
import Mapa.*;
import java.util.*;
import Graficas.*;

/**
 * 
 * Clase logica de la Bomba
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class Bomba {
	/**
	 * Radio de la bomba
	 */
	protected int longitud;
	/**
	 * Referencia al bomberman dueño de la bomba
	 */
	protected Bomberman MiB;
	/**
	 * Grafica de la bomba
	 */
	protected BombaGrafica BMBGrafica;
	/**
	 * boolean para controlar si se puede o no colocar otra bomba
	 */
	

	/**
	 * Crea una nueva bomba
	 */
	public Bomba(int l , Bomberman bb) {
		longitud = l;
		MiB = bb;
		BMBGrafica = new BombaGrafica(this);
	}

	/**
	 * retorna el bomberman asignado a esta bomba
	 * @return Bomberman B
	 */
	public Bomberman getBomberman()
	{
		return MiB;
	}
	
	/**
	 * retorna la grafica de la bomba
	 * @return BombaGrafica BG
	 */
	public BombaGrafica getBombaGrafica()
	{
		return BMBGrafica;
	}

	/**
	 * retorna el radio de explosion
	 * @return int r
	 */
	public int getLong() {
		return longitud;
	}

	/**
	 * setea el nuevo rango de explosion
	 * @param i int radio
	 */
	public void setLong(int i) {
		longitud = i;
	}
 
	
	/**
	 * setea el bomberman asociado a esta bomba
	 * @param b bomberman
	 */
	public void setBomberman(Bomberman b)
	{
		MiB = b;
	}
	
}