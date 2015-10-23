package PowerUps;
import Personaje.*;
import Graficas.*;
import java.util.*;


/**
 * 
 * Clase logica powerUp speedup
 * @author Alexis Rivas & Franco Sorgato
 *
 */
public class SpeedUp extends PowerUp {

	/**
	 * Crea el power up speedup con su respectivo score
	 */
	public SpeedUp() {
		super(30);
		super.GPU = new SpeedUpGrafica();
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
		System.out.println("AGARRO EL SPEED");
		super.GPU.getPowerLabel().setIcon(null);
		b.SpeedUp();
	}

}