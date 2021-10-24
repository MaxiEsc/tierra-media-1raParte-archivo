package sistema;

import java.util.*;

public class Atraccion implements InProducto{
	private int id_Atraccion;
	private String nombre;
	private double tiempo;
	private int precio_monedas;
	private int cupo;

	private static int contadorId = 0;

	public Atraccion(String nombre, double tiempo, int precio_monedas, int cupo) {
		this.id_Atraccion = ++Atraccion.contadorId;
		this.nombre = nombre;
		this.tiempo = tiempo;
		this.precio_monedas = precio_monedas;
		this.cupo = cupo;
	}

	// Metodos de interface

	@Override
	public String obtenerNombre() {
		return this.nombre;
	}

	@Override
	public int obtenerMonedas() {
		return this.precio_monedas;
	}

	@Override
	public double obtenerTiempo() {
		return this.tiempo;
	}

	@Override
	public int obtenerID() {
		return this.id_Atraccion;
	}

	@Override
	public void cambiarMonedas(int monedas) {
		this.precio_monedas = monedas;
	}

	@Override
	public void cambiarTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

	// Metodos de la clase Atraccion

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
 
	public int getCupo() {
		return cupo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void publicidad(Usuario user) {

		System.out.println("Nombre: " + user.obtenerNombre() + "Atraccion: " + obtenerNombre() + "\nCosto: " + obtenerMonedas() + " Tiempo : "
				+ obtenerTiempo() + "\n Monedas restantes: " + user.obtenerMonedas() + " Tiempo Restante : "
				+ user.obtenerTiempo());

	}

	// Metodos para realizar un posible orden

	@Override
	public int hashCode() {
		return Objects.hash(cupo, id_Atraccion, nombre, precio_monedas, tiempo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return cupo == other.cupo && id_Atraccion == other.id_Atraccion && Objects.equals(nombre, other.nombre)
				&& precio_monedas == other.precio_monedas
				&& Double.doubleToLongBits(tiempo) == Double.doubleToLongBits(other.tiempo);
	}

	@Override
	public String toString() {
		return "Atraccion [ Cupo :" + cupo + ", Id Atraccion : " + id_Atraccion + ", Nombre : " + nombre
				+ ", Precio en Monedas : " + precio_monedas + ", Tiempo : " + tiempo + " ]";
	}	 

}
