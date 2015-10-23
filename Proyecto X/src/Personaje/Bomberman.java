package Personaje;
import Graficas.*;
import Mapa.*;

import java.awt.event.KeyEvent;
import java.util.*;

import javax.swing.JLabel;

/**
 * 
 * Clase logica del Bomberman
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class Bomberman {

	/**
	 * cantidad de bombas que puede colocar en simultaneo
	 */
	protected int CantBomba;
	/**
	 * referencia al tablero
	 */
	protected Tablero MiTab;
	/**
	 * referencia a la casilla donde el bomberman esta parado
	 */
	protected Casilla MiCasilla;
	/**
	 * referencia a su bomba
	 */
	protected Bomba MiBomba;
	/**
	 * ancho del tablero
	 */
	protected int Ancho = 31;
	/**
	 * alto del tablero
	 */
	protected int Alto = 31;
	/**
	 * grafica del bomberman
	 */
	protected BombermanGrafica BG;
	/**
	 * boolean que controla su muerte
	 */
	protected boolean Dead;
	/**
	 * tamaño de la casilla en pixeles (es cuadrada)
	 */
	protected int MovPix = 16;
	/**
	 * boolean que controla si puede o no traspasar las paredes rompibles
	 */
	protected boolean traspasa;

	/**
	 * crea un nuevo bomberman
	 */
	public Bomberman() {
		CantBomba = 1;
		MiTab = null;
		MiCasilla = null;
		Dead = false;
		MiBomba = new Bomba();
		MiBomba.setBomberman(this);
		traspasa = false;
	}

	/**
	 * Retorna la bomba del bomberman
	 * @return bomba B
	 */
	public Bomba getBomba()
	{
		return MiBomba;
	}

	/**
	 * consulta que retorna verdadero si el bomberman puede moverse en la direccion recibida
	 * @param indice int direccion
	 * @return boolean verdadero o falso
	 */
	public boolean puedeMover(int indice)
	{
		Casilla[][] Matriz = MiTab.getMatriz();
		int xActual = MiCasilla.getX();
		int yActual = MiCasilla.getY();
		int nextX;
		int nextY;

		// Simplemente se fija si se puede realizar el movimiento solicitado
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
					else if(traspasa) return true;
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
					else if(traspasa) return true;
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
					else if(traspasa) return true;
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
					else if(traspasa) return true;
				}
			}
		}

		return false;
	}

	/**
	 * Metodo principal de movimiento, donde se controla si puede o no moverse a esa direccion, 
	 * en caso afirmativo, se mueve tanto grafica, como logicamente.
	 * @param dir entera
	 */
	public void mover(int dir){
		switch (dir){
		// A partir de la direccion recibida, se chequea a donde se quiere mover, y nos fijamos si es posible
		case KeyEvent.VK_UP : //Arriba
			if(puedeMover(0))
			{
				BG.movimientoGrafico(0);
				this.MoverAux(0);
			}
			break;
		case KeyEvent.VK_DOWN : //Abajo
			if(puedeMover(1))
			{
				BG.movimientoGrafico(1);
				this.MoverAux(1);
			}
			break;
		case KeyEvent.VK_LEFT : //Izquierda
			if(puedeMover(2))
			{
				BG.movimientoGrafico(2);
				this.MoverAux(2);
			}
			break;
		case KeyEvent.VK_RIGHT : //Derecha
			if(puedeMover(3))
			{
				BG.movimientoGrafico(3);
				this.MoverAux(3);
			}
			break;
		}
	}


	/**
	 * Metodo auxiliar que realiza el movimiento, y reasigna una nueva casilla actual
	 * @param indice direccion
	 */
	private void MoverAux(int indice) {
		Casilla[][] Matriz = MiTab.getMatriz();
		int xActual = MiCasilla.getX();
		int yActual = MiCasilla.getY();
		Casilla aux;
		int nextX = -1;
		int nextY = -1;

		// Si entra en este metodo, es porque "puedeMover" es verdadero, entonces se hacen los corrimientos
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
		// Al movernos de casilla, tenemos que setear una nueva, y terminar la relacion con la anterior
		MiCasilla.setBomber(null);
		aux = Matriz[nextX][nextY];
		aux.setBomber(this);
		MiCasilla = aux;
		// Si la casilla nueva tiene un powerup, entonces lo agarramos. Enviamos mensaje al PowerUp
		if(MiCasilla.getPowerUp() != null)
		{
			MiCasilla.getPowerUp().Buff(this);
			MiCasilla.setPowerUp(null);
		}
	}

	/**
	 * setea una nueva casilla al bomberman
	 * @param c casilla
	 */
	public void setCelda(Casilla c) {
		MiCasilla = c;
	}

	/**
	 * retorna la casilla actual donde el bomberman está parado
	 * @return casilla c
	 */
	public Casilla getCelda() {
		return MiCasilla;
	}

	/**
	 * Muerte logica y graficamente del bomberman, ademas de su thread controlador
	 */
	public void Muere() {
		// Efecto grafico, gameover, y corte del thread
		Dead = true;
		BG.getBT().getGUI().BombermanMuere();
		BG.muereGrafico();
		BG.getBT().destroy();
	}

	/**
	 * Buff del masacrality
	 * - Nota : Falta completar -
	 */
	public void Masacrality() {

	}

	/**
	 * Buff del bombality
	 * - Nota : Falta completar -
	 */
	public void Bombality() {


	}

	/**
	 * Buff del fatality
	 * 
	 */
	public void Fatality() {
		MiBomba.setLong(MiBomba.getLong()*2);
	}

	/**
	 * Buff del speedUp
	 */
	public void SpeedUp() {
		BG.setVelocidadSleep(BG.getVelocidadSleep() / 2);
	}

	/**
	 * retorna el tablero asociado al bomberman
	 * @return Tablero T
	 */
	public Tablero getTablero() {
		return MiTab;
	}

	/**
	 * Coloca una nueva bomba
	 * - NOTA : Falta hacer controles en caso de que se consiga un bombality - 
	 */
	public void colocarBomba() {
		if(MiBomba.noExploto())
		{	
			MiBomba.Explotar();
			explotarBombaAux();
		}
	}

	/**
	 * Metodo auxiliar para la explosion de bomba
	 * Donde se realizan las destrucciones y graficas de explosion
	 */
	private void explotarBombaAux(){
		int radio = MiBomba.getLong();
		Casilla[][] Matriz = MiTab.getMatriz();
		int xActual = MiBomba.getBombaGrafica().getLabelBomba().getX() / MovPix;
		int yActual = MiBomba.getBombaGrafica().getLabelBomba().getY() / MovPix;

		ArrayList<Integer> aEliminar = new ArrayList<Integer>();
		
		
		// Se controla los lugares donde debe explotar la bomba,
		// A su vez, si abajo de un rompible explotado, existia un powerup, entonces este se hace visible.
		
		// Control arriba.
		for(int i=0; i<=radio; i++)
		{
			if(yActual-i < 1) break;
			else
			{	
				if(Matriz[xActual][yActual - i] == null) break;
				else 
				{	
					if(Matriz[xActual][yActual - i].getPared() != null)
					{
						Matriz[xActual][yActual - i].setPared(null);// Rompe
						if(Matriz[xActual][yActual - i].getPowerUp() != null)
							Matriz[xActual][yActual - i].getPowerUp().getGrafica().visible();
					}
				}
				aEliminar.add(xActual);
				aEliminar.add(yActual - i);
			}
		}

		// Control abajo.
		for(int i=1; i<=radio; i++)
		{
			if(yActual+i > Alto-1) break;
			else
			{
				if(Matriz[xActual][yActual + i] == null) break;
				else 
				{	
					if(Matriz[xActual][yActual + i].getPared() != null)

					{
						Matriz[xActual][yActual + i].setPared(null); // Rompe
						if(Matriz[xActual][yActual + i].getPowerUp() != null)
							Matriz[xActual][yActual + i].getPowerUp().getGrafica().visible();
					}
				}
				aEliminar.add(xActual);
				aEliminar.add(yActual + i);
			}
		}

		// Control Izq.
		for(int i=1; i<=radio; i++)
		{
			if(xActual - i < 1) break;
			else
			{
				if(Matriz[xActual - i][yActual] == null) break;
				else
				{
					if(Matriz[xActual - i][yActual].getPared() != null)

					{
						Matriz[xActual - i][yActual].setPared(null); // Rompe
						if(Matriz[xActual - i][yActual].getPowerUp() != null)
							Matriz[xActual - i][yActual].getPowerUp().getGrafica().visible();
					}
				}
				aEliminar.add(xActual - i);
				aEliminar.add(yActual);
			}
		}

		// Control Der.
		for(int i=1; i<=radio; i++)
		{
			if(xActual + i > Ancho-1) break;
			else
			{
				if(Matriz[xActual + i][yActual] == null) break;
				else 
				{
					if(Matriz[xActual + i][yActual].getPared() != null)
					{
						Matriz[xActual  + i][yActual].setPared(null); // Rompe
						if(Matriz[xActual + i][yActual].getPowerUp() != null)
							Matriz[xActual + i][yActual].getPowerUp().getGrafica().visible();
					}
				}
				aEliminar.add(xActual + i);
				aEliminar.add(yActual);

			}
		}

		// Metodo aux que hace el efecto del fuego en las coordenadas donde la bomba explota
		MiBomba.getBombaGrafica().recibirCoordenadasFuego(aEliminar);

		int xBomber = MiCasilla.getX();
		int yBomber = MiCasilla.getY();

		// Aca controlamos si el bomberman estaba en la zona de explosion
		// De ser verdadero, bomberman muere
		
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<=radio; j++)
			{
				if(i == 0) // Arriba.
				{
					if(Matriz[xActual][yActual - j] == null) break;
					if(xActual == xBomber && yActual - j == yBomber) 
					{ 
						Muere();
						break;
					}
				}
				else if(i == 1) // Abajo.
				{
					if(Matriz[xActual][yActual + j] == null) break;
					if(xActual == xBomber && yActual + j == yBomber) 
					{ 
						Muere();
						break;
					}
				}
				else if(i == 2) // Izq
				{
					if(Matriz[xActual - j][yActual] == null) break;
					if(xActual - j  == xBomber && yActual == yBomber) 
					{ 
						Muere();
						break;
					}
				}
				else if(i == 3) // Der
				{
					if(Matriz[xActual + j][yActual] == null) break;
					if(xActual + j  == xBomber && yActual == yBomber) 
					{ 
						Muere();
						break;
					}
				}
			}
		}
	}

	/**
	 * Se setea un nuevo tablero donde el bomberman se encuentra
	 * 
	 * @param T tablero
	 */
	public void setTablero(Tablero T)
	{
		MiTab = T;
	}

	/**
	 * Se setea una nueva grafica de bomberman
	 * @param zz bombermanGrafica
	 */
	public void setBomberGrafica(BombermanGrafica zz)
	{
		BG = zz;
	}

	/**
	 * se retorna la grafica asignada al personaje bomberman
	 * @return grafica bombermangrafica
	 */
	public BombermanGrafica getBombermanGrafica()
	{
		return BG;
	}

	/**
	 * controla si bomberman aun existe
	 * @return boolean verdadero o falso
	 */
	public boolean vive()
	{
		return !Dead;
	}

}