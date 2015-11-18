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
		super(50,30); // 30 es velocidad del timer del sleep
		super.EnemyGraphics = new SiriusGrafica(30);
	}


	public int getPuntaje() {
		return super.getPuntaje();
	}

	private boolean Minimo(int xr, int yr, int indice)
	{
		int xB = MiTab.getBomber().getCelda().getX();
		int yB = MiTab.getBomber().getCelda().getY();
	
		int difOldX = Math.abs(xr - xB);
		int difOldY = Math.abs(yr - yB);
		if(indice == 0)
		{
			yr--;
		}
		else {
			if(indice == 1)
			{
				yr++;
			}
			else 
			{
				if(indice == 2)
				{
					xr--;
				}
				else if(indice == 3)
				{
					xr++;
				}
			}
		}

		int difNewX = Math.abs(xr - xB);
		int difNewY = Math.abs(yr - yB);
		
		return (difNewX < difOldX || difNewY < difOldY);
	}

	public boolean puedeMover(int indice)
	{
		Casilla[][] Matriz = MiTab.getMatriz();
		int xActual = MiCasilla.getX();
		int yActual = MiCasilla.getY();
		int nextX;
		int nextY;

		if(Minimo(xActual, yActual, indice))
		{
			// Chequeamos si a partir de la pos actual, podemos movernos hacia donde nos marca dir
			if(indice == 0) // Arriba
			{
				nextX = xActual;
				nextY = yActual - 1;
				if(nextY > 0)
				{
					if(Matriz[nextX][nextY] != null)
					{
						if(Matriz[nextX][nextY].getPared() == null)
						{
							return true;
						}
					}
				}
			}
			else if(indice == 1) // Abajo
			{
				nextX = xActual;
				nextY = yActual + 1;
				if(nextY < Alto)
				{
					if(Matriz[nextX][nextY] != null)
					{
						if(Matriz[nextX][nextY].getPared() == null)
						{
							return true;
						}
					}
				}
			}
			else if(indice == 2) // Izq
			{
				nextX = xActual - 1;
				nextY = yActual;
				if(nextX > 0)
				{
					if(Matriz[nextX][nextY] != null)
					{
						if(Matriz[nextX][nextY].getPared() == null)
						{
							return true;
						}
					}
				}
			}
			else if(indice == 3) // Der
			{
				nextX = xActual + 1;
				nextY = yActual;
				if(nextX < Ancho)
				{
					if(Matriz[nextX][nextY] != null)
					{
						if(Matriz[nextX][nextY].getPared() == null)
						{
							return true;
						}
					}
				}
			}
		}

		return false;
	}


	public void Mover(int indice) {
		Casilla[][] Matriz = MiTab.getMatriz();
		int xActual = MiCasilla.getX();
		int yActual = MiCasilla.getY();
		Casilla aux;
		int nextX = -1;
		int nextY = -1;

		// Este metodo se ejecuta, si puedeMover da true, por lo cual no va a haber problemas si cae en un lugar nulo de la matriz
		// simplemente actualizamos los corrimientos
		if(indice == 0) // Arriba
		{
			nextX = xActual;
			nextY = yActual - 1;
		}
		else if(indice == 1) // Abajo
		{
			nextX = xActual;
			nextY = yActual + 1;
		}
		else if(indice == 2) // Izq
		{
			nextX = xActual - 1;
			nextY = yActual;
		}
		else if(indice == 3) // Der
		{
			nextX = xActual + 1;
			nextY = yActual;
		}

		// Como cambio de casilla, asignamos una nueva casilla al malo, y lo borramos de la anterior
		MiCasilla.setMalo(null);
		aux = Matriz[nextX][nextY];
		aux.setMalo(this);
		MiCasilla = aux;
	}




	public void Muere() {

	}

}