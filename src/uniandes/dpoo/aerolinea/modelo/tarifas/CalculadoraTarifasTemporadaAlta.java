package uniandes.dpoo.aerolinea.modelo.tarifas;



import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas{
	
	protected int COSTO_POR_KM=1000;

	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		
		int distancia = calcularDistanciaVuelo(vuelo.getRuta());
		double costoBase = distancia * COSTO_POR_KM - calcularPorcentajeDescuento(vuelo, cliente) * distancia;
		return (int) costoBase;
	}
	
	
	
	
	public double calcularPorcentajeDescuento( Vuelo vuelo, Cliente cliente) {
		double descuento = 0.0;
		String clientej = cliente.getTipoCliente();
		if (clientej.equals("Corporativo")) {
			ClienteCorporativo clientej1 = (ClienteCorporativo) cliente;
			if (clientej1.getTamanoEmpresa() == 1) {
				descuento = 0.02;
			} else if (clientej1.getTamanoEmpresa() == 2) {
				descuento = 0.1;
			} else if (clientej1.getTamanoEmpresa() == 3) {
				descuento = 0.2;
			}

		}
		return descuento;

	}




	

}
