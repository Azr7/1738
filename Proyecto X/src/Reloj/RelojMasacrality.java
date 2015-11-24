package Reloj;
import GUIandLOGIC.*;
import Personaje.*;

public class RelojMasacrality extends Thread{
	
	protected Bomberman bomber;
	
	public RelojMasacrality(Bomberman b)
	{
		bomber = b;
		start();
	}
	
		public void run()
		{
			try {
				sleep(5000);
				bomber.desMasacrear();
			} catch (InterruptedException e) {}
		}
	
}
