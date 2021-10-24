package Motor;

import java.util.Comparator;

import sistema.Atraccion;

public class OrdenadorAtracciones implements Comparator<Atraccion> {

	@Override
	public int compare(Atraccion arg0, Atraccion arg1) {
		if (arg0.obtenerMonedas() == arg1.obtenerMonedas()) {
			return Integer.valueOf((int)arg0.obtenerTiempo()).compareTo((int)arg1.obtenerTiempo());
		}
		return Integer.valueOf(arg0.obtenerMonedas()).compareTo(arg1.obtenerMonedas());
	}
	
}
