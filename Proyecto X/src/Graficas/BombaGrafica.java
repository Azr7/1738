package Graficas;
import java.util.ArrayList;

import Personaje.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Timers.*;
import Mapa.*;

/**
 * 
 * Clase grafica de bomba
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class BombaGrafica {
	/**
	 * Imagen de la bomba
	 */
	protected JLabel Bomba;

	/**
	 * Thread que controla los timers de la bomba
	 */
	protected BombaThread BMBThread;
	/**
	 * Atributo tipo Bomba
	 */
	protected Bomba B;
	/**
	 * Tamaño de pixel en la parte grafica, necesaria para hacer calculos auxiliares
	 */
	protected int MovPix = 16;

	/**
	 * Crea una grafica asociada a la bomba
	 * @param b tipo bomba
	 */
	public BombaGrafica(Bomba b)
	{
		Bomba = new JLabel();

		B = b;
		
		BMBThread = new BombaThread(B.getBomberman().getTablero().getLogic(),B.getBomberman().getTablero().getLogic().getGUI(),B,this);
	}

	/**
	 * Devuelve la imagen asociada a la bomba
	 * @return JLabel grafica
	 */
	public JLabel getLabelBomba()
	{
		return Bomba;
	}

	/**
	 * Setea Thread a la grafica
	 * @param bmbt thread
	 */
	public void setBombThread(BombaThread bmbt)
	{
		BMBThread = bmbt;
	}

	/**
	 * Realiza los graficos correspondientes a la colocacion de una bomba
	 * @param x pos 
	 * @param y pos
	 */
	public void colocarBombaGrafica(int x, int y)
	{
		
			Casilla[][] Matriz = B.getBomberman().getTablero().getMatriz();
			Matriz[x][y] = null;
			B.getBomberman().getTablero().getLogic().getGUI().add(Bomba);
			Bomba.setBounds(x*MovPix,y*MovPix,30,30);
			Bomba.setVisible(true);
			Bomba.setIcon(new ImageIcon("Images/bomba1.png"));	
	}
	
	public void colocarBombaGrafica2(int x,int y)
	{
		Casilla[][] Matriz = B.getBomberman().getTablero().getMatriz();
		Bomba.setVisible(false);
		Matriz[x][y] = new Casilla(x,y);
		Matriz[x][y].setPared(null);
	}

	/**
	 * Realiza los graficos de las explosiones de las bombas
	 * @param AL lista de elementos a explotar graficamente
	 */
	public void recibirCoordenadasFuego(ArrayList<Integer> AL)
	{
		JLabel MatrizLabels[][] = BMBThread.getGUI().getMatrizLabel();
		FuegoGrafica FG = new FuegoGrafica();
		try{
			for(int i=0; i<AL.size(); i = i + 2)
			{
				int x = AL.get(i);
				int y = AL.get(i+1);
				MatrizLabels[x][y].setIcon(FG.getFuego(0));
			}
			BMBThread.sleep(150);
			for(int i=0; i<AL.size(); i = i + 2)
			{
				int x = AL.get(i);
				int y = AL.get(i+1);
				MatrizLabels[x][y].setIcon(FG.getFuego(1));
			}
			BMBThread.sleep(150);
			for(int i=0; i<AL.size(); i = i + 2)
			{
				int x = AL.get(i);
				int y = AL.get(i+1);
				MatrizLabels[x][y].setIcon(FG.getFuego(2));
			}
			BMBThread.sleep(200);
			for(int i=0; i<AL.size(); i = i + 2)
			{
				int x = AL.get(i);
				int y = AL.get(i+1);
				MatrizLabels[x][y].setIcon(FG.getFuego(3));
			}
			BMBThread.sleep(200);
			for(int i=0; i<AL.size(); i = i + 2)
			{
				int x = AL.get(i);
				int y = AL.get(i+1);
				MatrizLabels[x][y].setIcon(FG.getFuego(4));
			}
			BMBThread.sleep(200);
			for(int i=0; i<AL.size(); i = i + 2)
			{
				int x = AL.get(i);
				int y = AL.get(i+1);
				MatrizLabels[x][y].setIcon(null);
			}

			if(B.getBomberman().getTablero().getLogic().getGUI().getTotal() == 0)
			{
				B.getBomberman().getTablero().getLogic().getGUI().Win();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
