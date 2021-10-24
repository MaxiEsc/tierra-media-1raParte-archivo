package presentacion;

import Motor.*;
import sistema.*;
import java.util.*;

public class PresentacionMain {
	public static void main(String[] args) {

		ImpSistema servicio = new Sistema();
		servicio.iniciarSistema();
		
		String dato = "";
		Scanner principal = new Scanner(System.in);		
		while(!(dato.equalsIgnoreCase("salir"))) {
			pantalla();
			System.out.print("Ingrese Palabra o letra: ");
			dato = principal.nextLine(); 
			
			if (dato.equalsIgnoreCase("I")) {
				System.out.print("Ingrese el nombre del usuario en cuestion: ");
				String nombre = principal.nextLine();
				Usuario usuarioRegistrado = servicio.ingresarUsuarioPorNombre(nombre);
				servicio.sugerenciaPromociones(usuarioRegistrado);
				servicio.sugerenciaAtraciones(usuarioRegistrado);
			} 
			if(dato.equalsIgnoreCase("salir")) {
				System.out.println("Gracias por usar este servicio");				
			}
		}

	}
	public static void pantalla() {

		System.out.println("Bienvenido a nuestro servicio \n" + 
				"Ingrese 'I' para iniciar servicio con un nuevo usuario \n" + 
				"Ingrese 'salir' para salir de este menu");
	}
}
