package Reloj;
import GUIandLOGIC.*;

public class RelojThread extends Thread{
	protected Reloj MiReloj;
	protected GUI lazoGUI;
	
	protected volatile boolean stop = false;
	
	public RelojThread(GUI g)
	{
		lazoGUI = g;
		MiReloj = new Reloj();
	}
	
		public void run()
		{
			while(!stop)
			{
				try {
					sleep(1000);
					MiReloj.aumentarSegundos();
					lazoGUI.modClock();
				} catch (InterruptedException e) {}
			}
		}
		
	public void toggleStop()
	{
		this.interrupt();
		stop = !stop;
	}
	
	public int getS(){return MiReloj.getSeg();}
	public int getM(){return MiReloj.getMin();}
}
