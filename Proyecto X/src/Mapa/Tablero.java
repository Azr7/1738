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
	 * Crea un nuevo tablero e inicializa la matriz de movimientos
	 */
	public Tablero() {
		MiB = null;
		Enemigos = new Enemigo[6];
		MiLogica = null;

		Rugulos Rs1 = new Rugulos(); // Faltan los demas enemigos


		Matriz = new Casilla[31][31];
		int porcentaje = 31*31;
		Disponibles = new ArrayList<Casilla>();
		Paredes = new ArrayList<Casilla>();

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
		porcentaje = (31*31 - porcentaje) / 2; //50%
		Random ran = new Random();
		Random hayPowerUp = new Random();
		Random Type = new Random();
		int cantSpeedUp = 4;
		int cantBombality = 3;
		int cantFatality = 3;
		int cantMasacrality = 1;
		boolean LLeno = false;
		int control = 0;
		int iRandom;
		while(control < porcentaje)
		{
			iRandom = ran.nextInt(Disponibles.size());
			Disponibles.get(iRandom).setPared(new Pared());
			Paredes.add(Disponibles.get(iRandom));
			Disponibles.remove(iRandom);				
			control++;
		}

		int w = ran.nextInt(Disponibles.size()); 
		Disponibles.get(w).setMalo(Rs1);
		Rs1.setCelda(Disponibles.get(w));
		Rs1.setTablero(this);
		Disponibles.remove(w);



		MiB = new Bomberman();
		Matriz[1][1].setBomber(MiB);
		MiB.setCelda(Matriz[1][1]);
		MiB.setTablero(this);


		InicializarPowerUps();
	}

	/**
	 * Inicializa los powerUps en las casillas con pared no nula
	 */
	private void InicializarPowerUps()
	{
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

}