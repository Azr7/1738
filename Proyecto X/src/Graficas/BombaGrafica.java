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
	 * Animacion de la bomba
	 */
	protected Icon Bombas[];
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
		Bombas = new ImageIcon[11];

		Bombas[0] = new ImageIcon("Images/bomba1.png");
		Bombas[1] = new ImageIcon("Images/bomba2.png");
		Bombas[2] = new ImageIcon("Images/bomba3.png");
		Bombas[3] = new ImageIcon("Images/bomba1.png");
		Bombas[4] = new ImageIcon("Images/bomba2.png");
		Bombas[5] = new ImageIcon("Images/bomba3.png");
		Bombas[6] = new ImageIcon("Images/bomba1.png");
		Bombas[7] = new ImageIcon("Images/bomba2.png");
		Bombas[8] = new ImageIcon("Images/bomba3.png");
		Bombas[9] = new ImageIcon("Images/bomba1.png");
		Bombas[10] = new ImageIcon("Images/bomba2.png");

		Bomba = new JLabel();

		B = b;

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
		try {
			Casilla[][] Matriz = B.getBomberman().getTablero().getMatriz();
			Matriz[x][y].setPared(new Pared());
			Bomba.setBounds(x*MovPix,y*MovPix,30,30);
			Bomba.setIcon(Bombas[0]);
			Bomba.setVisible(true);
			for(int i=0; i<11; i++)
			{
				BMBThread.sleep(300);
				Bomba.setIcon(Bombas[i]);
			}			
			Bomba.setVisible(false);
			Matriz[x][y].setPared(null);
			B.toggle();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
