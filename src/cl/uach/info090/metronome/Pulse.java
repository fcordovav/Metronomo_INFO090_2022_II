package cl.uach.info090.metronome;
/**
 * @author Felipe Córdova.
 */

/**
 * Esta clase se crean los getters y setter necesarios para los atributos definidos.
 * Tambien incluye el metodo run() el cual esta en ejecucion constantemente (while True).
 */
public class Pulse extends java.lang.Thread{
	private int bpm = 60; // bpm en 60
	private MetronomeDisplay objeto;// objeto de tipo MetronomeDsiplay
	private boolean ejecucion; // atributo de thread. true--> play,  false-->stop
	/**
	 * Constructor de la clase Pulse, se encarga de hacer referencia el objeto.
	 * @param o
	 */
	public Pulse(MetronomeDisplay o){
		this.objeto = o; //interfaz
		this.ejecucion = false; // inicialmente parte en falso para que no empiece a ejecutarse 
		// y pintar los circulos hasta cuando se haga click en inicio (boton play) y se cambie a true.
		
	}
	public int getBpm() {
		return bpm;
	}
	public void setBpm(int bpm) {
		this.bpm = bpm;
	}
	public MetronomeDisplay getObjeto() {
		return objeto;
	}
	/**
	 * Retorna el valor de verdad del atributo ejecucion
	 * @return ejecucion
	 */
	public boolean isEjecucion() {
		return ejecucion;
	}
	public void setEjecucion(boolean ejecucion) {
		this.ejecucion = ejecucion;
	}
	/**
	 * Metodo sobre escrito de la libreria Thread
	 * Thread se encarga de llevar el pulso del metronomo de acuerdo al bpm seleccionado
	 */
	@Override
	public void run() {
		while(true) {
			if(isEjecucion())
				this.objeto.tick();
			try {
				float tiempo = 1000 * ((float)60/getBpm());
	            Thread.sleep((long)tiempo);
	         }catch (Exception e) {
	        	 e.printStackTrace();
	         }
		}
	}
}