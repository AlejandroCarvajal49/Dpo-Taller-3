package uniandes.dpoo.aerolinea.modelo;

import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */
public class Ruta
{
     private String horaSalida;
     private String horaLlegada;
     private String codigoRuta;
     private Aeropuerto origen;
     private Aeropuerto destino;
     
	public Ruta(Aeropuerto origen, Aeropuerto destino, String horaSalida, String horaLlegada, String codigoRuta) throws AeropuertoDuplicadoException {
		
		this.origen = origen;
		this.destino = destino;
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.codigoRuta = codigoRuta;
		
		}
		
		public String getCodigoRuta() {
			return codigoRuta;
		}
			
		
		public String getHoraSalida() {
			return horaSalida;
		}

		public String getHoraLlegada() {
			
			return horaLlegada;
		}

		public Aeropuerto getOrigen() {
			return origen;
		}

		public Aeropuerto getDestino() {
			return destino;
		}
		
		public int getDuracion() {
			
	        String[] salida = horaSalida.split(":");
	        String[] llegada = horaLlegada.split(":");

	        int horaSalidaEnMinutos = Integer.parseInt(salida[0]) * 60 + Integer.parseInt(salida[1]);
	        int horaLlegadaEnMinutos = Integer.parseInt(llegada[0]) * 60 + Integer.parseInt(llegada[1]);

	        // Considera que el vuelo podría llegar al día siguiente
	        if (horaLlegadaEnMinutos < horaSalidaEnMinutos) {
	            horaLlegadaEnMinutos += 24 * 60;  // Añade 24 horas en minutos
	        }
	        
	        

	        return horaLlegadaEnMinutos - horaSalidaEnMinutos;
	        
	        
	    }
/**
     * Dada una cadena con una hora y minutos, retorna los minutos.
     * 
     * Por ejemplo, para la cadena '715' retorna 15.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de minutos entre 0 y 59
     */
    public static int getMinutos( String horaCompleta )
    {
        int minutos = Integer.parseInt( horaCompleta ) % 100;
        return minutos;
    }

    /**
     * Dada una cadena con una hora y minutos, retorna las horas.
     * 
     * Por ejemplo, para la cadena '715' retorna 7.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de horas entre 0 y 23
     */
    public static int getHoras( String horaCompleta )
    {
        int horas = Integer.parseInt( horaCompleta ) / 100;
        return horas;
    }

    
}
