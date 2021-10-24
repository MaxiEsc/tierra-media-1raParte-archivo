package datos;

import java.util.*;
import PromocionesYmas.*;
import excepciones.*;
import sistema.*;
import java.io.*;

public class DatosArchivos implements IAccesoDatos {

	@Override
	public boolean existe(String nombreArchivo) throws AccesoDatos {
		File archivo = new File(nombreArchivo);
		return archivo.exists();
	}

	@Override
	public List<Atraccion> listarAtraccion(String nombreArchivo) throws LecturaDatosExc, CrearDatosExc {
		File archivo = new File(nombreArchivo);
		List<Atraccion> atracciones = new ArrayList<>();

		BufferedReader lectura;
		try {
			lectura = new BufferedReader(new FileReader(archivo));
			String linea = null;
			linea = lectura.readLine();
			while (linea != null) {

				atracciones.add(crearAtraccion(linea));
				linea = lectura.readLine();  
			}
			lectura.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
			throw new LecturaDatosExc("Excepcion!! Problemas al listar las atracciones " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace(System.out);
			throw new LecturaDatosExc(
					"Excepcion!! problemas al realizar lectura al listado de atracciones " + e.getMessage());
		} catch (CrearDatosExc e) {
			e.printStackTrace(System.out);
		}
		return atracciones;
	}

	@Override
	public List<Usuario> listarUsuarios(String nombreArchivo) throws LecturaDatosExc, CrearDatosExc {
		File archivo = new File(nombreArchivo);
		List<Usuario> usuarios = new ArrayList<>();
		BufferedReader lectura;
		try {
			lectura = new BufferedReader(new FileReader(archivo));
			String linea = null;
			linea = lectura.readLine();
			while (linea != null) {
				usuarios.add(crearUsuario(linea));
				linea = lectura.readLine();
			}
			lectura.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
			throw new LecturaDatosExc("Excepcion!! Problemas al listar los usuarios " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace(System.out);
			throw new LecturaDatosExc(
					"Excepcion!! problemas al realizar lectura al listado de Usuarios " + e.getMessage());
		} catch (CrearDatosExc e) {
			e.printStackTrace(System.out);
		}
		return usuarios;
	}
	
	@Override
	public List<Promocion> listarPromociones(String nombreArchivo, String nombreArchivo2) 
			throws LecturaDatosExc, AccesoDatos {
		File archivo = new File(nombreArchivo);
		List<Promocion> promociones = new ArrayList<>();
		BufferedReader lectura;
		try {
			lectura = new BufferedReader(new FileReader(archivo));
			String linea = null;
			linea = lectura.readLine();
			while (linea != null) {

				promociones.add(crearPromo(linea,nombreArchivo2));
				linea = lectura.readLine();

			}
			lectura.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
			throw new LecturaDatosExc("Excepcion!! Problemas al listar las promociones " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace(System.out);
			throw new LecturaDatosExc(
					"Excepcion!! problemas al realizar lectura al listado de promociones " + e.getMessage());
		} catch (CrearDatosExc e) {
			e.printStackTrace(System.out);
		}
		
		return promociones;
	}

	@Override
	public Promocion crearPromo(String linea, String nombreArchivo2) throws CrearDatosExc {
		String[] resultado = linea.split(",");
		Promocion nPromo = null;
		try {
			List<Atraccion> atracciones = new ArrayList<Atraccion>();
			atracciones = listarAtraccion(nombreArchivo2);
			
			if (resultado[1].equalsIgnoreCase("absoluto")) {
				
				nPromo = new Promocion(resultado[0],new Tipo(resultado[1]));
				nPromo.cambiarMonedas(Integer.parseInt(resultado[2]));
				nPromo.setCantidad_atracciones(Integer.parseInt(resultado[3]));
				nPromo.cargarListaAtraciones(resultado[4]);
				nPromo.cargarListaAtraciones(resultado[5]);		
				nPromo.cambiarMonedas(nPromo.calcularPrecio(atracciones,resultado[4] ,resultado[5], ""));
				nPromo.cambiarTiempo(nPromo.calcularTiempo(atracciones, resultado[4], resultado[5], ""));
				
			} else if (resultado[1].equalsIgnoreCase("porcentual")) {
				
				nPromo = new Promocion(resultado[0],new Tipo(resultado[1]));
				double porcentaje = Double.parseDouble(resultado[2]);
				porcentaje = 1.00 - porcentaje;
				nPromo.setCantidad_atracciones(Integer.parseInt(resultado[3]));
				nPromo.cargarListaAtraciones(resultado[4]);
				nPromo.cargarListaAtraciones(resultado[5]);
				nPromo.cargarListaAtraciones(resultado[6]);
				nPromo.cambiarMonedas((int)(nPromo.calcularPrecio(atracciones,resultado[4],
						resultado[5], resultado[6])*porcentaje));
				nPromo.cambiarTiempo(nPromo.calcularTiempo(atracciones, resultado[4],
						resultado[5], resultado[6]));				
				
			} else if (resultado[1].equalsIgnoreCase("AxB")) {
				
				nPromo = new Promocion(resultado[0], new Tipo(resultado[1]));
				nPromo.setCantidad_atracciones(Integer.parseInt(resultado[2]));
				nPromo.cargarListaAtraciones(resultado[3]);
				nPromo.cargarListaAtraciones(resultado[4]);
				nPromo.cargarListaAtraciones(resultado[6]);
				nPromo.cambiarMonedas(nPromo.calcularPrecio(atracciones,resultado[3], resultado[4], resultado[6]));
				nPromo.cambiarTiempo(nPromo.calcularTiempo(atracciones, resultado[3], resultado[4], resultado[6]));
			}else {
				System.out.println("No se pueden cargar los archivos");
			}
			
		} catch (LecturaDatosExc e) {
			e.printStackTrace();
		} catch (CrearDatosExc e) {
			e.printStackTrace();
		} 		

		return nPromo;
	}

	@Override
	public Usuario crearUsuario(String linea) throws CrearDatosExc {
		String[] resultado = linea.split(",");

		Usuario nUser = new Usuario(resultado[0],Integer.parseInt(resultado[1]),
				Double.parseDouble(resultado[2]));
		
		return nUser;
	}

	@Override
	public Atraccion crearAtraccion(String linea) throws CrearDatosExc {
		String[] resultado = linea.split(",");

		Atraccion nAtrac = new Atraccion(resultado[0],Double.parseDouble(resultado[1]),
				Integer.parseInt(resultado[2]),Integer.parseInt(resultado[3]));
		
		return nAtrac;
	}

	@Override
	public void escribir(Object objeto, String nombreArchivo, boolean unir) throws EscrituraDatosExc {
		File archivo = new File(nombreArchivo);

		try {
			PrintWriter escritura = new PrintWriter(new FileWriter(archivo, unir));
			// se escribe el archivo gracias al toString() de cada clase.
			escritura.println(objeto.toString());
			escritura.close();

		} catch (IOException e) {
			e.printStackTrace(System.out);
			throw new EscrituraDatosExc("Error al intentar escribir datos" + e.getMessage());
		}
	}

	@Override
	public void crear(String nombreArchivo) throws AccesoDatos {
		File archivo = new File(nombreArchivo);

		try {
			// Se crea un nuevo archivo necesario para la gestion de datos
			PrintWriter nuevo = new PrintWriter(new FileWriter(archivo));
			System.out.println("El archivo se ha creado perfectamente");
			nuevo.close();
		} catch (IOException e) {
			e.printStackTrace(System.out);
			throw new AccesoDatos("Error al crear un nuevo archivo" + e.getMessage());
		}
	}

	@Override
	public void borrar(String nombreArchivo) throws AccesoDatos {
		File archivo = new File(nombreArchivo);

		if (archivo.exists()) {
			archivo.delete();
			System.out.println("EL archivo ha sido eliminado");
		}
	}

	/*
	 * @Override public void escribir(Producto producto, String nombreArchivo,
	 * boolean unir) throws EscrituraDatosExc { File archivo = new
	 * File(nombreArchivo);
	 * 
	 * try {
	 * 
	 * PrintWriter escritura = new PrintWriter(new FileWriter(archivo, unir)); //se
	 * escribe el archivo gracias al toString de cada clase en este caso de producto
	 * escritura.println(producto.toString());
	 * 
	 * } catch (IOException e) {
	 * 
	 * e.printStackTrace(System.out); throw new
	 * EscrituraDatosExc("Error al intentar escribir datos" + e.getMessage()); }
	 * 
	 * }
	 * 
	 * @Override public void escribir(Atraccion atracion, String nombreArchivo,
	 * boolean unir) throws EscrituraDatosExc { File archivo = new
	 * File(nombreArchivo);
	 * 
	 * try {
	 * 
	 * PrintWriter escritura = new PrintWriter(new FileWriter(archivo, unir));
	 * escritura.println(atracion.toString()); } catch (IOException e) {
	 * 
	 * e.printStackTrace(System.out); throw new
	 * EscrituraDatosExc("Error al intentar escribir datos" + e.getMessage()); }
	 * 
	 * }
	 * 
	 * 
	 * @Override public void escribir(Usuario usuario, String nombreArchivo, boolean
	 * unir) throws EscrituraDatosExc { File archivo = new File(nombreArchivo);
	 * 
	 * try { PrintWriter escritura = new PrintWriter(new FileWriter(archivo,unir));
	 * escritura.println(usuario.toString()); } catch (IOException e) {
	 * 
	 * e.printStackTrace(System.out); throw new
	 * EscrituraDatosExc("Error al intentar escribir datos" + e.getMessage()); }
	 * 
	 * }
	 * 
	 * 
	 * @Override public void escribir(Promocion promocion, String nombreArchivo,
	 * boolean unir) throws EscrituraDatosExc { File archivo = new
	 * File(nombreArchivo);
	 * 
	 * try {
	 * 
	 * PrintWriter escritura = new PrintWriter(new FileWriter(archivo));
	 * escritura.println(promocion); } catch (IOException e) {
	 * 
	 * e.printStackTrace(System.out); throw new
	 * EscrituraDatosExc("Error al intentar escribir datos" + e.getMessage()); }
	 * 
	 * }
	 */
}
