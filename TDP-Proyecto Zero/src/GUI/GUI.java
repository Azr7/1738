package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class GUI extends javax.swing.JFrame {
	private JButton Act;
	private JTextArea frase;
	private JLabel Imagen;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI inst = new GUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public GUI() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setTitle("Proyecto 0 | 1738");
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				Act = new JButton();
				getContentPane().add(Act);
				Act.setText("Pulsar");
				Act.setBounds(18, 153, 95, 24);
				Act.setFont(new java.awt.Font("Segoe UI",0,12));
				OyenteFrase OF = new OyenteFrase();
				Act.addActionListener(OF);
			}
			{
				Imagen = new JLabel();
				getContentPane().add(Imagen);
				Imagen.setBounds(18, 10, 94, 137);
				Imagen.setIcon(new ImageIcon(getClass().getClassLoader().getResource("GUI/carl.gif")));
				Imagen.setBorder(BorderFactory.createTitledBorder(""));

			}
			{
				frase = new JTextArea();
				getContentPane().add(frase);
				frase.setBounds(124, 12, 250, 165);
				frase.setBorder(BorderFactory.createTitledBorder(""));
				frase.setEditable(false);
				frase.setFont(new java.awt.Font("Segoe UI",2,20));
			}
			pack();
			this.setSize(408, 228);
		} catch (Exception e) {
		   
			e.printStackTrace();
		}
	}
	
	private class OyenteFrase implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String C = "''LLEGÓ CARL , EL NEGRO\nQUE SABE JUGAR\nBASKETBALL''\n\n\n-Carl.";
			frase.setText(C);
		}
		
	}

}
