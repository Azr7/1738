package PowerUps;
import Personaje.*;
import Graficas.*;

import java.util.*;


/**
 * 
 * Clase Logica powerUp Masacrality
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class Masacrality extends PowerUp {

	/**
	 * Crea el power up masacrality con su respectivo score
	 */
	public Masacrality() {
		super(50);
		super.GPU = new MasacralityGrafica();
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
		System.out.println("AGARRO EL MASACRA");
		super.GPU.getPowerLabel().setIcon(null);
	}

}