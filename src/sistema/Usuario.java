package sistema;

import java.util.*;

public class Usuario implements InProducto {
	private int id_Usuario;
	private String nombre;
	private int monedas;
	private double tiempo;
	private List<String> atraccionesRealizadas = new ArrayList<>();

	private static int contadorId = 0;

	public Usuario(String nombre, int monedas, double tiempo) {
		this.id_Usuario = ++Usuario.contadorId;
		this.nombre = nombre;
		this.monedas = monedas;
		this.tiempo = tiempo;
	}

	// Metodos de interfaz
	
	@Override
	public String obtenerNombre() {

		return this.nombre;
	}

	@Override
	public int obtenerMonedas() {
		return this.monedas;
	}

	@Override
	public double obtenerTiempo() {

		return this.tiempo;
	}

	@Override
	public int obtenerID() {
		return this.id_Usuario;
	}

	@Override
	public void cambiarMonedas(int monedas) {
		this.monedas = monedas;
	}

	@Override
	public void cambiarTiempo(double tiempo) {
		this.tiempo = tiempo;
	}
	
	//Metodos de Usuario
	

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<String> getAtraccionesRealizadas() {
		return atraccionesRealizadas;
	}

	public void setAtraccionesRealizadas(List<String> atraccionesRealizadas) {
		this.atraccionesRealizadas = atraccionesRealizadas;
	}

	public void agregarAtracciones(String atracciones) {
		atraccionesRealizadas.add(atracciones);
	}
	
	public void listarAtraciones(String atracciones) {
		System.out.println("Atraciones tomadas: ");
		for (String iterar : atraccionesRealizadas) {
			System.out.println("-> " + iterar);
		}
	}
	
	//Metodos necesarios para calcular un posible orden por si se necesita ordenar

	

	@Override
	public String toString() {
		return "Usuario [ Id Usuario=" + id_Usuario + ", Nombre : " + nombre + ", Monedas : " + monedas + ", Tiempo : "
				+ tiempo + ", Atracciones Realizadas : " + atraccionesRealizadas + "] Compra -> ";
	}

	@Override
	public int hashCode() {
		return Objects.hash(atraccionesRealizadas, id_Usuario, monedas, nombre, tiempo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(atraccionesRealizadas, other.atraccionesRealizadas) && id_Usuario == other.id_Usuario
				&& monedas == other.monedas && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempo) == Double.doubleToLongBits(other.tiempo);
	}	

}
