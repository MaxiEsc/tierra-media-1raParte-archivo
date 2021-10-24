package Motor;

import java.util.Comparator;

import PromocionesYmas.Promocion;

public class OrdenadorPromociones implements Comparator<Promocion> {

	@Override
	public int compare(Promocion arg0, Promocion arg1) {
		if(arg0.obtenerMonedas() == arg1.obtenerMonedas()) {
			return Integer.valueOf((int)arg0.obtenerTiempo()).compareTo((int)arg1.obtenerTiempo());
		}
		return Integer.valueOf(arg0.obtenerMonedas()).compareTo(arg1.obtenerMonedas());
	}

}
