package Motor;

import java.util.*;
import datos.*;
import excepciones.AccesoDatos;
import excepciones.LecturaDatosExc;
import PromocionesYmas.*;
import sistema.*;

public class Sistema implements ImpSistema {

	private final IAccesoDatos datos;

	private List<Usuario> usuarios = new ArrayList<>();
	private List<Atraccion> atracciones = new ArrayList<>();
	private List<Promocion> promociones = new ArrayList<>();

	public Sistema() {

		this.datos = new DatosArchivos();
		try {
			this.usuarios = datos.listarUsuarios(NOMBRE_ARCHIVO_USUARIOS);
		} catch (LecturaDatosExc e) {
			e.printStackTrace();
		} catch (AccesoDatos e) {
			e.printStackTrace();
		}
		try {
			this.atracciones = datos.listarAtraccion(NOMBRE_ARCHIVO_ATRACIONES);
		} catch (LecturaDatosExc e) {
			e.printStackTrace();
		} catch (AccesoDatos e) {
			e.printStackTrace();
		}
		try {
			this.promociones = datos.listarPromociones(NOMBRE_ARCHIVO_PROMOCIONES, NOMBRE_ARCHIVO_ATRACIONES);
		} catch (LecturaDatosExc e) {
			e.printStackTrace();
		} catch (AccesoDatos e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ordenarUsuariosporNombre() {
		this.usuarios.sort(new OrdenadorPorNombre());
		// Es una funcion que nos ayudara mas tarde
	}

	@Override
	public void ordenarAtraciones() {
		this.atracciones.sort(new OrdenadorAtracciones());
	}

	@Override
	public void ordenarPromociones() {
		this.promociones.sort(new OrdenadorPromociones());
	}

	@Override
	public void sugerenciaAtraciones(Usuario usuario) {
		String opcion = "";
		boolean fUsuarios = false;
		int j = 0;
		int indice = 0;
		ordenarAtraciones();

		while (j < usuarios.size() && fUsuarios == false) {

			if (usuarios.get(j).obtenerNombre().equals(usuario.obtenerNombre())
					&& usuarios.get(j).obtenerMonedas() == usuario.obtenerMonedas()
					&& usuarios.get(j).obtenerTiempo() == usuario.obtenerTiempo()) {
				indice = j;
				fUsuarios = true;

			}
			j++;
		}
		if (fUsuarios == true) {
			int i = 0;
			while (i < atracciones.size() && precioMinimoA() > usuarios.get(indice).obtenerMonedas()
					&& usuarios.get(indice).obtenerTiempo() < tiempoMinimoA()) {

				if (!(usuarios.get(indice).getAtraccionesRealizadas().contains(atracciones.get(i).obtenerNombre()))) {

					if (atracciones.get(i).obtenerMonedas() <= usuarios.get(indice).obtenerMonedas()
							&& usuarios.get(indice).obtenerTiempo() < atracciones.get(i).obtenerTiempo()
							&& atracciones.get(i).getCupo() > 0) {

						atracciones.get(i).publicidad(usuarios.get(indice));

						// _________________________________________________________________________________

						while ((!opcion.equalsIgnoreCase("N")) && (!opcion.equalsIgnoreCase("S"))) {

							Scanner consulta = new Scanner(System.in);
							System.out.println("¿Desea tomar la Atraccion?: S: Si  |  N: NO ");
							System.out.print("Opcion : ");

							if (consulta.hasNextLine()) {
								opcion = consulta.nextLine();
							}

							if (!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N")) {
								System.out.println("Ingrese las opciones correspondiente");
								atracciones.get(indice).publicidad(usuario);
							}
						}

						// _________________________________________________________________________________

						if (opcion.equalsIgnoreCase("S")) {
							comprarAtraccion(usuarios.get(indice), atracciones.get(i));
							generarTicketA(usuarios.get(indice), atracciones.get(i));
						} else if (opcion.equalsIgnoreCase("N")) {
							System.out.println("Siguiente atraccion");
						}
					}
				}
				i++;
				opcion = "";
			}
		} else {
			System.out.println("El usuario en cuestion no se encuentra en la lista");
		}
		System.out.println("No hay mas atracciones en el sistema compatibles con su tiempo o dinero restante, Muchas gracias por elegirnos");
	}

	@Override
	public void sugerenciaPromociones(Usuario usuario) {
		String opcion = "";
		boolean fUsuarios = false;
		int j = 0;
		int indice = 0;
		ordenarAtraciones();
		ordenarPromociones();

		while (j < usuarios.size() && fUsuarios == false) {
			if (usuarios.get(j).obtenerNombre().equalsIgnoreCase(usuario.obtenerNombre())
					&& usuarios.get(j).obtenerMonedas() == usuario.obtenerMonedas()
					&& usuarios.get(j).obtenerTiempo() == usuario.obtenerTiempo()) {
				indice = j;
				fUsuarios = true;
			}
			j++;
		}
		j = 0;

		if (fUsuarios == true) {
			while (j < promociones.size() && usuarios.get(indice).obtenerMonedas() > precioMinimoP()
					&& usuarios.get(indice).obtenerTiempo() > tiempoMinimoP()) {

				if (!(usuarios.get(indice).getAtraccionesRealizadas().contains(promociones.get(j).obtenerNombre()))) {

					if (usuarios.get(indice).obtenerMonedas() >= promociones.get(j).obtenerMonedas()
							&& usuarios.get(indice).obtenerTiempo() > promociones.get(j).obtenerTiempo()) {

						promociones.get(j).publicidad(usuarios.get(indice));

						// _________________________________________________________________________________

						while ((!opcion.equalsIgnoreCase("N")) && (!opcion.equalsIgnoreCase("S"))) {

							Scanner consulta = new Scanner(System.in);
							System.out.println("\n¿Desea tomar la Atraccion?: S: Si  |  N: NO \n\n\n");
							System.out.print("Opcion : ");

							if (consulta.hasNextLine()) {
								opcion = consulta.nextLine();
							}

							if (!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N")) {
								System.out.println("Ingrese las opciones correspondiente");
								atracciones.get(indice).publicidad(usuario);
							}
						}

						// _________________________________________________________________________________

						if (opcion.equalsIgnoreCase("S")) {
							comprarPromocion(usuarios.get(indice), promociones.get(j));
							generarTicketP(usuarios.get(indice), promociones.get(j));
						} else if (opcion.equalsIgnoreCase("N")) {
							System.out.println("Siguiente promocion");
						}
					}
				}
				j++;
				opcion = "";

			}
			System.out.println("");
		}
		System.out.println("No hay mas promociones compatibles con su tiempo o dinero restante en el sistema");
	}

	@Override
	public void generarTicketA(Usuario usuario, Atraccion atraccion) {

		boolean anexar = false;
		try {
			anexar = datos.existe(TICKET);
			datos.escribir(usuario, TICKET, anexar);
			datos.escribir(atraccion, TICKET, anexar);

		} catch (AccesoDatos e) {
			e.printStackTrace(System.out);
		}
	}

	@Override
	public void comprarAtraccion(Usuario usuario, Atraccion atraccion) {

		usuario.cambiarMonedas((usuario.obtenerMonedas() - atraccion.obtenerMonedas()));
		usuario.cambiarTiempo((usuario.obtenerTiempo() - atraccion.obtenerTiempo()));
		usuario.agregarAtracciones(atraccion.obtenerNombre());

		atraccion.setCupo(atraccion.getCupo() - 1);
	}

	@Override
	public void generarTicketP(Usuario usuario, Promocion promo) {
		boolean anexar = false;
		try {
			anexar = datos.existe(TICKET);
			datos.escribir(usuario, TICKET, anexar);
			datos.escribir(promo, TICKET, anexar);

		} catch (AccesoDatos e) {
			e.printStackTrace(System.out);
		}
	}

	@Override
	public void comprarPromocion(Usuario usuario, Promocion promo) {

		usuario.cambiarMonedas((usuario.obtenerMonedas() - promo.obtenerMonedas()));
		usuario.cambiarTiempo((usuario.obtenerTiempo() - promo.obtenerTiempo()));

		for (int i = 0; i < promo.getAtraccionPromocion().size(); i++) {
			usuario.agregarAtracciones(promo.getAtraccionPromocion().get(i));
		}

	}

	@Override
	public void iniciarSistema() {

		try {
			if (this.datos.existe(TICKET)) {
				datos.borrar(TICKET);
			} else {
				datos.crear(TICKET);
			}
			datos.crear(TICKET);

		} catch (AccesoDatos e) {
			e.printStackTrace(System.out);
		} finally {
			System.out.println("Sistema iniciado");
		}

	}

	@Override
	public double tiempoMinimoP() {
		double minimo = 0;

		for (int i = 0; i < promociones.size(); i++) {

			if (minimo < promociones.get(i).obtenerTiempo()) {
				minimo = promociones.get(i).obtenerTiempo();
			}
		}

		return minimo;
	}

	@Override
	public double tiempoMinimoA() {
		double minimo = 0;

		for (int i = 0; i < atracciones.size(); i++) {

			if (minimo < atracciones.get(i).obtenerTiempo()) {
				minimo = atracciones.get(i).obtenerTiempo();
			}
		}

		return minimo;
	}

	@Override
	public int precioMinimoA() {
		int minimo = 0;

		for (int i = 0; i < atracciones.size(); i++) {

			if (minimo < atracciones.get(i).obtenerMonedas()) {
				minimo = atracciones.get(i).obtenerMonedas();
			}
		}

		return minimo;
	}

	@Override
	public int precioMinimoP() {
		int minimo = 0;

		for (int i = 0; i < promociones.size(); i++) {

			if (minimo < atracciones.get(i).obtenerMonedas()) {
				minimo = atracciones.get(i).obtenerMonedas();
			}
		}

		return minimo;
	}

	@Override
	public String consulta(int indice, Usuario usuario) {

		String opcion = "";
		Scanner consulta = new Scanner(System.in);

		while ((!opcion.equalsIgnoreCase("N")) && (!opcion.equalsIgnoreCase("S"))) {

			System.out.println("¿Desea tomar la Atraccion?: S: Si  |  N: NO ");
			System.out.print("Opcion : ");
			opcion = consulta.nextLine();

			if (!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N")) {
				System.out.println("Ingrese las opciones correspondiente");
				atracciones.get(indice).publicidad(usuario);
			}
		}
		consulta.close();
		return opcion;
	}

	@Override
	public Usuario ingresarUsuarioPorNombre(String nombreUsuario) {
		Usuario resultado = null;
		boolean encontrado = false;
		int i = 0;
		while (i < usuarios.size() && encontrado == false) {

			if (usuarios.get(i).obtenerNombre().equalsIgnoreCase(nombreUsuario)) {
				resultado = usuarios.get(i);
			}
			i++;
		}
		return resultado;
	}

	/*
	 * public double tiempoAtracciones( Promocion promo, Atraccion atraciones) {
	 * double aux = 0;
	 * 
	 * if() {
	 * 
	 * for (Atraccion iterar : atracciones) { aux += iterar.obtenerTiempo(); }
	 * 
	 * } return aux; }
	 * 
	 * public int promoAatraccionesTotal() { int aux = 0;
	 * 
	 * for (Atraccion iterar : atracciones) { aux += iterar.obtenerMonedas(); }
	 * 
	 * return aux; }
	 * 
	 * public int promoBatracionesDescuento() { int aux = 0;
	 * 
	 * for (int i = 0; i < atracciones.size()-1; i++) {
	 * 
	 * } }
	 * 
	 */
}
