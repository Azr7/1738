package PowerUps;
import Graficas.*;
import Personaje.*;

import java.util.*;

/**
 * 
 * Clase logica powerup Bombality
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class Bombality extends PowerUp {

	/**
	 * Crea el power up bombality con su respectivo score
	 */
	public Bombality() {
		super(35);
		super.GPU = new BombalityGrafica();
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
		System.out.println("AGARRO EL BOMBALITY");
		super.GPU.getPowerLabel().setIcon(null);
		b.Bombality();
	}

}