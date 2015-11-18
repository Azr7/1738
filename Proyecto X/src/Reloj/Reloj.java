package Reloj;

public class Reloj {
	protected int seg;
	protected int min;
	
	public Reloj()
	{
		seg = 0;
		min = 0;
	}
	
	public void aumentarSegundos()
	{
		if(seg == 59)
		{
			seg = 0;
			min++;
		}
		else seg++;
	}
	
	public int getSeg(){return seg;}
	public int getMin(){return min;}
	
}
