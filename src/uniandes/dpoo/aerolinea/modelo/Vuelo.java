package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
	
	private String fecha;
	private Ruta ruta;
	private Avion avion;
	private Map<String, Tiquete> tiquetes;
	
	public Vuelo(Ruta ruta, String fecha, Avion avion) {
		this.ruta = ruta;
		this.fecha = fecha;
		this.avion = avion;
	}

	public String getFecha() {
		return fecha;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public Avion getAvion() {
		return avion;
	}
	
	public Collection<Tiquete> getTiquetes() {
		return tiquetes.values();
		
	}
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException {
		int contador=0;
		for (int i = 0; i < cantidad; i++) {
			if (avion.getCapacidad()<tiquetes.size()) {
				Tiquete tiquete = new Tiquete("TQ" + (tiquetes.size() + 1), this, cliente,
						calculadora.calcularTarifa(this, cliente));
				tiquetes.put(tiquete.getCodigo(), tiquete);
				contador++;
			} else {
				throw new VueloSobrevendidoException(this);
			}
		}
		return contador;
	}
	
	public boolean equals(Object obj) {
		return false;
	}

}
