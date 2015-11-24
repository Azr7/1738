package GUIandLOGIC;
import Mapa.*;
import Personaje.*;
import java.util.*;

/**
 * 
 * Clase Logica
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class Logica {

	/**
	 * Referencia a la GUI
	 */
	protected GUI GUI;
	/**
	 * referencia al tablero
	 */
	protected Tablero MiTab;

	/**
	 * Crea la logica
	 */
	public Logica() {
		GUI = null;
		MiTab = null;
	}


	/**
	 * Inicializa el tablero, esto es, los rompibles, irrompibles, enemigos, powerups, etc.
	 */
	public void InicializarTablero() {
		MiTab = new Tablero();
		setTablero(MiTab);
		MiTab.setLogica(this);		
	}

	/**
	 * retorno el tablero asociado
	 * @return tablero T
	 */
	public Tablero getTablero() {
		return MiTab;
	}

	/**
	 * Setea la gui asociada a la logica
	 * @param g GUI
	 */
	public void setGUI(GUI g)
	{
		GUI = g;		
	}
	/**
	 * setea el tablero asociado a la logica
	 * @param T tablero
	 */
	public void setTablero(Tablero T)
	{
		MiTab = T;
	}

	/**
	 * Devuelve la matriz del tablero
	 * @return Matriz de casilla
	 */
	public Casilla[][] getMatrizTablero()
	{
		return MiTab.getMatriz();
	}

	/**
	 * Devuelve el bomberman del tablero
	 * @return Bomberman B
	 */
	public Bomberman getBombermanTablero()
	{
		return MiTab.getBomber();
	}
	/**
	 * Retorna la gui asociada a la clase logica
	 * @return g GUI
	 */
	public GUI getGUI()
	{
		return GUI;
	}
}