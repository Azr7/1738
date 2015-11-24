package Personaje;
import Graficas.*;
import Mapa.*;
import Reloj.RelojMasacrality;

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


	protected boolean MasacralityActivo;
	/**
	 * cantidad de bombas que puede colocar en simultaneo
	 */
	protected int cantidad;
	/**
	 * referencia al tablero
	 */
	protected Tablero MiTab;

	protected int radio;
	/**
	 * referencia a la casilla donde el bomberman esta parado
	 */
	protected Casilla MiCasilla;
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
	 * crea un nuevo bomberman
	 */
	public Bomberman() {
		cantidad = 1;
		radio = 1;
		MiTab = null;
		MiCasilla = null;
		Dead = false;
		MasacralityActivo = false;		
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
		if(!Dead){
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
						else if(MasacralityActivo) return true;
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
						else if(MasacralityActivo) return true;
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
						else if(MasacralityActivo) return true;
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
						else if(MasacralityActivo) return true;
					}
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
			MiTab.getLogic().getGUI().aumentarScore(MiCasilla.getPowerUp().getPuntaje());
			MiCasilla.setPowerUp(null);
		}
		if(MiCasilla.getMalo() != null)
		{
			Muere();
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
		if(!MasacralityActivo)
		{
			Dead = true;
			BG.muereGrafico();
			BG.getBT().destro();
		}
	}

	/**
	 * Buff del masacrality
	 * 
	 */
	public void Masacrality() {
		MasacralityActivo = true;
		RelojMasacrality RM = new RelojMasacrality(this);
	}
	public void desMasacrear()
	{
		MasacralityActivo = false;
	}

	/**
	 * Buff del bombality
	 * - Nota : Falta completar -
	 */
	public void Bombality() {
		cantidad++;
	}

	/**
	 * Buff del fatality
	 * 
	 */
	public void Fatality() {
		radio = radio*2;
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
		if((!Dead && cantidad>0) || MasacralityActivo){
			cantidad--;
			Bomba Aux = new Bomba(radio,this);			
		}
	}

	public void incBombas()
	{
		cantidad++;
	}

	/**
	 * Metodo auxiliar para la explosion de bomba
	 * Donde se realizan las destrucciones y graficas de explosion
	 */
	public boolean explotarBombaAux(Bomba MiBomba,ArrayList<Integer> aEliminar){
		int radio = MiBomba.getLong();
		Casilla[][] Matriz = MiTab.getMatriz();
		int xActual = MiBomba.getBombaGrafica().getLabelBomba().getX() / MovPix;
		int yActual = MiBomba.getBombaGrafica().getLabelBomba().getY() / MovPix;
		int aRomper = 0;
		boolean muere = false;

		// Aca controlamos si el bomberman estaba en la zona de explosion
		// De ser verdadero, bomberman muere

		int xBomber = MiCasilla.getX();
		int yBomber = MiCasilla.getY();	
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<=radio; j++)
			{
				if(i == 0) // Arriba.
				{
					if(Matriz[xActual][yActual - j] == null) break;
					if(xActual == xBomber && yActual - j == yBomber) 
					{ 
						if(!MasacralityActivo){
							Dead = true;
							muere = true;
						}
						break;
					}
				}
				else if(i == 1) // Abajo.
				{
					if(Matriz[xActual][yActual + j] == null) break;
					if(xActual == xBomber && yActual + j == yBomber) 
					{ 
						if(!MasacralityActivo){
							Dead = true;
							muere = true;
						}
						break;
					}
				}
				else if(i == 2) // Izq
				{
					if(Matriz[xActual - j][yActual] == null) break;
					if(xActual - j  == xBomber && yActual == yBomber) 
					{ 
						if(!MasacralityActivo){
							Dead = true;
							muere = true;
						}
						break;
					}
				}
				else if(i == 3) // Der
				{
					if(Matriz[xActual + j][yActual] == null) break;
					if(xActual + j  == xBomber && yActual == yBomber) 
					{ 
						if(!MasacralityActivo){
							Dead = true;
							muere = true;
						}
						break;
					}
				}
			}
		}


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
						MiTab.getLogic().getGUI().setTotal(MiTab.getLogic().getGUI().getTotal()-1);
						Matriz[xActual][yActual - i].setPared(null);// Rompe
						aRomper++;
						if(Matriz[xActual][yActual - i].getPowerUp() != null)
							Matriz[xActual][yActual - i].getPowerUp().getGrafica().visible();
					}
					else if(Matriz[xActual][yActual - i].getMalo() != null)
					{
						Matriz[xActual][yActual - i].getMalo().Muere();
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
						MiTab.getLogic().getGUI().setTotal(MiTab.getLogic().getGUI().getTotal()-1);
						Matriz[xActual][yActual + i].setPared(null); // Rompe
						aRomper++;
						if(Matriz[xActual][yActual + i].getPowerUp() != null)
							Matriz[xActual][yActual + i].getPowerUp().getGrafica().visible();
					}
					else if(Matriz[xActual][yActual + i].getMalo() != null)
					{
						Matriz[xActual][yActual + i].getMalo().Muere();
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
						MiTab.getLogic().getGUI().setTotal(MiTab.getLogic().getGUI().getTotal()-1);
						Matriz[xActual - i][yActual].setPared(null); // Rompe
						aRomper++;
						if(Matriz[xActual - i][yActual].getPowerUp() != null)
							Matriz[xActual - i][yActual].getPowerUp().getGrafica().visible();
					}
					else if(Matriz[xActual - i][yActual].getMalo() != null)
					{
						Matriz[xActual - i][yActual].getMalo().Muere();
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
						MiTab.getLogic().getGUI().setTotal(MiTab.getLogic().getGUI().getTotal()-1);
						Matriz[xActual  + i][yActual].setPared(null); // Rompe
						aRomper++;
						if(Matriz[xActual + i][yActual].getPowerUp() != null)
							Matriz[xActual + i][yActual].getPowerUp().getGrafica().visible();
					}
					else if(Matriz[xActual + i][yActual].getMalo() != null)
					{
						Matriz[xActual + i][yActual].getMalo().Muere();
					}
				}
				aEliminar.add(xActual + i);
				aEliminar.add(yActual);

			}
		}

		// Calcula el puntaje por romper paredes y lo modifica
		int addPuntaje = aRomper * MiCasilla.getPuntaje();
		MiTab.getLogic().getGUI().aumentarScore(addPuntaje);

		return muere;		
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