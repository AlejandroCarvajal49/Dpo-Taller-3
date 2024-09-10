package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class PersistenciaTiquetesJson implements IPersistenciaTiquetes {
	
	private String NOMBRE_CLIENTE="nombre";
	private String TIPO_CLIENTE="tipoCliente";
	private String CLIENTE="cliente";
	private String USADO="usado";
	private String TARIFA="tarifa";
	private String CODIGO_TIQUETE="codigoTiquete";
	private String FECHA="fecha";
	private String CODIGO_RUTA="codigoRuta";
	
	
	@Override
	public void cargarTiquetes(String archivo, Aerolinea aerolinea) throws IOException, InformacionInconsistenteException {
		
		String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
		JSONObject archivo1 = new JSONObject( jsonCompleto);
		cargarClientes(aerolinea, archivo1.getJSONArray("clientes"));
		try {
			cargarTiquetes(aerolinea, archivo1.getJSONArray("tiquetes"));
		} catch (InformacionInconsistenteException e) {
			throw new InformacionInconsistenteException("Error en la informacion de los tiquetes");
		} 
		
		
		
		
	}

	@Override
	public void salvarTiquetes(String archivo, Aerolinea aerolinea) throws IOException {
		
		
		JSONObject jobject1 = new JSONObject();
		salvarClientes(aerolinea, jobject1);
		
		JSONObject jTiquetes = new JSONObject();
		salvarTiquetes1(aerolinea, jTiquetes);
		
		
	}
	
	
	private void cargarClientes(Aerolinea aerolinea, JSONArray jClientes) throws InformacionInconsistenteException {
        int longitud = jClientes.length();
		for (int i = 0; i < longitud; i++) {
			Cliente nuevoCliente1 = null;
			JSONObject jCliente = jClientes.getJSONObject(i);
			String tipoCliente = jCliente.getString(TIPO_CLIENTE);
			if (ClienteNatural.NATURAL.equals(tipoCliente)) {
				nuevoCliente1 = ClienteNatural.cargarDesdeJSON(jCliente);
			} else {
				nuevoCliente1 = ClienteCorporativo.cargarDesdeJSON(jCliente);
			} if (aerolinea.existeCliente(nuevoCliente1.getIdentificador())) {
                throw new InformacionInconsistenteException("El cliente no existe");
			} else {
				aerolinea.agregarCliente(nuevoCliente1);
			}
			}
		

	}
	
	private void salvarClientes(Aerolinea aerolinea, JSONObject jobject) {
		JSONArray jClientes = new JSONArray();
		for (Cliente cliente : aerolinea.getClientes()) {
			JSONObject jCliente = new JSONObject();
			if (cliente instanceof ClienteNatural) {
				jCliente.put(TIPO_CLIENTE, ClienteNatural.NATURAL);
			} else {
				jCliente.put(TIPO_CLIENTE, ClienteCorporativo.CORPORATIVO);
			}
			jCliente.put(NOMBRE_CLIENTE, cliente.getIdentificador());
			jClientes.put(jCliente);
		}
		jobject.put("clientes", jClientes);

	}
	

	private void cargarTiquetes(Aerolinea aerolinea, JSONArray jTiquete) throws InformacionInconsistenteException{
	    int longitud = jTiquete.length();
	    for (int i = 0; i < longitud; i++) {
	        JSONObject jTiquete1 = jTiquete.getJSONObject(i);
	        String codigoTiquete = jTiquete1.getString(CODIGO_TIQUETE);
	        String fecha = jTiquete1.getString(FECHA);
	        int tarifa = jTiquete1.getInt(TARIFA);
	        boolean usado = jTiquete1.getBoolean(USADO);
	        String codigoRuta = jTiquete1.getString(CODIGO_RUTA);
	        String identificadorCliente = jTiquete1.getString(CLIENTE);
	
	        
	        Cliente cliente = aerolinea.getCliente(identificadorCliente);
	        if (cliente != null) {
	            Vuelo vuelo = aerolinea.getVuelo(codigoRuta, fecha);
	            if (vuelo != null) {
	                Tiquete tiquete = new Tiquete(codigoTiquete, vuelo, cliente, tarifa);
	                cliente.agregarTiquete(tiquete);
	        GeneradorTiquetes.registrarTiquete(tiquete);
	                } else {
	                	throw new InformacionInconsistenteException("El vuelo no existe");
	            }
			} else {
				throw new InformacionInconsistenteException("El cliente no existe");
			}
	       
	        
	        
	    }
}
	
	
	private void salvarTiquetes1(Aerolinea aerolinea, JSONObject jTiquetes) {
		JSONArray jTiquete = new JSONArray();
		for (Tiquete tiquete : aerolinea.getTiquetes()) {
			JSONObject jTiquete1 = new JSONObject();
			jTiquete1.put(CODIGO_TIQUETE, tiquete.getCodigo());
			jTiquete1.put(FECHA, tiquete.getVuelo().getFecha());
			jTiquete1.put(TARIFA, tiquete.getTarifa());
			jTiquete1.put(USADO, tiquete.esUsado());
			jTiquete1.put(CODIGO_RUTA, tiquete.getVuelo().getRuta().getCodigoRuta());
			jTiquete1.put(CLIENTE, tiquete.getCliente().getIdentificador());
			jTiquete.put(jTiquete1);
		}
		jTiquetes.put("tiquetes", jTiquete);

	}
	
	
	
	

}
