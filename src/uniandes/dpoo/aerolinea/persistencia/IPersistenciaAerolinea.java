package uniandes.dpoo.aerolinea.persistencia;

import uniandes.dpoo.aerolinea.modelo.Aerolinea;

public interface IPersistenciaAerolinea {
	
	/**
	 * Carga la información de la aerolínea, y actualiza la estructura de objetos
	 * que se encuentra dentro de la aerolínea
	 * 
	 * @param archivo La ruta al archivo que contiene la información que se va a
	 *                cargar
	 * @throws Exception Se lanza esta excepción si hay problemas leyendo el archivo
	 */
	public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws Exception;
		
	
	

	/**
	 * Salva en un archivo toda la información sobre la aerolínea
	 * 
	 * @param archivo La ruta al archivo donde debe quedar almacenada la información
	 * @throws Exception Se lanza esta excepción si hay problemas escribiendo el
	 *                   archivo
	 */
	public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws Exception;

}
