package GUIandLOGIC;
import Graficas.*;
import Reloj.*;
import Timers.*;
import Mapa.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import Personaje.*;
import MAIN.*;
/**
 * 
 * Clase GUI, implementando la interfaz grafica
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class GUI extends javax.swing.JFrame {

	/**
	 * Puntaje total del juego
	 */
	protected int Puntaje;
	/**
	 * referencia a la clase logica
	 */
	protected Logica MiLogica;
	/**
	 * Imagenes asociadas al tablero, paredes y caminos libres.
	 */
	protected JLabel Mapa[][];
	/**
	 * Tama�o de una sola casilla en pixel (son cuadradas)
	 */
	protected int MovPix = 16;
	/**
	 * Contenedor de la gui
	 */
	protected Container contenedor;
	/**
	 * Grafica del bomberman
	 */
	protected BombermanGrafica GraphBomber;
	/**
	 * Conjunto de imagenes asociadas a los enemigos
	 */
	protected JLabel LabelEnemies[];
	/**
	 * direccion, utilizada en los listener de movimiento de teclado
	 */
	protected int direccion = 4;
	/**
	 * Aux booleano usado en los movs de teclado
	 */
	protected boolean lock = false;

	/**
	 * Thread del bomberman
	 */
	protected BombermanThread BT;

	/**
	 * Thread de los enemigos (uno por cada uno)
	 */
	protected EnemigoThread ETs[];
	/**
	 * Aux booleano para determinar si esta muerto o no el bomberman
	 */
	protected boolean noMuerto = true;	
	/**
	 * arg de Threads e imagenes de enemigos
	 */
	protected int arg = 0;

	/**
	 * Datos del juego, info, score y tiempo, en imagen base.
	 */
	protected JLabel datos;
	/**
	 * score
	 */
	protected JLabel score;
	/**
	 * reloj
	 */
	protected JLabel reloj;
	/**
	 * timer reloj
	 */
	protected RelojThread RT;
	/**
	 * segundos
	 */
	protected int S;
	/**
	 * minutos
	 */
	protected int M;
	/**
	 * puntaje total
	 */
	protected int ObjetosDestruibles;
	

	/**
	 * Crea la grafica del juego
	 */
	public GUI() {
		super();
		ObjetosDestruibles = 0;
		Iniciar();
		initGUI();
	}


	/**
	 * Inicializa la matriz de imagenes del juego, teniendo en cuenta la Matriz logica de casillas.
	 * A su vez, genera la grafica del bomberman, los enemigos.
	 * Y realiza las asociaciones necesarias en cuanto a Threads, Imagenes y Logica de personajes.
	 */
	public void Iniciar()
	{
		contenedor = getContentPane();
		Color C = new Color(30,150,30);
		contenedor.setBackground(C);
		contenedor.setBounds(0, -1, 508, 511);
		MiLogica = new Logica();
		MiLogica.setGUI(this);
		Puntaje = 0;
		MiLogica.InicializarTablero();
		Mapa = new JLabel[31][31];
		Casilla[][] Matriz = MiLogica.getMatrizTablero();
		GraphBomber = new BombermanGrafica();
		LabelEnemies = new JLabel[6];
		ETs = new EnemigoThread[6];
		setTotal(MiLogica.getTablero().getCantidadObjetivos());		

		datos = new JLabel();		
		datos.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Graficas/Datos.png")));
		contenedor.add(datos);
		datos.setBounds(510,16,500,500);

		score = new JLabel("0");
		contenedor.add(score);
		score.setBounds(630,95,100,15);

		RT = new RelojThread(this);
		reloj = new JLabel("0 min : 0 seg");
		contenedor.add(reloj);
		reloj.setBounds(600,48,100,15);
		// iniciamos todo lo necesario utilizado en la gui.


		Bomberman BombermanAux = null;
		int actualX = 0;
		int actualY = 0;


		// Hacemos un doble for, manejando la matriz logica para armar la grafica
		for(int i=0; i<31 ; i++)
		{
			for(int j=0; j<31; j++)
			{
				Mapa[i][j] = new JLabel("");
				if(Matriz[i][j] == null) // Sabemos que si casilla es null, es irrompible
				{
					ImageIcon II = new ImageIcon(getClass().getClassLoader().getResource("Graficas/solido.png"));
					Mapa[i][j].setIcon(II);
				}
				else if(Matriz[i][j].getPared() == null) // Si la casilla es NO NULA, puede o no tener pared
				{
					if(Matriz[i][j].getMalo() != null) // Si no tiene pared, puede contener un malo
					{
						EnemigoGrafica aux = Matriz[i][j].getMalo().getEnemyGraphics();
						LabelEnemies[arg] = aux.getEnemyLabel();
						aux.setIndice(arg);
						contenedor.add(LabelEnemies[arg],0);
						LabelEnemies[arg].setBounds(actualX,actualY,30,30);			
						ETs[arg] = new EnemigoThread(aux,Matriz[i][j].getMalo());
						arg++;						
					}
				}
				else
				{	// Si pared != null, entonces es un rompible, el cual puede o no tener powerup
					ImageIcon II = new ImageIcon(getClass().getClassLoader().getResource("Graficas/rompible.png"));
					Mapa[i][j].setIcon(II);
					if(Matriz[i][j].getPowerUp() != null)
					{
						JLabel PWGrafica = Matriz[i][j].getPowerUp().getGrafica().getPowerLabel();
						contenedor.add(PWGrafica);
						PWGrafica.setBounds(actualX,actualY,30,30);
						PWGrafica.setVisible(false); // <<< EL POWER UP COMIENZA COMO INVISIBLE HASTA QUE UNA PARED EXPLOTA
					}
				}
				contenedor.add(Mapa[i][j]);
				Mapa[i][j].setBounds(actualX,actualY,30,30);
				actualY = actualY + MovPix;
			}
			actualX = actualX + MovPix;
			actualY = 0;

		}
		// Anteriormente tomamos en cuenta la iniciativa de hacer que el bomberman y los powerups, sean imagenes que no
		// est�n en el tablero, sino por encima. Pero al usar setbounds los colocamos en los lugares correctos.

		// Grafica de bomberman, thread, imagen, y demas se inicializan y relacionan aqui
		JLabel JBomberman = GraphBomber.getBomberLabel();
		JBomberman.setBounds(1*MovPix,1*MovPix, 30, 30);
		Matriz[1][1].getBomber().setBomberGrafica(GraphBomber);
		BombermanAux = Matriz[1][1].getBomber();
		contenedor.add(JBomberman,0);
		BT = new BombermanThread(BombermanAux,MiLogica,this);
		GraphBomber.setBT(BT);
		GraphBomber.setGUI(this);


	}

	/**
	 * Modifica la grafica y agrega los listeners de teclado
	 */
	private void initGUI() {
		try {
			{
				this.setLayout(null);
				this.setTitle("Bomberman | TDP 2015 | AR & FS");
				this.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent i) {
						if(noMuerto)
						{
							if(i.getKeyCode() == KeyEvent.VK_SPACE) MiLogica.getBombermanTablero().colocarBomba();
							else mover(i);
						}
					}
					public void keyTyped(KeyEvent i) {
						if(noMuerto)
						{
							if(i.getKeyCode() == KeyEvent.VK_SPACE) MiLogica.getBombermanTablero().colocarBomba();
							else mover(i);
						}
					}
				});

			}
			setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
			this.addWindowListener(new CortarThreads());
			pack();
			this.setSize(720, 535);
			this.setResizable(false);
		} catch (Exception e) {
			//add your error handling code here
			e.printStackTrace();
		}
	}
	/**
	 * Clase utilizada al momento de cerrar el juego para detener los threads activos.
	 * - Nota : Falta completar con otras cosas -
	 * @author Alexis Rivas & Franco Sorgato
	 *
	 */
	private class CortarThreads implements WindowListener{
		// Queriamos cortar con los threads activos una vez que se cierra el juego.
		// Con los del bomberman y los malos, se cortar�an al momento del game over
		public void windowClosing(java.awt.event.WindowEvent arg0) {
			RT.toggleStop();
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}
		public void windowActivated(java.awt.event.WindowEvent arg0){}
		public void windowClosed(java.awt.event.WindowEvent arg0) {}
		public void windowDeactivated(java.awt.event.WindowEvent arg0) {}
		public void windowDeiconified(java.awt.event.WindowEvent arg0) {}
		public void windowIconified(java.awt.event.WindowEvent arg0) {}
		public void windowOpened(java.awt.event.WindowEvent arg0) {}
	}


	/**
	 * Metodo aux utilizado para saber si el usuario desea mover al personaje principal, luego bloquea mas acciones.
	 * @param k evento de teclado
	 */
	public void mover(KeyEvent k)
	{
		if(!lock){
			direccion = k.getKeyCode();
			lock = true;
		}
	}

	/**
	 * Desbloquea el movimiento
	 */
	public void toggle()
	{
		lock = !lock;
	}


	/**
	 * Devuelve la direccion hacia donde el usuario se quiere desplazar con el personaje principal
	 * @return int 0,1,2,3 , siendo cada una de ellas una direccion
	 */
	public int getDireccion()
	{
		return direccion;
	}

	/**
	 * Consulta, para saber si el personaje esta realizando movimientos
	 * @return boolean verdadero o falso
	 */
	public boolean isLocked(){
		return lock == false;
	}

	/**
	 * Toggea la situacion de vida de bomberman
	 */
	public void BombermanMuere()
	{
		noMuerto = false;
		GameOver();
	}
	/**
	 * Metodo aux para terminar el juego
	 * - Nota : Falta implementar -
	 */
	private void GameOver()
	{
		RT.toggleStop();
		int seleccion = JOptionPane.showOptionDialog( null,"Usted ha perdido. Consigui� "+Puntaje+" puntos \nen "+M+" minutos y "+S+" segundos.",
				"Game Over !",JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,null,// null para icono por defecto.
				new Object[] { "Cerrar"},"");
		System.exit(0);		
	}

	/**
	 * Devuelve la matriz de imagenes asociada al tablero completo de la grafica
	 * @return Matriz de JLabels
	 */
	public JLabel[][] getMatrizLabel()
	{
		return Mapa;
	}
	/**
	 * aumenta el score del juego
	 * @param n entero
	 */
	public void aumentarScore(int n)
	{
		Puntaje = Puntaje + n;
		score.setText("" + Puntaje);
	}
	/**
	 * acomoda el reloj con el tiempo actual
	 */
	public void modClock()
	{
		S = RT.getS();
		M = RT.getM();
		reloj.setText(M + " min : " + S + " seg");
	}
	/**
	 * borra un label enemigo
	 * @param i
	 */
	public void quitarEnemigo(int i)
	{
		LabelEnemies[i].setIcon(null);
	}
	/**
	 * setea el puntaje total antes de comenzar el juego
	 * @param x puntaje
	 */
	public void setTotal(int x)
	{
		ObjetosDestruibles = x;
	}
	/**
	 * retorna el puntaje total
	 * @return puntaje entero total
	 */
	public int getTotal()
	{
		return ObjetosDestruibles;
	}
	/**
	 * retorna puntaje actual
	 * @return puntaje entero
	 */
	public int getPuntaje()
	{
		return Puntaje;
	}
	
	/**
	 * metodo ganar
	 */
	public void Win()
	{
		RT.toggleStop();
		int seleccion = JOptionPane.showOptionDialog( null,"Usted ha GANADO. Consigui� "+Puntaje+" puntos \nen "+M+" minutos y "+S+" segundos.",
				"FELICITACIONES !",JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,null,// null para icono por defecto.
				new Object[] { "Cerrar"},"");
		System.exit(0);				
	}
}
