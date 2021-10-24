package PromocionesYmas;

import java.util.*;
import sistema.*;

public class Promocion implements InProducto {

	static final String PROMOCION_ABSOLUTA = "absoluta";
	static final String PROMOCION_PORCENTUAL = "porcentual";
	static final String PROMOCION_AxB = "AxB";

	private String nombre;
	private Tipo tipo;
	private int cantidad_atracciones;
	private int precio_Monedas;
	private double duracion;
	private int id_promocion;
	private List<String> atraccionPromocion = new ArrayList<>();

	private static int contadorId = 0;

	public Promocion(String nombre, Tipo tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.id_promocion = ++Promocion.contadorId;
	}

	public Promocion(String nombre, Tipo tipo, int cantidad_atracciones, int precio_Monedas, double duracion) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.cantidad_atracciones = cantidad_atracciones;
		this.precio_Monedas = precio_Monedas;
		this.duracion = duracion;
		this.id_promocion = ++Promocion.contadorId;
	}

	// Metodos de interfaces

	@Override
	public String obtenerNombre() { 
		return this.nombre;
	}

	@Override
	public int obtenerMonedas() {
		return this.precio_Monedas;
	}

	@Override
	public double obtenerTiempo() {
		return this.duracion;
	}

	@Override
	public int obtenerID() {
		return this.id_promocion;
	}

	@Override
	public void cambiarMonedas(int monedas) {
		this.precio_Monedas = monedas;
	}

	@Override
	public void cambiarTiempo(double tiempo) {
		this.duracion = tiempo;
	}

	// Metodos de promocion

	public Tipo getTipo() {
		return tipo;
	}

	public List<String> getAtraccionPromocion() {
		return atraccionPromocion;
	}

	public void setAtraccionPromocion(List<String> atraccionPromocion) {
		this.atraccionPromocion = atraccionPromocion;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int getCantidad_atracciones() {
		return cantidad_atracciones;
	}

	public void setCantidad_atracciones(int cantidad_atracciones) {
		this.cantidad_atracciones = cantidad_atracciones;
	}

	public void cargarListaAtraciones(String atraccion) {
		atraccionPromocion.add(atraccion);
	}

	public void publicidad(Usuario user) {
		System.out.println("Nombre : " + user.obtenerNombre() + "\nLa Promocion: " + obtenerNombre() + " Con el Precio total: " + obtenerMonedas()
				+ " Tiempo Requerido: " + obtenerTiempo() + "\nMonedas restantes: " + user.obtenerMonedas()
				+ " Tiempo restante: " + user.obtenerTiempo());
	}

	public void mostrarAtracPromocion() {

		System.out.println("atracciones disponibles en esta Promocion");
		for (String iterar : atraccionPromocion) {
			System.out.println(iterar);
		}
	}

	public double calcularTiempo(List<Atraccion> atraccion, String atraccionA, String atraccionB, String atraccionC) {
		double total = 0;
		int casos = 0;
		int i = 0;

		while (i < atraccion.size() && (casos <= getCantidad_atracciones())) {

			if (atraccion.get(i).obtenerNombre().equalsIgnoreCase(atraccionA)
					|| atraccion.get(i).obtenerNombre().equalsIgnoreCase(atraccionB)
					|| atraccion.get(i).obtenerNombre().equalsIgnoreCase(atraccionC)) {

				total += atraccion.get(i).obtenerTiempo();
				casos++;
			}
			i++;
		}
		return total;
	}

	public int calcularPrecio(List<Atraccion> atraccion, String atraccionA, String atraccionB, String atraccionC) {
		int total = 0;
		int casos = 0;
		int i = 0;

		while (i < atraccion.size() && (casos < getCantidad_atracciones())) {

			if (atraccion.get(i).obtenerNombre().equalsIgnoreCase(atraccionA)
					|| atraccion.get(i).obtenerNombre().equalsIgnoreCase(atraccionB)
					|| atraccion.get(i).obtenerNombre().equalsIgnoreCase(atraccionC)) {

				total += atraccion.get(i).obtenerMonedas();
				casos++;
			}
			i++;
		}
		return total;
	}

	@Override
	public int hashCode() {
		return Objects.hash(atraccionPromocion, cantidad_atracciones, duracion, id_promocion, nombre, precio_Monedas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atraccionPromocion, other.atraccionPromocion)
				&& cantidad_atracciones == other.cantidad_atracciones
				&& Double.doubleToLongBits(duracion) == Double.doubleToLongBits(other.duracion)
				&& id_promocion == other.id_promocion && Objects.equals(nombre, other.nombre)
				&& precio_Monedas == other.precio_Monedas;
	}

	@Override
	public String toString() {
		return "Promocion [ Nombre : " + nombre + ", Tipo : " + tipo + ", Cantidad de Atracciones : "
				+ cantidad_atracciones + ", Precio Ahorro en Monedas : " + precio_Monedas + ", Duracion : " + duracion
				+ ", Id Promocion : " + id_promocion + " ]";
	}

}
