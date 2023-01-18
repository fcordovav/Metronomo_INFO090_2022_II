package cl.uach.info090.metronome;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Felipe C�rdova
 */

/**
 * Esta clase se encarga se crear lo principal que se ve en pantalla, tipo: ventana, panel, botones, interfaz de SimpleMetronomeDisplay.
 */
@SuppressWarnings("serial")
public class MetronomeGUI extends javax.swing.JFrame implements java.awt.event.ActionListener{
	private static MetronomeGUI metronome; // metronome
	private JPanel panel; // panel
	private JLabel etiqueta; // 'BPM'
	private JComboBox<String> opciones; // lista despegable
	private JButton inicio, fin; // botones
	private SimpleMetronomeDisplay interfaz; // zona de abajo de la interfaz
	private Pulse pulse; // pulso
	private JLabel creditos; // nombre del autor
	
	/**
	 * Constructor de MetronomeGUI el cual se la ventana, panel, botones de bpm, botones de inicio y fin, la interfaz de tipo 
	 * SimpleMetronomeDisplay y el pulso de tipo Pulse, para estas creaciones de crearon metodos privados para que se vea mas ordenado
	 */
	private MetronomeGUI() {
		crearVentana();
		crearPanel();
		crearBotonBPM();
		nombreAutor();
		crearBotones();
		crearInterfaz();
		crearPulso();
		this.setVisible(true); // visible
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // para que se cierre
	}
	/**
	 * Retorna la instancia para una clase singleton
	 * @return metronome
	 */
	public static MetronomeGUI getInstance() {
		if(metronome == null)
			metronome = new MetronomeGUI();
		return metronome;
	}
	/**
	 * Metodo privado que se invoca en el constructor para crear la dimension de la ventana con el titulo.
	 * Ademas se centra la ventana para que quede en medio de la pantalla.
 	 */
	private void crearVentana() {
		this.setSize(700,350); 
		this.setTitle("METRONOME");
		this.setLocationRelativeTo(null);
	}
	/**
	 * Metodo privado que se invoca en el constructor para crear un panel que va 'encima' de la ventana que ya está creada.
	 * Ademas se le asigna un color a la ventana.
 	 */
	private void crearPanel() {
		panel = new JPanel();
		panel.setLayout(null); // desactivando el diseño por defecto
		panel.setBackground(Color.orange);
		this.getContentPane().add(panel);
	}
	/**
	 * Metodo privado que se invoca en el constructor para crear la etiqueta BPM que se muestra en el panel, adyacentemente a esta
	 * etiqueta se crean las opciones para los BPM centrados. Ambas creaciones se agregan al panel.
 	 */
	private void crearBotonBPM() {
		etiqueta = new JLabel("BPM : ");
		etiqueta.setBounds(50,10,50,30);
		panel.add(etiqueta);
		opciones = new JComboBox<String>();
		for(int i=40; i<=220; i+=5)
			opciones.addItem(Integer.toString(i));
		opciones.setBounds(100,10,100,30);
		((JLabel)opciones.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(opciones);
	}
	/**
	 * Metodo privado que se encarga de crear la etiqueta con el nombre del autor de este proyecto
	 */
	private void nombreAutor() {
		creditos = new JLabel("Creator: Felipe Córdova");
		creditos.setBounds(540,265,150,50);
		panel.add(creditos);
	}
	
	/**
	 * Metodo privado que se invoca en el constructor para crear los botones inicio y fin. Estos botones se agregan al panel.
 	 */
	private void crearBotones() {
		inicio = new JButton("Play");
		inicio.addActionListener(this);
		inicio.setBounds(300,10,100,30);
		fin = new JButton("Stop");
		fin.addActionListener(this);
		fin.setBounds(500,10,100,30);
		panel.add(inicio);
		panel.add(fin);
	}
	/**
	 * Metodo privado para crear la interfaz de tipo SimpleMetronomeDisplay, este metodo se invoca en el constructor.
	 */
	private void crearInterfaz() {
		interfaz = new SimpleMetronomeDisplay();
		interfaz.setBounds(50,73,550,200);
		panel.add(interfaz);
	}
	/**
	 * Metodo privado que se invoca en el costructor para crear el pulso y el hilo.
	 */
	private void crearPulso() {
		pulse = new Pulse(interfaz);
	    pulse.start();
	}
	/** 
	 * Metodo sobre escrito de la libreria ActionListener, esto hace que los botones inicio y fin funcionen,
	 * ademas de pasar a entero el bpm seleccionado.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == inicio) {
			int seleccionado = Integer.parseInt((String)opciones.getSelectedItem());
			pulse.setBpm(seleccionado);
			pulse.setEjecucion(true);
		}
		if(e.getSource() == fin)
			pulse.setEjecucion(false);
	}
	/**
	 * Main donde inicia el programa
	 * @param args
	 */
	public static void main(String[] args) {
		MetronomeGUI.getInstance();
	}
}