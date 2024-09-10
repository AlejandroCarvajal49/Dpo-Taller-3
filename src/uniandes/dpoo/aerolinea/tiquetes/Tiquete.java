package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class Tiquete {
	
	private String codigo;
	private int tarifa;
	private boolean usado;
	private Cliente clienteComprador;
	private Vuelo vuelo;
	
	public Tiquete(String codigo, Vuelo vuelo,Cliente clienteComprador, int tarifa) {
		this.codigo = codigo;
		this.tarifa = tarifa;
		this.vuelo=vuelo;
		this.clienteComprador = clienteComprador;
		
		
	}

	public String getCodigo() {
		return codigo;
	}

	public int getTarifa() {
		return tarifa;
	}
	
	public Cliente getCliente() {
		return clienteComprador;
	}
	
	public Vuelo getVuelo() {
		return vuelo;
	}
	
	public void marcarComoUsado() {
		this.usado = true;

	}
	public boolean esUsado() {
		return usado;
	}

}
