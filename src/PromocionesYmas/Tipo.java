package PromocionesYmas;

public class Tipo {

	private String tipo_atraccion;

	public Tipo(String tipo_atraccion) {
		this.tipo_atraccion = tipo_atraccion;
	}

	public String getTipo_atraccion() {
		return tipo_atraccion;
	}

	public void setTipo_atraccion(String tipo_atraccion) {
		this.tipo_atraccion = tipo_atraccion;
	}

	@Override
	public String toString() {
		return "Tipo [ Tipo Atraccion : " + tipo_atraccion + " ]";
	}
	
	

}
