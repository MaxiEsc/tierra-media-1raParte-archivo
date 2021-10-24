package Motor;

import PromocionesYmas.Promocion;
import sistema.Atraccion;
import sistema.Usuario;

public interface ImpSistema {
	
	String NOMBRE_ARCHIVO_USUARIOS = "D:\\Mis documentos\\Todo tipo de documentos\\yoprogramo\\Proyecto-Tierra-Media-1raParte\\archivos\\usuarios.txt";
	String NOMBRE_ARCHIVO_ATRACIONES = "D:\\Mis documentos\\Todo tipo de documentos\\yoprogramo\\Proyecto-Tierra-Media-1raParte\\archivos\\atracciones.txt";
	String NOMBRE_ARCHIVO_PROMOCIONES = "D:\\Mis documentos\\Todo tipo de documentos\\yoprogramo\\Proyecto-Tierra-Media-1raParte\\archivos\\promociones.txt";
	String TICKET = "D:\\Mis documentos\\Todo tipo de documentos\\yoprogramo\\Proyecto-Tierra-Media-1raParte\\salida\\ticket.txt";
	
	public void ordenarUsuariosporNombre();
	public void ordenarAtraciones();
	public void ordenarPromociones();
	public void sugerenciaAtraciones(Usuario usuario);
	public void sugerenciaPromociones(Usuario usuario);
	public void generarTicketA(Usuario usuario, Atraccion atraccion);
	public void comprarAtraccion(Usuario usuario, Atraccion atraccion);
	public void generarTicketP(Usuario usuario, Promocion promo);
	public void comprarPromocion(Usuario usuario, Promocion promo);
	public void iniciarSistema();
	public double tiempoMinimoA();
	public double tiempoMinimoP();
	public int precioMinimoA();
	public int precioMinimoP();
	public String consulta(int indice, Usuario usuario);
	public Usuario ingresarUsuarioPorNombre(String nombreUsuario);
	
	
}
