package sistema;

public interface InProducto {
	
	String obtenerNombre();
	int obtenerMonedas();
	double obtenerTiempo();	
	int obtenerID();
	void cambiarMonedas(int monedas);
	void cambiarTiempo(double tiempo);
}
