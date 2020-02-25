package uniandes.lym.robot.control;

public class Variable {
	
	
	private String nombre; 
	
	private Integer numero; 
	
	public Variable(Integer pNumero, String pNombre)
	{
		nombre = pNombre; 
		numero = pNumero; 
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public Integer getNumero() {
		return numero;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}
