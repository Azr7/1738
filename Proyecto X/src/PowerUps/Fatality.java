package PowerUps;
import Graficas.*;
import Personaje.*;

import java.util.*;

/**
 * 
 * Clase  logica PowerUp Fatality
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class Fatality extends PowerUp {

	/**
	 * Crea el power up fatality con su respectivo score
	 */
	public Fatality() {
		super(35);
		super.GPU = new FatalityGrafica();
	}

	/**
	 * 
	 */
	public int getPuntaje() {
		return super.getPuntaje();
	}

	/**
	 * 
	 */
	public void Buff(Bomberman b) {
		System.out.println("AGARRO EL FATALITY");
		super.GPU.getPowerLabel().setIcon(null);
		b.Fatality();
	}


}