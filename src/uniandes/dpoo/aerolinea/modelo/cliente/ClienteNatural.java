package uniandes.dpoo.aerolinea.modelo.cliente;

import org.json.JSONObject;

import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class ClienteNatural extends Cliente{
	
	public final static String NATURAL = "Natural";
	private String nombre;
	
	public ClienteNatural(String nombre) {
		this.nombre = nombre;
	}
	
	public String getIdentificador() {
		return "NATURAL-" + nombre;
    
	}
	
	public String getTipoCliente() {
		return NATURAL;
	}

	public static ClienteNatural cargarDesdeJSON(JSONObject cliente) {
		String nombre = cliente.getString("nombre");
		return new ClienteNatural(nombre);
	}

	
}
