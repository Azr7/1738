package Mapa;
import Personaje.*;
import PowerUps.*;
import Enemigos.*;
import GUIandLOGIC.*;

import java.util.*;


/**
 * 
 * Clase Tablero, logica del juego
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class Tablero {
	/**
	 * Referencia al Bomberman
	 */
	protected Bomberman MiB;
	/**
	 * Arreglo de enemigos
	 */
	protected Enemigo[] Enemigos;
	/**
	 * Matriz de casillas donde se aplica la logica de movimientos y colisiones
	 */
	protected Casilla Matriz[][];
	/**
	 * Referencia a la logica
	 */
	protected Logica MiLogica;
	/**
	 * ArrayList de casillas disponibles luego de iniciar la matriz con IRROMPIBLES
	 */
	protected ArrayList<Casilla> Disponibles;
	/**
	 * ArrayList de casillas CON PARED
	 */
	protected ArrayList<Casilla> Paredes;
	/**
	 * Objetivos del juego
	 */
	protected int Objetivos;

	/**
	 * Crea un nuevo tablero e inicializa la matriz de movimientos
	 */
	public Tablero() {
		Objetivos = 0;
		MiB = null;
		Enemigos = new Enemigo[6];
		MiLogica = null;

		Matriz = new Casilla[31][31];
		int porcentaje = 31*31;
		Disponibles = new ArrayList<Casilla>(); // Lugares donde se van a generar paredes
		Paredes = new ArrayList<Casilla>(); // Paredes generadas

		for(int i=0; i<31; i++)
		{
			for(int j=0; j<31; j++)
			{
				if(i == 0 || j == 0 || i==30 || j==30)
				{
					Matriz[i][j] = null; // Pared Indestructible
					porcentaje--;
				}

				else if(i % 2 == 0 && j % 2 == 0) 
				{
					Matriz[i][j] = null; // Pared Indestructible
					porcentaje--;
				}
				else
				{
					Matriz[i][j] = new Casilla(i,j); // Depende de pared, si es o no caminable.
					if(i != 1 && j != 1)
					{
						if(i != 2 && j != 1 && i!=1 && j!=2) 
						{
							Disponibles.add(Matriz[i][j]);
						}
					}
				}

			}
		}
		// Se genera la matriz logica

		// se calcula el lugar que falta rellenar, se divide en 2, y esa cantidad es la que sera ocupada por rompibles
		porcentaje = (31*31 - porcentaje) / 2; //50%
		Random ran = new Random();
		int control = 0;
		int iRandom;
		while(control < porcentaje)
		{
			// Se generan las paredes rompibles y se agregan a la lista de paredes
			iRandom = ran.nextInt(Disponibles.size());
			Disponibles.get(iRandom).setPared(new Pared()); // << Si esta instruccion se saca
			Paredes.add(Disponibles.get(iRandom));
			Disponibles.remove(iRandom);				
			control++;
			Objetivos++;
		}
		
		// Solamente se aplica un malo a todo el tablero. se debe repetir para todos los enemigos este procedimiento
		int w;
		for(int i=0; i<3; i++)
		{
			w = ran.nextInt(Disponibles.size());
			Rugulos RS = new Rugulos();
			Disponibles.get(w).setMalo(RS);
			RS.setCelda(Disponibles.get(w));
			RS.setTablero(this);
			Disponibles.remove(w);
			Objetivos++;
		}
		// ----------------------------------
		
		for(int i=0; i<2; i++)
		{
			w = ran.nextInt(Disponibles.size());
			Altair AR = new Altair();
			Disponibles.get(w).setMalo(AR);
			AR.setCelda(Disponibles.get(w));
			AR.setTablero(this);
			Disponibles.remove(w);
			Objetivos++;
		}
		// ----------------------------------
		w = ran.nextInt(Disponibles.size());
		Sirius SS = new Sirius();
		Disponibles.get(w).setMalo(SS);
		SS.setCelda(Disponibles.get(w));
		SS.setTablero(this);
		Disponibles.remove(w);
		Objetivos++;
		// ----------------------------------
		

		// Se genera el bomberman, se relaciona al tablero, y se aplica a la matriz
		MiB = new Bomberman();
		Matriz[1][1].setBomber(MiB);
		MiB.setCelda(Matriz[1][1]);
		MiB.setTablero(this);

		// Se inicializan los powerups
		InicializarPowerUps();
		
	}

	/**
	 * Inicializa los powerUps en las casillas con pared no nula
	 */
	private void InicializarPowerUps()
	{
		// Tener en cuenta que los PowerUp's se generan bajo un rompible.
		// al momento de iniciar el juego (o si se saca la instruccion de generar pared declarada arriba) ,
		// no se verá ninguno, dado que desde GUI los PowerUps comienzan en la grafica como invisibles
		Random ran = new Random();
		int iRandom;		
		for(int i=0; i<4; i++)
		{
			// 4 Speedups
			iRandom = ran.nextInt(Paredes.size());
			Paredes.get(iRandom).setPowerUp(new SpeedUp());
			Paredes.remove(iRandom);
		}
		for(int i=0; i<3; i++)
		{
			// 3 fatality
			iRandom = ran.nextInt(Paredes.size());
			Paredes.get(iRandom).setPowerUp(new Fatality());
			Paredes.remove(iRandom);
		}
		for(int i=0; i<3; i++)
		{
			// 3 bombality
			iRandom = ran.nextInt(Paredes.size());
			Paredes.get(iRandom).setPowerUp(new Bombality());
			Paredes.remove(iRandom);
		}

		// el masacrality
		iRandom = ran.nextInt(Paredes.size());
		Paredes.get(iRandom).setPowerUp(new Masacrality());
		Paredes.remove(iRandom);
	}

	/**
	 * setea la logica al tablero
	 * @param L Logica
	 */
	public void setLogica(Logica L){
		MiLogica = L;
	}

	/**
	 * retorna la logica asignada al tablero
	 * @return Logica L
	 */
	public Logica getLogic() {
		return MiLogica;
	}

	/**
	 * retorna la matriz de casilleros 
	 * @return matriz Casilla
	 */
	public Casilla[][] getMatriz()
	{
		return Matriz;
	}

	/**
	 * retorna el bomberman asociado al tablero
	 * @return bomberman B
	 *  
	 */
	public Bomberman getBomber()
	{
		return MiB;
	}
	
	/**
	 * cantidad de objetivos del juego (a romper)
	 * @return int cantidad
	 */
	public int getCantidadObjetivos(){return Objetivos;}

}