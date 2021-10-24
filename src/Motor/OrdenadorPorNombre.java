package Motor;

import java.util.Comparator;

import sistema.Usuario;

public class OrdenadorPorNombre implements Comparator<Usuario> {

	@Override
	public int compare(Usuario arg1, Usuario arg2) {
		return arg1.obtenerNombre().compareTo(arg2.obtenerNombre());
	}

}
