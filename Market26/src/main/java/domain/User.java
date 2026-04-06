package domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String email;
	private String name;
	private String contrasena;
	private float saldo = 0.0f;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Transaccion> transacciones=new ArrayList<Transaccion>();
	
	public User(String email, String name,String contrasena) {
	    this.email = email;
	    this.name = name;
	    this.contrasena=contrasena;
	}
	public User() {}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return email+";"+name +transacciones;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public Transaccion addTransaccion(float precioFinal, String tipo, String descripcion, float saldoAnterior, float nuevoSaldo)  {
		Transaccion t = new Transaccion(precioFinal, tipo, descripcion, this, saldoAnterior, nuevoSaldo);
        transacciones.add(t);
        return t;
	}
	
	

}
