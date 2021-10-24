package datos;

import excepciones.*;
import sistema.*;
import java.util.*;
import PromocionesYmas.*;

public interface IAccesoDatos {

	boolean existe(String nombreArchivo) throws AccesoDatos;
	List<Atraccion> listarAtraccion(String nombreArchivo)throws LecturaDatosExc, AccesoDatos;
	List<Usuario> listarUsuarios(String nombreArchivo)throws LecturaDatosExc, AccesoDatos;
	List<Promocion> listarPromociones(String nombreArchivo, String nombreArchivo2)throws LecturaDatosExc, AccesoDatos;
	Promocion crearPromo(String linea,String nombreArchivo2)throws CrearDatosExc;
	Usuario crearUsuario(String linea)throws CrearDatosExc;
	Atraccion crearAtraccion(String linea)throws CrearDatosExc;
	
	
	void escribir(Object objeto, String nombreArchivo, boolean unir)throws EscrituraDatosExc;
	
	void crear(String nombreArchivo) throws AccesoDatos;
	void borrar(String nombreArchivo) throws AccesoDatos;
	
	/*
	void escribir(Producto producto, String nombreArchivo, boolean unir)throws EscrituraDatosExc;
	void escribir(Atraccion atracion, String nombreArchivo, boolean unir)throws EscrituraDatosExc;
	void escribir(Usuario usuario, String nombreArchivo, boolean unir)throws EscrituraDatosExc;
	void escribir(Promocion promocion, String nombreArchivo, boolean unir)throws EscrituraDatosExc;
	 */
}
