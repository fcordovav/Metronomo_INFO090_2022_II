package cl.uach.info090.metronome;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author Felipe Córdova
 */

/**
 * En esta clase, principalmente se encarga de dibujar los circulos y pintarlos de acuerdo a contador de circulos (numeroCirculo).
 * Finalmente se agrego el sonido.
 */
public class SimpleMetronomeDisplay extends javax.swing.JPanel implements MetronomeDisplay {
	private static final long serialVersionUID = 1L;
	private int numeroCirculo = 0; // Atributo que hace referencia al circulo pintado correspondiente, (1,2,3,4 de izquierda a derecha),
	// inicialmente numeroCirculo parte en 0 para que no este pintado el circulo 1 al ejecutar el programa.
	private Clip sonido; // Atributo para el sonido.
	/**
	 * Constructor de SimpleMetronomeDisplay. Aqui se asigna color tipo negro y se invoca al metodo para el sonido 
	 * que fue adaptado del video de referencia que se adjunta en el metodo.
	 */
	public SimpleMetronomeDisplay() {
		setBackground(Color.black);
		abrirSonido();
	}
	/**
	 * Metodo el cual dibuja los circulos y los pinta.
	 * La variable numero de explica arriba.
	 * Se adjunta referencia: https://www.youtube.com/watch?v=pI8EIQQPVNk
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/**
		 * Hasta antes del if se crean los circulos iniciales, sin pintar con sus respectivas ubicaciones.
		 */
		g.setColor(Color.green);
		g.drawOval(40, 50, 100, 100);
		g.setColor(Color.red);
		g.drawOval(160, 50, 100, 100);
		g.setColor(Color.yellow);
		g.drawOval(280, 50, 100, 100);
		g.setColor(Color.blue);
		g.drawOval(400, 50, 100, 100);
		/**
		 * A partir de aqui va verificando en que circulo va, y realizar la labor de pintar su correspondiente
		 * circulo y luego vuelve a estar sin pintar. tambien aqui se trabaja con el sonido para que el metronome suene.
		 * Al final se repiten instrucciones para cada circulo, cambiando obviamente la ubicacion del pintado segun donde vaya numeroCirculo.
		 */
		if(numeroCirculo == 1) {
			g.setColor(Color.green);
			g.fillOval(40, 50, 100, 100);
			g.drawOval(160, 50, 100, 100);
			g.drawOval(280, 50, 100, 100);
			g.drawOval(400, 50, 100, 100);
		}
		if(numeroCirculo == 2) {
			g.setColor(Color.red);
			g.drawOval(40, 50, 100, 100);
			g.fillOval(160, 50, 100, 100);
			g.drawOval(280, 50, 100, 100);
			g.drawOval(400, 50, 100, 100);
		}
		if(numeroCirculo == 3) {
			g.setColor(Color.yellow);
			g.drawOval(40, 50, 100, 100);
			g.drawOval(160, 50, 100, 100);
			g.fillOval(280, 50, 100, 100);
			g.drawOval(400, 50, 100, 100);
		}
		if(numeroCirculo == 4) {
			g.setColor(Color.blue);
			g.drawOval(40, 50, 100, 100);
			g.drawOval(160, 50, 100, 100);
			g.drawOval(280, 50, 100, 100);
			g.fillOval(400, 50, 100, 100);
		}
		this.sonido.start();
		this.sonido.setMicrosecondPosition(0);
	}
	
	/**
	 * Metodo sobre escrito de la interfaz MetronomeDisplay.
	 * Tiene como funcionalidad, invocar a repaint e ir sumando 1 al contador de circulos. (recordar que son 4 circulos y que se
	 * toman de izquierda a derecha) 1.2.3.4
	 */
	@Override
	public void tick() {
		repaint();
		if(numeroCirculo < 4)
			numeroCirculo++;
		else
			numeroCirculo = 1;
	}
	/**
	 * Metodo privado el cual se encarga del sonido
	 * Se adjunta referencia: https://www.youtube.com/watch?v=b2FdBujapwM&t=491s
	 */
	private void abrirSonido(){
		File archivo = new File("./data/sonido_metronomo.wav");
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(archivo);
			this.sonido = AudioSystem.getClip();
			this.sonido.open(audio);
		}catch (UnsupportedAudioFileException | IOException |LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
