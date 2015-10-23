package Enemigos;
import java.util.*;
import Graficas.*;
import Mapa.Casilla;

/**
 * 
 * Clase logica enemigo Rugulos
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class Rugulos extends Enemigo {

	/**
	 * Constructor, inicializa el enemigo con 15 de puntaje y 1 de velocidad
	 */
	public Rugulos() {
		super(15,1);
		super.EnemyGraphics = new RugulosGrafica(1);
	}

	public int getPuntaje() {
		return super.getPuntaje();
	}



	public boolean puedeMover(int indice)
	{
		Casilla[][] Matriz = MiTab.getMatriz();
		int xActual = MiCasilla.getX();
		int yActual = MiCasilla.getY();
		int nextX;
		int nextY;


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

		return false;
	}


	public void Mover(int indice) {
		Casilla[][] Matriz = MiTab.getMatriz();
		int xActual = MiCasilla.getX();
		int yActual = MiCasilla.getY();
		Casilla aux;
		int nextX = -1;
		int nextY = -1;


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
		
		MiCasilla.setMalo(null);
		aux = Matriz[nextX][nextY];
		aux.setMalo(this);
		MiCasilla = aux;
	}




	public void Muere()
	{
		
	}

}