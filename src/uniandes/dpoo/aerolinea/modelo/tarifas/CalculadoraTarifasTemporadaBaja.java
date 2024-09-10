package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas{
	
	protected final int COSTO_POR_KM_NATURAL=600;
	protected final int COSTO_POR_KM_CORPORATIVO=800;
	protected final double DESCUENTO_PEQ=0.02;
	protected final double DESCUENTO_MEDIANAS=0.1;
	protected final double DESCUENTO_GRANDES=0.2;
	
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		int costoBase = 0;
		int distancia = calcularDistanciaVuelo(vuelo.getRuta());
		if (cliente.getTipoCliente().equals("Natural")) {
			costoBase = distancia * COSTO_POR_KM_NATURAL;
		} else {
			costoBase = distancia * COSTO_POR_KM_CORPORATIVO;
		}
		return costoBase;
	}
	
	@Override
	public double calcularPorcentajeDescuento(Vuelo vuelo, Cliente cliente) {
		double descuento = 0.0;
		String clientej= cliente.getTipoCliente();
		if (clientej.equals("Corporativo")) {
		    ClienteCorporativo clientej1 = (ClienteCorporativo) cliente;
			if (clientej1.getTamanoEmpresa()==1) {
				descuento = DESCUENTO_PEQ;
			} else if (clientej1.getTamanoEmpresa()==2) {
				descuento = DESCUENTO_MEDIANAS;
			} else if (clientej1.getTamanoEmpresa()==3) {
				descuento = DESCUENTO_GRANDES;
			}
			
			
			}
		return descuento;
	
	}

	

	
	
}
